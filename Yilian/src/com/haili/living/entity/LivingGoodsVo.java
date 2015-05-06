package com.haili.living.entity;

import java.io.Serializable;

public class LivingGoodsVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4028772321115505899L;
	
	private String imgUrl;
    private String goodsName;
    private String goodsPrice;
    
	public LivingGoodsVo(String imgUrl, String goodsName, String goodsPrice) {
		super();
		this.imgUrl = imgUrl;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
    
    
    
}
