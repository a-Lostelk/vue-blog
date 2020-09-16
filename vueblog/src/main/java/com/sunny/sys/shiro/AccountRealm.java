package com.sunny.sys.shiro;

import com.sunny.sys.entity.User;
import com.sunny.sys.jwt.JwtToken;
import com.sunny.sys.service.UserService;
import com.sunny.sys.jwt.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Sunny
 * @Date: 2020/8/2
 * @Description: 完成用户登录和权限的核心类
 */
@Component
public class AccountRealm  extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    /**
     * 判断是否支持JWTtoken
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) token.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户被锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user, profile);
        return new SimpleAuthenticationInfo(profile, token.getPrincipal(), getName());
    }
}
