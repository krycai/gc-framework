package com.allen.upload.base;

/**
 * 返回码枚举类
 */
public enum ResponseCode {

    SUCCESS("200", "请求成功"),
    FAILURE("500", "系统内部异常"),
    BAD_REQUEST("400", "请求参数不合法"),
    NOT_FOUND("404", "您所请求的资源不存在"),
    HTTP_MEDIATYPE_NOTSUPPORTED_ERROR("10102", "不支持的媒体类型"),
    HTTP_REQUESTMETHOD_NOTSUPPORTED_ERROR("10103","不支持的请求方式"),
    LOGIN_EXPIRED("10402", "登录超时, 请重新登录"),
    LOGIN_EXIST("10403", "已在其他地方登录, 请重新登录"),
    TOKEN_NOT_EXIST("10401", "token不允许为空"),
    FILE_UPLOAD_EXCEPTION("10409", "文件上传错误"),
    TOKEN_PERMISSION_DENIED("14403", "权限不足！"),
    CONTEXT_LONG("14404", "超出字段长度限制，请您重新输入！"),
    RESOURCE_LOCKED_DENIED("10405", "资源被锁！")
    ;

    private String code;

    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
