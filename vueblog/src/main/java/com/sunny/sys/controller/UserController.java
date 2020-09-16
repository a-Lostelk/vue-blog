package com.sunny.sys.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.sys.entity.User;
import com.sunny.sys.service.UserService;
import com.sunny.sys.utils.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Sunny
 * @since 2020-07-28
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequiresAuthentication
    @GetMapping(value = "/index")
    public String findList() {
        User user = userService.getById(1L);
        if (ObjectUtil.isEmpty(user)) {
            return ResultUtil.unkonwUserPass();
        }
        return ResultUtil.success(user);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public String save(@Validated @RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername, user.getUsername());
        User one = userService.getOne(wrapper);
        if (one == null) {
            user.setCreated(new Date());
            user.setStatus(0);
            String pass = SecureUtil.md5(user.getPassword());
            user.setPassword(pass);
            userService.save(user);
            return ResultUtil.success();
        }
        return ResultUtil.fail("406", "用户已存在");
    }
}
