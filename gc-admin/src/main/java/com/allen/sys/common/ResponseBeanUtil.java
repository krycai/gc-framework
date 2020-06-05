package com.allen.sys.common;

import com.allen.sys.constants.ResponseCodeEnum;

/**
 * @author xuguocai 2020/6/1 11:31
 */
public class ResponseBeanUtil {

    /**创建一个成功的bean
     * @return
     */
    public static ResponseBean ok(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        responseBean.setResponseCode(ResponseCodeEnum.SC.getCode());
        responseBean.setResponseMsg(ResponseCodeEnum.SC.getDoc());
        return responseBean;
    }

    /**创建一个成功的bean，code是ResponseCodeEnum.SC.getCode()
     * @param scMsg 成功提示
     * @return
     */
    public static ResponseBean ok(String scMsg){
        ResponseBean responseBean = ok();
        responseBean.setResponseMsg(scMsg);
        return responseBean;
    }

    /**创建一个成功的bean，ResponseCodeEnum.SC.getCode()
     * @param scMsg 成功提示
     * @param data 返回的数据
     * @return
     */
    public static <T> ResponseBean ok(String scMsg , T data){
        ResponseBean responseBean = ok(scMsg);
        responseBean.setData(data);
        return responseBean;
    }
    /**创建一个成功的bean，msg是操作成功，code是ResponseCodeEnum.SC.getCode()
     * @param data 返回的数据，
     * @return
     */
    public static <T> ResponseBean ok( T data){
        ResponseBean responseBean = ok(ResponseCodeEnum.SC.getDoc());
        responseBean.setData(data);
        return responseBean;
    }
    /**创建一个失败的bean
     * @param failCode 错误code
     * @param failMsg 错误信息
     * @return
     */
    public static ResponseBean fail(int failCode , String failMsg){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setResponseCode(failCode);
        responseBean.setResponseMsg(failMsg);
        return responseBean;
    }

    public static ResponseBean fail(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setResponseCode(ResponseCodeEnum.FAIL.getCode());
        responseBean.setResponseMsg(ResponseCodeEnum.FAIL.getDoc());
        return responseBean;
    }

    /**创建一个失败的bean，错误信息是按照responseCodeEnum.getDoc的提示
     * @param responseCodeEnum 错误枚举
     * @return
     */
    public static ResponseBean fail(ResponseCodeEnum responseCodeEnum){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setResponseCode(responseCodeEnum.getCode());
        responseBean.setResponseMsg(responseCodeEnum.getfMsg());
        return responseBean;
    }

    /**创建一个失败的bean
     * @param responseCodeEnum 错误枚举
     * @param args 枚举里面的替换参数
     * @return
     */
    public static ResponseBean fail(ResponseCodeEnum responseCodeEnum , String... args){
        ResponseBean responseBean = fail(responseCodeEnum);
        responseBean.setResponseMsg(responseCodeEnum.getDoc(args));
        return responseBean;
    }

}
