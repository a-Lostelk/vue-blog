package com.sunny.sys.config;

import com.sunny.sys.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Sunny
 * @Date: 2020/8/23
 * @Description:
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    JwtFilter jwtFilter;

    /**
     * 用来配置静态资源的，比如html，js，css，等等
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    /**
     * 注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter).addPathPatterns("/**").excludePathPatterns("/login", "/user/save");
    }
}
