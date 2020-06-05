package com.allen.sys.constants;

/**
 * @author xuguocai 2020/6/5 11:52
 */
public enum ResponseCodeEnum {
    SC(0,"操作成功","操作成功"),
    FAIL(1,"操作失败","操作失败"),
    FAIL2(-1,"操作失败","操作失败"),

    PARAM_LOSE(101,"缺少参数,参数： [${0}] 不存在或者为null","参数异常"),
    PARAM_ILLEGAL(102,"参数中存在非法数据,参数：[${0}] 值为： [${1}]无法被识别","参数异常"),
    PARAM_ILLEGAL2(103,"参数中存在非法数据,参数：[${0}] 值为： [${1}] 无法被识别，应该是 [${2}] 中的一个","参数异常"),
    PARAM_LONG(104,"参数过长，请检测长度"),

    PARAM_EXCEPTION(199,"参数型无法定义异常","参数异常"),

    CONFIG_LOSE(201,"没有找到对应的配置","配置异常"),
    CONFIG_ILLEGAL(202,"能找到配置，但无法使用","配置异常"),
    CONFIG_EXCEPTION(299,"配置型无法定义异常","配置异常"),


    ACCOUNT_PASSWORD(301,"账号密码错误"),
    ACCOUNT_ILLEGAL(302,"没有此账号"),
    ACCOUNT_ABNORMAL(303,"此账号异常，可能存在风险"),
    ACCOUNT_PERMISSIONS(304,"权限不足"),
    ACCOUNT_EXCEPTION(399,"配置型无法定义异常"),

    FILE_LARGE(401,"文件过大"),
    FILE_DOWNLOAD(402,"文件下载失败"),
    FILE_LOCK(403,"文件被锁，无法处理"),
    FILE_NAME_LONG(404,"文件名过长"),
    FILE_UPLOAD(405,"上传失败"),
    FILE_BAD(406,"上传文件损坏"),
    FILE_EXITS(407,"系统已存在相同文件"),
    FILE_UPLOADING(408,"已存在相同文件正在上传"),
    FILE_DATA(409,"文件数据错误"),
    FILE_CHUNK_COUNT(4,"切片数出错"),
    FILE_SIZE(411,"切片文件大小错误"),
    FILE_EXCEPTION(499,"文件型无法定义异常"),

    BIZ_EXCEPTION(9900,"自定义异常"),
    ;



    private int code;
    private String doc;
    private String fMsg;

    ResponseCodeEnum(int code , String doc , String fMsg){
        this.code = code;
        this.doc = doc;
        this.fMsg = fMsg;
    }

    ResponseCodeEnum(int code , String doc ){
        this(code,doc,doc);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc){
        this.doc = doc;
    }

    public String getDoc(String... args){
        String doc = getDoc();
        for (int i = 0; i < args.length; i++) {
            doc = doc.replace("${"+i+"}",args[i]);
        }
        return doc;
    }

    public String getfMsg() {
        return fMsg;
    }

    public void setfMsg(String fMsg) {
        this.fMsg = fMsg;
    }

    @Override
    public String toString() {
        return "ResponseCodeEnum{" +
                "code=" + code +
                ", doc='" + doc + '\'' +
                ", fMsg='" + fMsg + '\'' +
                "} " + super.toString();
    }
}
