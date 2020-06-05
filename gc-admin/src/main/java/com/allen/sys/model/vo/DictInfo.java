package com.allen.sys.model.vo;

import java.io.Serializable;

/**
 * @author xuguocai 2020/6/1 10:43
 */
public class DictInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dictId;
	
	private String dictName;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	

}
