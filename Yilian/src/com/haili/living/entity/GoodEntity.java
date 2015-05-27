package com.haili.living.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体 一个实体有600行，可见接口字段多么的丰富多彩 Created by liteng on 2015/4/21.
 */
public class GoodEntity implements Serializable{

	public GoodEntity() {
	}

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
	private String goods_jingle; //商品广告词
	private String goods_serial;//商家编号.
	private String transport_id;//运费模板
	private String goods_discount;// 折扣
	private String goods_costprice;//成本价
	
	private String transport_title;//运费模板名称
//	private String goods_commend;//商品推荐 1是，0否，默认为0
	private String goods_freight ;//运费 0为免运费
	private String goods_vat;//是否开具增值税发票 1是，0否
	
	private String areaid_1;//一级地区id
	private String areaid_2 ;//二级地区id
	private String areaid_3 ;//三级地区id
	private String goods_stcids ;//店铺分类id 首尾用,隔开
	private String plateid_top  ;//顶部关联板式
	private String plateid_bottom  ;//底部关联板式
	private String goods_specname  ;//规格名称序列化（下标为规格id）
	private String goods_spec  ;//规格名称序列化（下标为规格id）
	private Date goods_selltime  ;// 上架时间
	private Date goods_addtime  ;// 商品添加时间
	private String goods_lock  ;// 商品锁定 0未锁，1已锁
	private String goods_verify  ;// 商品审核 1通过，0未通过，10审核中
	private String goods_verifyremark  ;// 审核失败原因
	private String goods_stateremark   ;//  违规原因
	private String goods_state   ;// 商品状态 0下架，1正常，10违规（禁售）
	private String goods_body   ;//  商品内容
	private String goods_attr   ;//  商品属性
	private String type_id    ;// 类型id
	
	private String spec_value      ;//  规格值
	private String spec_name      ;//  规格名称
	private String endtime_life      ;//  生活馆中类别商品，开卖时间
	private String starttime_life      ;// 生活馆中类别商品，结束时间
	private String gc_name        ;//商品分类
	
	private Date goods_edittime;
	private String color_id;
	private String goods_shicai;
	private String goods_zzbz;
	private String goods_shicaidetail;
	private String goods_xts;
	
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
    private String brand_id ; // 品牌id
    private String brand_name  ; // 品牌名称
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

	public String getGoods_spec() {
		return goods_spec;
	}

	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}

	public void setGoods_commend(String goods_commend) {
		this.goods_commend = goods_commend;
	}

	public String getGc_id() {
		return gc_id;
	}

	public String getGoods_serial() {
		return goods_serial;
	}

	public void setGoods_serial(String goods_serial) {
		this.goods_serial = goods_serial;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
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

	public String getTransport_id() {
		return transport_id;
	}

	public void setTransport_id(String transport_id) {
		this.transport_id = transport_id;
	}

	public String getGoods_discount() {
		return goods_discount;
	}

	public void setGoods_discount(String goods_discount) {
		this.goods_discount = goods_discount;
	}

	public String getGoods_costprice() {
		return goods_costprice;
	}

	public void setGoods_costprice(String goods_costprice) {
		this.goods_costprice = goods_costprice;
	}

	public String getTransport_title() {
		return transport_title;
	}

	public void setTransport_title(String transport_title) {
		this.transport_title = transport_title;
	}

	public String getGoods_freight() {
		return goods_freight;
	}

	public Date getGoods_edittime() {
		return goods_edittime;
	}

	public void setGoods_edittime(Date goods_edittime) {
		this.goods_edittime = goods_edittime;
	}

	public void setGoods_freight(String goods_freight) {
		this.goods_freight = goods_freight;
	}

	public String getGoods_vat() {
		return goods_vat;
	}

	public String getGoods_zzbz() {
		return goods_zzbz;
	}

	public void setGoods_zzbz(String goods_zzbz) {
		this.goods_zzbz = goods_zzbz;
	}

	public void setGoods_vat(String goods_vat) {
		this.goods_vat = goods_vat;
	}

	public String getAreaid_1() {
		return areaid_1;
	}

	public void setAreaid_1(String areaid_1) {
		this.areaid_1 = areaid_1;
	}

	public String getAreaid_2() {
		return areaid_2;
	}

	public void setAreaid_2(String areaid_2) {
		this.areaid_2 = areaid_2;
	}

	public String getAreaid_3() {
		return areaid_3;
	}

	public void setAreaid_3(String areaid_3) {
		this.areaid_3 = areaid_3;
	}

	public String getGoods_stcids() {
		return goods_stcids;
	}

	public void setGoods_stcids(String goods_stcids) {
		this.goods_stcids = goods_stcids;
	}

	public String getPlateid_top() {
		return plateid_top;
	}

	public void setPlateid_top(String plateid_top) {
		this.plateid_top = plateid_top;
	}

	public String getPlateid_bottom() {
		return plateid_bottom;
	}

	public void setPlateid_bottom(String plateid_bottom) {
		this.plateid_bottom = plateid_bottom;
	}

	public String getGoods_specname() {
		return goods_specname;
	}

	public void setGoods_specname(String goods_specname) {
		this.goods_specname = goods_specname;
	}

	public Date getGoods_selltime() {
		return goods_selltime;
	}

	public String getColor_id() {
		return color_id;
	}

	public void setColor_id(String color_id) {
		this.color_id = color_id;
	}

	public void setGoods_selltime(Date goods_selltime) {
		this.goods_selltime = goods_selltime;
	}

	public Date getGoods_addtime() {
		return goods_addtime;
	}

	public void setGoods_addtime(Date goods_addtime) {
		this.goods_addtime = goods_addtime;
	}

	public String getGoods_lock() {
		return goods_lock;
	}

	public void setGoods_lock(String goods_lock) {
		this.goods_lock = goods_lock;
	}

	public String getGoods_verify() {
		return goods_verify;
	}

	public void setGoods_verify(String goods_verify) {
		this.goods_verify = goods_verify;
	}

	public String getGoods_verifyremark() {
		return goods_verifyremark;
	}

	public String getGoods_shicai() {
		return goods_shicai;
	}

	public void setGoods_shicai(String goods_shicai) {
		this.goods_shicai = goods_shicai;
	}

	public String getGoods_xts() {
		return goods_xts;
	}

	public void setGoods_xts(String goods_xts) {
		this.goods_xts = goods_xts;
	}

	public void setGoods_verifyremark(String goods_verifyremark) {
		this.goods_verifyremark = goods_verifyremark;
	}

	public String getGoods_stateremark() {
		return goods_stateremark;
	}

	public String getGoods_shicaidetail() {
		return goods_shicaidetail;
	}

	public void setGoods_shicaidetail(String goods_shicaidetail) {
		this.goods_shicaidetail = goods_shicaidetail;
	}

	public void setGoods_stateremark(String goods_stateremark) {
		this.goods_stateremark = goods_stateremark;
	}

	public String getGoods_state() {
		return goods_state;
	}

	public void setGoods_state(String goods_state) {
		this.goods_state = goods_state;
	}

	public String getGoods_body() {
		return goods_body;
	}

	public void setGoods_body(String goods_body) {
		this.goods_body = goods_body;
	}

	public String getGoods_attr() {
		return goods_attr;
	}

	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getSpec_value() {
		return spec_value;
	}

	public void setSpec_value(String spec_value) {
		this.spec_value = spec_value;
	}

	public String getSpec_name() {
		return spec_name;
	}

	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}


	public String getEndtime_life() {
		return endtime_life;
	}

	public void setEndtime_life(String endtime_life) {
		this.endtime_life = endtime_life;
	}

	public String getStarttime_life() {
		return starttime_life;
	}

	public void setStarttime_life(String starttime_life) {
		this.starttime_life = starttime_life;
	}

	public String getGc_name() {
		return gc_name;
	}

	public void setGc_name(String gc_name) {
		this.gc_name = gc_name;
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

	public String getGoods_jingle() {
		return goods_jingle;
	}

	public void setGoods_jingle(String goods_jingle) {
		this.goods_jingle = goods_jingle;
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
