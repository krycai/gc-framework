package com.allen.sys.model.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_dict_entry")
public class SysDictEntry {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 业务字典子选项
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 字典项编码
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 字典项名称
     */
    @Column(name = "dict_name")
    private String dictName;

    /**
     * 状态（1使用中/0已废弃）
     */
    private Integer status;

    /**
     * 排序编码
     */
    private Integer sort;

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
     * 获取业务字典子选项
     *
     * @return type_code - 业务字典子选项
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置业务字典子选项
     *
     * @param typeCode 业务字典子选项
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取字典项编码
     *
     * @return dict_code - 字典项编码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置字典项编码
     *
     * @param dictCode 字典项编码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取字典项名称
     *
     * @return dict_name - 字典项名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置字典项名称
     *
     * @param dictName 字典项名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取状态（1使用中/0已废弃）
     *
     * @return status - 状态（1使用中/0已废弃）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（1使用中/0已废弃）
     *
     * @param status 状态（1使用中/0已废弃）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取排序编码
     *
     * @return sort - 排序编码
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序编码
     *
     * @param sort 排序编码
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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