package com.allen.sys.model.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dict_type")
public class SysDictType {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 业务字典编码
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 业务字典名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除标记
1：删除
0：未删除
     */
    @Column(name = "del_flag")
    private Boolean delFlag;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取业务字典编码
     *
     * @return type_code - 业务字典编码
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置业务字典编码
     *
     * @param typeCode 业务字典编码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取业务字典名称
     *
     * @return type_name - 业务字典名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置业务字典名称
     *
     * @param typeName 业务字典名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    /**
     * 获取删除标记
1：删除
0：未删除
     *
     * @return del_flag - 删除标记
1：删除
0：未删除
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记
1：删除
0：未删除
     *
     * @param delFlag 删除标记
1：删除
0：未删除
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}