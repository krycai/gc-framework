package com.allen.sys.model.dto;

import cn.com.bluemoon.mybatis.api.Paging;

import java.io.Serializable;

/**
 * @author xuguocai 2020/5/29 13:33
 */
public class LogParamDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 分页参数
     */
    private Paging page;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Paging getPage() {
        return page;
    }

    public void setPage(Paging page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "LogParamDto{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", page=" + page +
                '}';
    }

}
