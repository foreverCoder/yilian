package com.haili.living.entity;

import java.io.Serializable;

public class ClassifyVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8509402566092059399L;
	/**
	 * 分类实体类
	 */
    private String gc_name;
    private int type_id;
    private int gc_sort;
    private int gc_id;
    private int gc_parent_id;
	public String getGc_name() {
		return gc_name;
	}
	public void setGc_name(String gc_name) {
		this.gc_name = gc_name;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getGc_sort() {
		return gc_sort;
	}
	public void setGc_sort(int gc_sort) {
		this.gc_sort = gc_sort;
	}
	public int getGc_id() {
		return gc_id;
	}
	public void setGc_id(int gc_id) {
		this.gc_id = gc_id;
	}
	public int getGc_parent_id() {
		return gc_parent_id;
	}
	public void setGc_parent_id(int gc_parent_id) {
		this.gc_parent_id = gc_parent_id;
	}
    
}
