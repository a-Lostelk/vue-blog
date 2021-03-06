package com.sunny.sys.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: Sunny
 * @Date: 2020/8/9
 * @Description:
 */
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
