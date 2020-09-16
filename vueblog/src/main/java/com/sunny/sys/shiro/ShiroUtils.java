package com.sunny.sys.shiro;

import org.apache.shiro.SecurityUtils;

/**
 * @Author: Sunny
 * @Date: 2020/8/11
 * @Description:
 */
public class ShiroUtils {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
