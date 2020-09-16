package com.sunny.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *      用户表
 * </p>
 *
 * @author Sunny
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @TableField("password")
    @NotBlank(message = "密码不能为空")
    private String password;

    @TableField("status")
    private Integer status;

    @TableField("created")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date created;

    @TableField("last_login")
    private Date lastLogin;


}
