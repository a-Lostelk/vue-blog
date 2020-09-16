package com.sunny.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.sys.entity.Blog;
import com.sunny.sys.service.BlogService;
import com.sunny.sys.shiro.ShiroUtils;
import com.sunny.sys.utils.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Sunny
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/list")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {
        int pageSize = 5;
        Page page = new Page(currentPage, pageSize);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().lambda().orderByDesc(Blog::getCreated));
        return ResultUtil.success(pageData);
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "文章不存在");
        return ResultUtil.success(blog);
    }

    @RequiresAuthentication
    @PostMapping(value = "/edit")
    public String edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtils.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtils.getProfile().getId().longValue());
            temp.setCreated(new Date());
            temp.setStatus(0);
        }
        BeanUtils.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return ResultUtil.success();
    }
}
