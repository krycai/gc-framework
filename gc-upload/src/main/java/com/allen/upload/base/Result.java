package com.allen.upload.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author guoweijun 2019/3/12 13:37.
 * 用法示例：
 * Result.ok();
 * Result.ok("查询成功");
 * Result.ok(user);
 * Result.ok(user).setMsg("查询成功");
 * Result.error();
 * Result.error("查询失败");
 * Result.error().setResponseCode(ResponseCode.BAD_REQUEST)
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value="Result对象", description="结果集")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码")
    private String code = ResponseCode.SUCCESS.getCode();

    @ApiModelProperty(value = "响应信息")
    private String msg = ResponseCode.SUCCESS.getMessage();

    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 请求成功
     * @return
     */
    public static Result ok(){
        Result result = new Result();
        return result;
    }

    /**
     * 请求成功
     * @param msg
     * @return
     */
    public static Result ok(String msg){
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

    /**
     * 请求成功
     */
    public static<T> Result<T> ok(String msg, T data){
        Result<T> result = new Result<>();
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 请求成功
     */
    public static<T> Result<T> error(String msg, T data){
        Result<T> result = new Result<>();
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 请求成功
     * @param t
     * @return
     */
    public static<T> Result<T> ok(T t){
        Result<T> result = new Result<>();
        result.setData(t);
        return result;
    }

    /**
     * 请求失败
     * @param
     * @return
     */
    public static Result error(){
        Result result = new Result();
        result.setCode(ResponseCode.FAILURE.getCode());
        result.setMsg(ResponseCode.FAILURE.getMessage());
        return result;
    }
    /**
     * 请求失败
     * @param msg
     * @return
     */
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ResponseCode.FAILURE.getCode());
        result.setMsg(msg);
        return result;
    }


    /**
     * 设置响应信息
     * @param responseCode
     * @return
     */
    public Result<T> setResponseCode(ResponseCode responseCode){
        this.setCode(responseCode.getCode());
        this.setMsg(responseCode.getMessage());
        return this;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Objects.equals(code, ResponseCode.SUCCESS.getCode());
    }

    @JsonIgnore
    public boolean isSuccessWithNotNullData() {
        return Objects.equals(code, ResponseCode.SUCCESS.getCode()) && data != null;
    }

    @JsonIgnore
    public boolean isNotSuccess() {
        return !isSuccess();
    }
}
