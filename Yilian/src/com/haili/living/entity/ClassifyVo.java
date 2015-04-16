package com.haili.living.entity;

import java.io.Serializable;

public class ClassifyVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2412251284463194540L;
    private String classifyName;
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public ClassifyVo(String classifyName) {
		super();
		this.classifyName = classifyName;
	}
    
}
