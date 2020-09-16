package com.sunny.sys.utils;

/**
 * @Author: Sunny
 * @Date: 2020/6/15
 * @Description:
 */
public class ResultUtil {
    /**
     * @param @param  object
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 成功有返回
     */
    public static String success(Object object) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return JsonUtils.toJson(result);
    }

    /**
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 成功无返回
     */
    public static String success() {
        return ResultUtil.success(null);
    }

    public static String fail() {
        Result result = new Result(ResultEnum.SYSTEM_EXCEPTION.getCode(), ResultEnum.SYSTEM_EXCEPTION.getMsg());
        result.setData(null);
        return JsonUtils.toJson(result);
    }

    public static String fail(String code, String msg) {
        Result result = new Result(code, msg);
        return JsonUtils.toJson(result);
    }

    /**
     * @param @param  object
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 参数不合法返回
     */
    public static String paramError(Object object) {
        Result result = new Result(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMsg());
        result.setData(object);
        return JsonUtils.toJson(result);
    }

    /**
     * @return Result 返回类型
     * @Title: paramError
     * @Description: 参数不合法返回
     */
    public static String paramError() {
        Result result = new Result(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMsg());
        return JsonUtils.toJson(result);
    }

    /**
     * 返回自定义状态码及msg
     * @param code
     * @param msg
     * @return
     */
    public static String customResultParam(String code, String msg,Object object) {
        Result result = new Result(code, msg);
        result.setData(object);
        return JsonUtils.toJson(result);
    }
    /**
     * @return Result 返回类型
     * @Title: paramError
     * @Description: 数据库错误
     */
    public static String dataBaseError() {
        Result result = new Result(ResultEnum.DATABASE_ERROR.getCode(), ResultEnum.DATABASE_ERROR.getMsg());
        return JsonUtils.toJson(result);
    }
    /**
     * @param @param  code
     * @param @param  msg
     * @param @return 设定文件
     * @return Result 返回类型, 错误返回时状态码为 9999
     * @Title: error
     * @Description: 失败方法返回
     */
    public static String error() {
        Result result = new Result(ResultEnum.SYSTEM_EXCEPTION.getCode(),ResultEnum.SYSTEM_EXCEPTION.getMsg());
        return JsonUtils.toJson(result);
    }

    public static String error(String code, String message) {
        Result result = new Result(code, message);
        return JsonUtils.toJson(result);
    }

    /**
     * @param @param  code
     * @param @param  msg
     * @param @return 设定文件
     * @return Result 返回类型, 错误返回时状态码为 9999
     * @Title: error
     * @Description: 失败方法返回
     */
    public static String unkonwUserPass() {
        Result result = new Result(ResultEnum.UNKNOWN_USERPASS.getCode(),ResultEnum.UNKNOWN_USERPASS.getMsg());
        return JsonUtils.toJson(result);
    }

    /**
     * @param @param  code
     * @param @param  msg
     * @param @return 设定文件
     * @return Result 返回类型, 错误返回时状态码为 9999
     * @Title: error
     * @Description: 用户名或密码错误
     */
    public static String userOrPassError() {
        Result result = new Result(ResultEnum.USER_PASS_ERROR.getCode(),ResultEnum.USER_PASS_ERROR.getMsg());
        return JsonUtils.toJson(result);
    }

    /**
     * @param @param  code
     * @param @param  msg
     * @param @return 设定文件
     * @return Result 返回类型, 错误返回时状态码为 9999
     * @Title: error
     * @Description: 失败方法返回
     */
    public static String unknownError() {
        Result result = new Result(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg());
        return JsonUtils.toJson(result);
    }

    public static String isNotExits() {
        Result result = new Result(ResultEnum.IS_NOT_EXITS.getCode(),ResultEnum.IS_NOT_EXITS.getMsg());
        return JsonUtils.toJson(result);
    }
}
