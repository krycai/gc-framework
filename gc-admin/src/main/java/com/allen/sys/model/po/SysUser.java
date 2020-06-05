package com.allen.sys.model.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_user")
public class SysUser {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 优先权
     */
    private Integer level;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 最后登陆IP
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @Column(name = "login_date")
    private Date loginDate;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 是否系统角色授权(0 否 1 是)
     */
    @Column(name = "is_role_assigned")
    private Boolean isRoleAssigned;

    /**
     * 是否资源角色授权,(0 否 1 是)
     */
    @Column(name = "is_resource_assigned")
    private Boolean isResourceAssigned;

    /**
     * 删除标记
     */
    @Column(name = "del_flag")
    private Boolean delFlag;

    /**
     * 职务
     */
    private String duty;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 是否是PKI用户（1：是，0：否）
     */
    @Column(name = "is_pki_user")
    private Boolean isPkiUser;

    /**
     * 电脑mac地址
     */
    private String mac;

    /**
     * 是否超级管理员
     */
    @Column(name = "is_admin")
    private Boolean isAdmin;

    /**
     * 皮肤
     */
    private String skin;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建者
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取优先权
     *
     * @return level - 优先权
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置优先权
     *
     * @param level 优先权
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取手机
     *
     * @return mobile - 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     *
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取用户头像
     *
     * @return photo - 用户头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置用户头像
     *
     * @param photo 用户头像
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取最后登陆IP
     *
     * @return login_ip - 最后登陆IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置最后登陆IP
     *
     * @param loginIp 最后登陆IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取最后登陆时间
     *
     * @return login_date - 最后登陆时间
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 设置最后登陆时间
     *
     * @param loginDate 最后登陆时间
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 获取是否启用
     *
     * @return enabled - 是否启用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否启用
     *
     * @param enabled 是否启用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取是否系统角色授权(0 否 1 是)
     *
     * @return is_role_assigned - 是否系统角色授权(0 否 1 是)
     */
    public Boolean getIsRoleAssigned() {
        return isRoleAssigned;
    }

    /**
     * 设置是否系统角色授权(0 否 1 是)
     *
     * @param isRoleAssigned 是否系统角色授权(0 否 1 是)
     */
    public void setIsRoleAssigned(Boolean isRoleAssigned) {
        this.isRoleAssigned = isRoleAssigned;
    }

    /**
     * 获取是否资源角色授权,(0 否 1 是)
     *
     * @return is_resource_assigned - 是否资源角色授权,(0 否 1 是)
     */
    public Boolean getIsResourceAssigned() {
        return isResourceAssigned;
    }

    /**
     * 设置是否资源角色授权,(0 否 1 是)
     *
     * @param isResourceAssigned 是否资源角色授权,(0 否 1 是)
     */
    public void setIsResourceAssigned(Boolean isResourceAssigned) {
        this.isResourceAssigned = isResourceAssigned;
    }

    /**
     * 获取删除标记
     *
     * @return del_flag - 删除标记
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记
     *
     * @param delFlag 删除标记
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取职务
     *
     * @return duty - 职务
     */
    public String getDuty() {
        return duty;
    }

    /**
     * 设置职务
     *
     * @param duty 职务
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取身份证
     *
     * @return id_card - 身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证
     *
     * @param idCard 身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取是否是PKI用户（1：是，0：否）
     *
     * @return is_pki_user - 是否是PKI用户（1：是，0：否）
     */
    public Boolean getIsPkiUser() {
        return isPkiUser;
    }

    /**
     * 设置是否是PKI用户（1：是，0：否）
     *
     * @param isPkiUser 是否是PKI用户（1：是，0：否）
     */
    public void setIsPkiUser(Boolean isPkiUser) {
        this.isPkiUser = isPkiUser;
    }

    /**
     * 获取电脑mac地址
     *
     * @return mac - 电脑mac地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置电脑mac地址
     *
     * @param mac 电脑mac地址
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 获取是否超级管理员
     *
     * @return is_admin - 是否超级管理员
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置是否超级管理员
     *
     * @param isAdmin 是否超级管理员
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 获取皮肤
     *
     * @return skin - 皮肤
     */
    public String getSkin() {
        return skin;
    }

    /**
     * 设置皮肤
     *
     * @param skin 皮肤
     */
    public void setSkin(String skin) {
        this.skin = skin;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建者
     *
     * @return create_user - 创建者
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建者
     *
     * @param createUser 创建者
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    /**
     * 获取更新者
     *
     * @return update_user - 更新者
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新者
     *
     * @param updateUser 更新者
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}