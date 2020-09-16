package com.sunny.sys.utils;

/**
 * @Author: Sunny
 * @Date: 2020/6/14
 * @Description:
 */
public enum ResultEnum {

    /**
     * 系统错误码
     */
    /**
     * UNKNOWN_ERROR 未知错误
     * SUCCESS 成功
     * PARAM_ERROR 参数不合法
     * DATABASE_ERROR 数据库异常
     */
    UNKNOWN_ERROR("-1", "未知错误"),
    SUCCESS("200", "成功"),
    PARAM_ERROR("201", "参数不合法"),
    DATABASE_ERROR("202", "数据库异常"),
    UNKNOWN_USERPASS("203","用户名或密码为空"),
    USER_PASS_ERROR("204", "用户名或密码错误"),
    SYSTEM_EXCEPTION("9999","系统异常"),
    IS_NOT_EXITS("205", "结果不存在"),
    ;


    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String code;
    private String msg;

    public ResultEnum getByCode(String code) {
        for (ResultEnum e : ResultEnum.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }

}
