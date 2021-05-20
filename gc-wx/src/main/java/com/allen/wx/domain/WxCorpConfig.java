package com.allen.wx.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "wx_corp_config")
public class WxCorpConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 企业代号
     */
    @Column(name = "corp_code")
    private String corpCode;

    /**
     * 企业名字
     */
    @Column(name = "corp_name")
    private String corpName;

    /**
     * 企业id
     */
    @Column(name = "corp_id")
    private String corpId;

    /**
     * 通讯录secret
     */
    @Column(name = "contacts_secret")
    private String contactsSecret;

    /**
     * 外部联系人secret
     */
    @Column(name = "external_secret")
    private String externalSecret;

    /**
     * 会话存档secret
     */
    @Column(name = "session_secret")
    private String sessionSecret;

    /**
     * 消息是否加解密
     */
    @Column(name = "message_encrypt")
    private Boolean messageEncrypt;

    /**
     * 消息加解密token
     */
    private String token;

    /**
     * 消息加解密钥匙
     */
    @Column(name = "encoding_aes_key")
    private String encodingAesKey;

    /**
     * private_key 会话存档私钥
     */
    @Column(name = "private_key")
    private String privateKey;

    /**
     * private_key 会话存档公钥
     */
    @Column(name = "public_key")
    private String publicKey;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加人
     */
    @Column(name = "op_code")
    private String opCode;

    /**
     * 添加人
     */
    @Column(name = "op_name")
    private String opName;

    /**
     * 添加时间
     */
    @Column(name = "op_time")
    private Date opTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取企业代号
     *
     * @return corp_code - 企业代号
     */
    public String getCorpCode() {
        return corpCode;
    }

    /**
     * 设置企业代号
     *
     * @param corpCode 企业代号
     */
    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    /**
     * 获取企业名字
     *
     * @return corp_name - 企业名字
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 设置企业名字
     *
     * @param corpName 企业名字
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * 获取企业id
     *
     * @return corp_id - 企业id
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 设置企业id
     *
     * @param corpId 企业id
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    /**
     * 获取通讯录secret
     *
     * @return contacts_secret - 通讯录secret
     */
    public String getContactsSecret() {
        return contactsSecret;
    }

    /**
     * 设置通讯录secret
     *
     * @param contactsSecret 通讯录secret
     */
    public void setContactsSecret(String contactsSecret) {
        this.contactsSecret = contactsSecret;
    }

    /**
     * 获取外部联系人secret
     *
     * @return external_secret - 外部联系人secret
     */
    public String getExternalSecret() {
        return externalSecret;
    }

    /**
     * 设置外部联系人secret
     *
     * @param externalSecret 外部联系人secret
     */
    public void setExternalSecret(String externalSecret) {
        this.externalSecret = externalSecret;
    }

    /**
     * 获取消息是否加解密
     *
     * @return message_encrypt - 消息是否加解密
     */
    public Boolean getMessageEncrypt() {
        return messageEncrypt;
    }

    /**
     * 设置消息是否加解密
     *
     * @param messageEncrypt 消息是否加解密
     */
    public void setMessageEncrypt(Boolean messageEncrypt) {
        this.messageEncrypt = messageEncrypt;
    }

    /**
     * 获取消息加解密token
     *
     * @return token - 消息加解密token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置消息加解密token
     *
     * @param token 消息加解密token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取消息加解密钥匙
     *
     * @return encoding_aes_key - 消息加解密钥匙
     */
    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    /**
     * 设置消息加解密钥匙
     *
     * @param encodingAesKey 消息加解密钥匙
     */
    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取添加人
     *
     * @return op_code - 添加人
     */
    public String getOpCode() {
        return opCode;
    }

    /**
     * 设置添加人
     *
     * @param opCode 添加人
     */
    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    /**
     * 获取添加人
     *
     * @return op_name - 添加人
     */
    public String getOpName() {
        return opName;
    }

    /**
     * 设置添加人
     *
     * @param opName 添加人
     */
    public void setOpName(String opName) {
        this.opName = opName;
    }

    /**
     * 获取添加时间
     *
     * @return op_time - 添加时间
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * 设置添加时间
     *
     * @param opTime 添加时间
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getSessionSecret() {
        return sessionSecret;
    }

    public void setSessionSecret(String sessionSecret) {
        this.sessionSecret = sessionSecret;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}