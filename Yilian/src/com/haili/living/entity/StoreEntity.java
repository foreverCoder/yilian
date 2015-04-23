package com.haili.living.entity;

/**
 * 生活馆实体 Created by liteng on 2015/4/21.
 */
public class StoreEntity {

	/**
	 * 
	 * "store_id": "9", "store_name": "美味生活馆", "store_address": "",
	 * "store_label": null, "store_tel": "", "street": "黎阳家苑",
	 * "store_workingtime": null
	 * 
	 * maplnglat : 117.18365190,31.81160903 store_id : 9 store_name : 美味生活馆
	 * type_id : 2
	 */
	private String maplnglat;// 地图经纬度
	private String store_id;// 生活馆ID
	private String store_name;// 生活馆名称
	private String type_id;// 生活馆店铺类型 1商城 2，生活馆；3农庄
	private String store_address;
	private String store_label;
	private String store_tel;
	private String street;
	private String store_workingtime;

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	public String getStore_label() {
		return store_label;
	}

	public void setStore_label(String store_label) {
		this.store_label = store_label;
	}

	public String getStore_tel() {
		return store_tel;
	}

	public void setStore_tel(String store_tel) {
		this.store_tel = store_tel;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStore_workingtime() {
		return store_workingtime;
	}

	public void setStore_workingtime(String store_workingtime) {
		this.store_workingtime = store_workingtime;
	}

	public void setMaplnglat(String maplnglat) {
		this.maplnglat = maplnglat;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getMaplnglat() {
		return maplnglat;
	}

	public String getStore_id() {
		return store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public String getType_id() {
		return type_id;
	}
}
