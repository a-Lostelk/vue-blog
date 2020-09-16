package com.sunny.sys.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Sunny
 * @Date: 2020/8/8
 * @Description:
 */
@Data
public class AccountProfile implements Serializable {

    private Integer id;

    private String username;

    private String avatar;

    private String email;
}
