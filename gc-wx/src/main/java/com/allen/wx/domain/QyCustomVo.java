package com.allen.wx.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XmlRootElement 与 XStreamAlias 是两种处理方式
 */
@XmlRootElement(name = "xml")
@XStreamAlias("xml")
public class QyCustomVo {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Event;
    private String ChangeType;
    private String UserID;
    private String ExternalUserID;
    private String State;
    private String WelcomeCode;

    public QyCustomVo() {
    }

    public String getToUserName() {

        return ToUserName;
    }
    @XmlElement(name = "ToUserName")
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }
    @XmlElement(name = "FromUserName")
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }
    @XmlElement(name = "CreateTime")
    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }
    @XmlElement(name = "MsgType")
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }
    @XmlElement(name = "Event")
    public void setEvent(String event) {
        Event = event;
    }

    public String getChangeType() {
        return ChangeType;
    }
    @XmlElement(name = "ChangeType")
    public void setChangeType(String changeType) {
        ChangeType = changeType;
    }

    public String getUserID() {
        return UserID;
    }
    @XmlElement(name = "UserID")
    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getExternalUserID() {
        return ExternalUserID;
    }
    @XmlElement(name = "ExternalUserID")
    public void setExternalUserID(String externalUserID) {
        ExternalUserID = externalUserID;
    }

    public String getState() {
        return State;
    }
    @XmlElement(name = "State")
    public void setState(String state) {
        State = state;
    }

    public String getWelcomeCode() {
        return WelcomeCode;
    }
    @XmlElement(name = "WelcomeCode")
    public void setWelcomeCode(String welcomeCode) {
        WelcomeCode = welcomeCode;
    }

    // 加密消息体
    private String Encrypt;

    public String getEncrypt() {
        return Encrypt;
    }

    @XmlElement(name = "Encrypt")
    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }

    // 消息体XML字符串
    private String xmlStr;

    public String getXmlStr() {
        return xmlStr;
    }

    public void setXmlStr(String xmlStr) {
        this.xmlStr = xmlStr;
    }

    @Override
    public String toString() {
        return "QyCustVo{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                ", Event='" + Event + '\'' +
                ", ChangeType='" + ChangeType + '\'' +
                ", UserID='" + UserID + '\'' +
                ", ExternalUserID='" + ExternalUserID + '\'' +
                ", State='" + State + '\'' +
                ", WelcomeCode='" + WelcomeCode + '\'' +
                ", Encrypt='" + Encrypt + '\'' +
                ", xmlStr='" + xmlStr + '\'' +
                '}';
    }
}
