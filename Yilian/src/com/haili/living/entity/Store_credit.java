package com.haili.living.entity;

import java.io.Serializable;

public class Store_credit implements Serializable{
	public Store_credit() {
	}
	private Store_desccredit store_desccredit;//描述相符
	private Store_desccredit store_servicecredit;//服务态度
	private Store_desccredit store_deliverycredit;//发货速度
	public Store_desccredit getStore_desccredit() { 
		return store_desccredit;
	}
	public void setStore_desccredit(Store_desccredit store_desccredit) {
		this.store_desccredit = store_desccredit;
	}
	public Store_desccredit getStore_servicecredit() {
		return store_servicecredit;
	}
	public void setStore_servicecredit(Store_desccredit store_servicecredit) {
		this.store_servicecredit = store_servicecredit;
	}
	public Store_desccredit getStore_deliverycredit() {
		return store_deliverycredit;
	}
	public void setStore_deliverycredit(Store_desccredit store_deliverycredit) {
		this.store_deliverycredit = store_deliverycredit;
	}
	
}
