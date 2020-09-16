package com.sunny.sys.service.impl;

import com.sunny.sys.entity.User;
import com.sunny.sys.mapper.UserMapper;
import com.sunny.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sunny
 * @since 2020-07-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
