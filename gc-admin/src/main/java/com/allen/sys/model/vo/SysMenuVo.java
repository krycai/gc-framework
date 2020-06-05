package com.allen.sys.model.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单Entity
 *
 * @author Guoqing
 */
public class SysMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 父级编号
     */
    private Integer parentId;

    private String parentName;

    /**
     * 所有父级编号
     */
    private String parentIds;
    /**
     * 名称
     */
    private String text;
    /**
     * 链接
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 页面打开方式
     */
    private String targetType;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否在菜单中显示（1：显示；0：不显示）
     */
    private boolean isShow;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 是否是叶子节点
     */
    private Boolean leaf = true;
    /**
     * 子节点
     */
    private List<SysMenuVo> children = new ArrayList<>();

    private Date createTime;

    private Date opTime;

    private String delFlag;

    public SysMenuVo() {
        super();
    }

    /**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Length(min = 1, max = 100)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Length(min = 0, max = 2000)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Length(min = 0, max = 100)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	@NotNull
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean getShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    @Length(min = 0, max = 200)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Length(min = 0, max = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public List<SysMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuVo> children) {
        this.children = children;
    }

    /**
     * 添加子节点
     *
     * @param node 菜单节点
     */
    public void addChild(SysMenuVo node) {
        this.children.add(node);
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public boolean isShow() {
        return isShow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "SysMenuVo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", parentIds='" + parentIds + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", targetType='" + targetType + '\'' +
                ", sort=" + sort +
                ", isShow=" + isShow +
                ", permission='" + permission + '\'' +
                ", remarks='" + remarks + '\'' +
                ", leaf=" + leaf +
                ", children=" + children +
                ", createTime=" + createTime +
                ", opTime=" + opTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}