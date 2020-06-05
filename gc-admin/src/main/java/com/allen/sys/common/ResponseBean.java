package com.allen.sys.common;

import com.allen.sys.constants.ResponseCodeEnum;

import java.io.Serializable;

/**
 * @author xuguocai 2020/6/5 11:50
 */
public class ResponseBean<T>  implements Serializable {
    private static final long serialVersionUID = 7L;
    /**
     * 业务是否成功
     * */
    private boolean isSuccess;

    /**
     * 响应码,尽量从ResponseCodeEnum中找到对应的错误码
     * {@link ResponseCodeEnum}
     * */
    private int responseCode;
    /**
     * 返回信息
     * */
    private String responseMsg;
    /**
     * 数据实体类，如非必要，禁止使用Json
     * */
    private T data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public ResponseBean setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public ResponseBean setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        if(responseCode == ResponseCodeEnum.SC.getCode()){
            setSuccess(true);
            setResponseMsg(ResponseCodeEnum.SC.getDoc());
        }
        return this;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public ResponseBean setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseBean<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "isSuccess=" + isSuccess +
                ", responseCode=" + responseCode +
                ", responseMsg='" + responseMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
