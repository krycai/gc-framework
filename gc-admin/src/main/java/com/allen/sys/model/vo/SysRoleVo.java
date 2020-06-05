package com.allen.sys.model.vo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色Entity
 *
 * @author Guoqing
 */
public class SysRoleVo  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
	private Integer id;

	/**
     * 名称
     */
    private String name;
    
    /**
     * 角色代码
     */
    private String code;
    /**
     * 是否可用
     */
    private Boolean enabled;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 菜单列表
     */
    private List<SysRoleMenu> roleMenus = new ArrayList<>();

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

	@Length(min = 1, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Length(min = 0, max = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public List<SysRoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(List<SysRoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }
}
