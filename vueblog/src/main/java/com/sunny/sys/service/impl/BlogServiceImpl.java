package com.sunny.sys.service.impl;

import com.sunny.sys.entity.Blog;
import com.sunny.sys.mapper.BlogMapper;
import com.sunny.sys.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sunny
 * @since 2020-08-09
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
