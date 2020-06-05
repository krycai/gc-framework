package com.allen.sys.model.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_operation_log")
public class SysOperationLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 接口说明
     */
    @Column(name = "oper_content")
    private String operContent;

    /**
     * 操作等级
     */
    @Column(name = "oper_level")
    private String operLevel;

    /**
     * 操作类型
     */
    @Column(name = "oper_type")
    private String operType;

    /**
     * 操作方法
     */
    @Column(name = "oper_method")
    private String operMethod;

    /**
     * 请求参数
     */
    @Column(name = "oper_params")
    private String operParams;

    /**
     * ip地址
     */
    @Column(name = "oper_ip")
    private String operIp;

    /**
     * 代理信息
     */
    @Column(name = "oper_user_agent")
    private String operUserAgent;

    /**
     * 备注
     */
    private String message;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取接口说明
     *
     * @return oper_content - 接口说明
     */
    public String getOperContent() {
        return operContent;
    }

    /**
     * 设置接口说明
     *
     * @param operContent 接口说明
     */
    public void setOperContent(String operContent) {
        this.operContent = operContent;
    }

    /**
     * 获取操作等级
     *
     * @return oper_level - 操作等级
     */
    public String getOperLevel() {
        return operLevel;
    }

    /**
     * 设置操作等级
     *
     * @param operLevel 操作等级
     */
    public void setOperLevel(String operLevel) {
        this.operLevel = operLevel;
    }

    /**
     * 获取操作类型
     *
     * @return oper_type - 操作类型
     */
    public String getOperType() {
        return operType;
    }

    /**
     * 设置操作类型
     *
     * @param operType 操作类型
     */
    public void setOperType(String operType) {
        this.operType = operType;
    }

    /**
     * 获取操作方法
     *
     * @return oper_method - 操作方法
     */
    public String getOperMethod() {
        return operMethod;
    }

    /**
     * 设置操作方法
     *
     * @param operMethod 操作方法
     */
    public void setOperMethod(String operMethod) {
        this.operMethod = operMethod;
    }

    /**
     * 获取请求参数
     *
     * @return oper_params - 请求参数
     */
    public String getOperParams() {
        return operParams;
    }

    /**
     * 设置请求参数
     *
     * @param operParams 请求参数
     */
    public void setOperParams(String operParams) {
        this.operParams = operParams;
    }

    /**
     * 获取ip地址
     *
     * @return oper_ip - ip地址
     */
    public String getOperIp() {
        return operIp;
    }

    /**
     * 设置ip地址
     *
     * @param operIp ip地址
     */
    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    /**
     * 获取代理信息
     *
     * @return oper_user_agent - 代理信息
     */
    public String getOperUserAgent() {
        return operUserAgent;
    }

    /**
     * 设置代理信息
     *
     * @param operUserAgent 代理信息
     */
    public void setOperUserAgent(String operUserAgent) {
        this.operUserAgent = operUserAgent;
    }

    /**
     * 获取备注
     *
     * @return message - 备注
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置备注
     *
     * @param message 备注
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}