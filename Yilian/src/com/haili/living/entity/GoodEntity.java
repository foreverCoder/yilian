package com.haili.living.entity;

import java.io.Serializable;

/**
 * 商品实体 一个实体有600行，可见接口字段多么的丰富多彩 Created by liteng on 2015/4/21.
 */
public class GoodEntity implements Serializable{

	/**
	 * 商品分类列表返回字段 goods_price : 0.01 group_flag : false goods_marketprice :
	 * 10.00 goods_id : 97 goods_image_url :
	 * http://www.zq2014.com/haili/data/upload
	 * /shop/store/goods/21/21_04828525927007214_360.jpg evaluation_count : 0
	 * goods_name : 产地直供 evaluation_good_star : 5 goods_image :
	 * 21_04828525927007214.jpg goods_salenum : 1 xianshi_flag : false
	 */
	private String goods_price;// 商品价格
	private boolean group_flag;
	private String goods_marketprice;// 市场价 
	private String goods_id;// 商品ID
	private String goods_image_url;// 商品图片地址
	private String evaluation_count;//评价数
	private String goods_name;//商品名称 
	private String evaluation_good_star;// 评价星级
	private String goods_image;
	private String goods_salenum;//销售数量
	private boolean xianshi_flag;
	
	 /**
	  * 以下是生活馆搜索商品的接口
     * gc_id : 1217
     * goods_price : 11.00
     * farm_type : null
     * goods_commonid : 20
     * goods_id : 43
     * store_id : 11
     * goods_name : 测试生活馆2
     * goods_image : http://www.zq2014.com/haili/data/upload/shop/store/goods/11/11_04805073382771374_360.jpg
     * store_name : 生活馆大学城店
     */
    private String gc_id;//商品分类id
    private String farm_type;//农庄商品的类型，1生成验证码，2为农作物，3为养殖，4为发物流
    private String goods_commonid;//商品公共表id
    private String store_id;//商铺ID 
    private String store_name;//商铺名

    /*
     *指定商品信息的多出字段
     */
    private String goods_collect;//收藏数量
    private String life_type;//生活馆类别,1为早市特卖,2为晚市特卖,3为餐饮外卖-早餐，4为餐饮外卖-午餐，5为餐饮外卖-晚餐
    private String goods_storage;//商品库存 
    private int goods_click;//商品点击数量 
    private String goods_commend;//商品推荐 1是，0否 默认0
    
	public String getGoods_collect() {
		return goods_collect;
	}

	public void setGoods_collect(String goods_collect) {
		this.goods_collect = goods_collect;
	}

	public String getLife_type() {
		return life_type;
	}

	public void setLife_type(String life_type) {
		this.life_type = life_type;
	}

	public String getGoods_storage() {
		return goods_storage;
	}

	public void setGoods_storage(String goods_storage) {
		this.goods_storage = goods_storage;
	}

	public int getGoods_click() {
		return goods_click;
	}

	public void setGoods_click(int goods_click) {
		this.goods_click = goods_click;
	}

	public String getGoods_commend() {
		return goods_commend;
	}

	public void setGoods_commend(String goods_commend) {
		this.goods_commend = goods_commend;
	}

	public String getGc_id() {
		return gc_id;
	}

	public void setGc_id(String gc_id) {
		this.gc_id = gc_id;
	}

	public String getFarm_type() {
		return farm_type;
	}

	public void setFarm_type(String farm_type) {
		this.farm_type = farm_type;
	}

	public String getGoods_commonid() {
		return goods_commonid;
	}

	public void setGoods_commonid(String goods_commonid) {
		this.goods_commonid = goods_commonid;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}

	public void setGroup_flag(boolean group_flag) {
		this.group_flag = group_flag;
	}

	public void setGoods_marketprice(String goods_marketprice) {
		this.goods_marketprice = goods_marketprice;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public void setGoods_image_url(String goods_image_url) {
		this.goods_image_url = goods_image_url;
	}

	public void setEvaluation_count(String evaluation_count) {
		this.evaluation_count = evaluation_count;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public void setEvaluation_good_star(String evaluation_good_star) {
		this.evaluation_good_star = evaluation_good_star;
	}

	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}

	public void setGoods_salenum(String goods_salenum) {
		this.goods_salenum = goods_salenum;
	}

	public void setXianshi_flag(boolean xianshi_flag) {
		this.xianshi_flag = xianshi_flag;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public boolean isGroup_flag() {
		return group_flag;
	}

	public String getGoods_marketprice() {
		return goods_marketprice;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public String getGoods_image_url() {
		return goods_image_url;
	}

	public String getEvaluation_count() {
		return evaluation_count;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public String getEvaluation_good_star() {
		return evaluation_good_star;
	}

	public String getGoods_image() {
		return goods_image;
	}

	public String getGoods_salenum() {
		return goods_salenum;
	}

	public boolean isXianshi_flag() {
		return xianshi_flag;
	}
}
