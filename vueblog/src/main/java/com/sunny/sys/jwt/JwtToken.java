package com.sunny.sys.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @CreateBy: Sunny
 * @Date: 2020/8/7
 * @Description: AuthenticationToken 是用来获取到用户的相关凭证
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
