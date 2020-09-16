package com.sunny.sys.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.sys.dto.LoginDto;
import com.sunny.sys.entity.User;
import com.sunny.sys.jwt.JwtUtils;
import com.sunny.sys.service.UserService;
import com.sunny.sys.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Sunny
 * @Date: 2020/8/9
 * @Description: 登录接口
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = "/login")
    public String login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername, loginDto.getUsername());
        User user = userService.getOne(wrapper);
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return ResultUtil.error("-1", "用户名或密码错误");
        }
        String token = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", token);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        return ResultUtil.success(MapUtil.builder().put("id", user.getId())
                .put("username", user.getUsername()).put("avatar", user.getAvatar()).map());
    }

    @RequiresAuthentication
    @GetMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return ResultUtil.success();
    }
}
