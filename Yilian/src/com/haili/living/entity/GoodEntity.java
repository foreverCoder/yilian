package com.haili.living.entity;

/**
 * 商品实体 一个实体有600行，可见接口字段多么的丰富多彩 Created by liteng on 2015/4/21.
 */
public class GoodEntity {



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
	private String goods_marketprice;// 商品超市价格
	private String goods_id;// 商品ID
	private String goods_image_url;// 商品图片地址
	private String evaluation_count;
	private String goods_name;
	private String evaluation_good_star;// 评价星级
	private String goods_image;
	private String goods_salenum;
	private boolean xianshi_flag;

	/*
	 * 以下是 生活馆页面接口返回的实体字段，太多无用字段，没做任何优化处理，也是醉了
	 * 
	 * /** goods_collect : 0 goods_zzbz : null life_type : 0 goods_storage : 123
	 * goods_salenum : 0 brand_name : goods_commonid : 14 goods_selltime : 0
	 * goods_edittime : 1425286290 store_name : 7f8e5473751f6d3b9986 goods_lock
	 * : 0 goods_marketprice : 12.00 brand_id : 0 store_id : 9 goods_state : 1
	 * goods_shicaidetail : null goods_stateremark : null goods_attr : N;
	 * spec_value : N; plateid_bottom : 0 goods_body : <img src=
	 * "http://localhost/eathaili/data/upload/shop/store/goods/9/9_04762708400105268_1280.jpg"
	 * alt="image" /><img src=
	 * "http://localhost/eathaili/data/upload/shop/store/goods/9/9_04762708560323065_1280.jpg"
	 * alt="image" /> goods_id : 14 goods_costprice : 0.00 goods_discount : 100
	 * gc_id : 1217 goods_name :
	 * 751f6d3b99864e2d554654c17c7b6d4b8bd56dfb52a0554654c1ff12 goods_verify : 1
	 * goods_serial : goods_click : 8 transport_id : 0 areaid_1 : 0 areaid_2 : 0
	 * goods_price : 12.00 endtime_life : 00:05 areaid_3 : 0 farm_type : null
	 * goods_verifyremark : null goods_spec : N; goods_xts : null
	 * evaluation_good_star : 5 goods_specname : transport_title :
	 * evaluation_count : 0 goods_shicai : null goods_addtime : 1425286262
	 * goods_image : 9_04762708560323065.jpg goods_vat : 0 type_id : 0 spec_name
	 * : N; goods_freight : 0.00 plateid_top : 0 goods_commend : 1 goods_stcids
	 * : ,0, gc_name : 751f6d3b9986 &gt;5f0052067c7b1 starttime_life : 00:05
	 * color_id : 0 goods_jingle :
	 */
	private String goods_collect;
	private String goods_zzbz;
	private String life_type;
	private String goods_storage;
	private String brand_name;
	private String goods_commonid;
	private String goods_selltime;
	private String goods_edittime;
	private String store_name;
	private String goods_lock;
	private String brand_id;
	private String store_id;
	private String goods_state;
	private String goods_shicaidetail;
	private String goods_stateremark;
	private String goods_attr;
	private String spec_value;
	private String plateid_bottom;
	private String goods_body;
	private String goods_costprice;
	private String goods_discount;
	private String gc_id;
	private String goods_verify;
	private String goods_serial;
	private String goods_click;
	private String transport_id;
	private String areaid_1;
	private String areaid_2;
	private String endtime_life;
	private String areaid_3;
	private String farm_type;
	private String goods_verifyremark;
	private String goods_spec;
	private String goods_xts;
	private String goods_specname;
	private String transport_title;
	private String goods_shicai;
	private String goods_addtime;
	private String goods_vat;
	private String type_id;
	private String spec_name;
	private String goods_freight;
	private String plateid_top;
	private String goods_commend;
	private String goods_stcids;
	private String gc_name;
	private String starttime_life;
	private String color_id;
	private String goods_jingle;

	public String getGoods_collect() {
		return goods_collect;
	}

	public void setGoods_collect(String goods_collect) {
		this.goods_collect = goods_collect;
	}

	public String getGoods_zzbz() {
		return goods_zzbz;
	}

	public void setGoods_zzbz(String goods_zzbz) {
		this.goods_zzbz = goods_zzbz;
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

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getGoods_commonid() {
		return goods_commonid;
	}

	public void setGoods_commonid(String goods_commonid) {
		this.goods_commonid = goods_commonid;
	}

	public String getGoods_selltime() {
		return goods_selltime;
	}

	public void setGoods_selltime(String goods_selltime) {
		this.goods_selltime = goods_selltime;
	}

	public String getGoods_edittime() {
		return goods_edittime;
	}

	public void setGoods_edittime(String goods_edittime) {
		this.goods_edittime = goods_edittime;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getGoods_lock() {
		return goods_lock;
	}

	public void setGoods_lock(String goods_lock) {
		this.goods_lock = goods_lock;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getGoods_state() {
		return goods_state;
	}

	public void setGoods_state(String goods_state) {
		this.goods_state = goods_state;
	}

	public String getGoods_shicaidetail() {
		return goods_shicaidetail;
	}

	public void setGoods_shicaidetail(String goods_shicaidetail) {
		this.goods_shicaidetail = goods_shicaidetail;
	}

	public String getGoods_stateremark() {
		return goods_stateremark;
	}

	public void setGoods_stateremark(String goods_stateremark) {
		this.goods_stateremark = goods_stateremark;
	}

	public String getGoods_attr() {
		return goods_attr;
	}

	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
	}

	public String getSpec_value() {
		return spec_value;
	}

	public void setSpec_value(String spec_value) {
		this.spec_value = spec_value;
	}

	public String getPlateid_bottom() {
		return plateid_bottom;
	}

	public void setPlateid_bottom(String plateid_bottom) {
		this.plateid_bottom = plateid_bottom;
	}

	public String getGoods_body() {
		return goods_body;
	}

	public void setGoods_body(String goods_body) {
		this.goods_body = goods_body;
	}

	public String getGoods_costprice() {
		return goods_costprice;
	}

	public void setGoods_costprice(String goods_costprice) {
		this.goods_costprice = goods_costprice;
	}

	public String getGoods_discount() {
		return goods_discount;
	}

	public void setGoods_discount(String goods_discount) {
		this.goods_discount = goods_discount;
	}

	public String getGc_id() {
		return gc_id;
	}

	public void setGc_id(String gc_id) {
		this.gc_id = gc_id;
	}

	public String getGoods_verify() {
		return goods_verify;
	}

	public void setGoods_verify(String goods_verify) {
		this.goods_verify = goods_verify;
	}

	public String getGoods_serial() {
		return goods_serial;
	}

	public void setGoods_serial(String goods_serial) {
		this.goods_serial = goods_serial;
	}

	public String getGoods_click() {
		return goods_click;
	}

	public void setGoods_click(String goods_click) {
		this.goods_click = goods_click;
	}

	public String getTransport_id() {
		return transport_id;
	}

	public void setTransport_id(String transport_id) {
		this.transport_id = transport_id;
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

	public String getEndtime_life() {
		return endtime_life;
	}

	public void setEndtime_life(String endtime_life) {
		this.endtime_life = endtime_life;
	}

	public String getAreaid_3() {
		return areaid_3;
	}

	public void setAreaid_3(String areaid_3) {
		this.areaid_3 = areaid_3;
	}

	public String getFarm_type() {
		return farm_type;
	}

	public void setFarm_type(String farm_type) {
		this.farm_type = farm_type;
	}

	public String getGoods_verifyremark() {
		return goods_verifyremark;
	}

	public void setGoods_verifyremark(String goods_verifyremark) {
		this.goods_verifyremark = goods_verifyremark;
	}

	public String getGoods_spec() {
		return goods_spec;
	}

	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}

	public String getGoods_xts() {
		return goods_xts;
	}

	public void setGoods_xts(String goods_xts) {
		this.goods_xts = goods_xts;
	}

	public String getGoods_specname() {
		return goods_specname;
	}

	public void setGoods_specname(String goods_specname) {
		this.goods_specname = goods_specname;
	}

	public String getTransport_title() {
		return transport_title;
	}

	public void setTransport_title(String transport_title) {
		this.transport_title = transport_title;
	}

	public String getGoods_shicai() {
		return goods_shicai;
	}

	public void setGoods_shicai(String goods_shicai) {
		this.goods_shicai = goods_shicai;
	}

	public String getGoods_addtime() {
		return goods_addtime;
	}

	public void setGoods_addtime(String goods_addtime) {
		this.goods_addtime = goods_addtime;
	}

	public String getGoods_vat() {
		return goods_vat;
	}

	public void setGoods_vat(String goods_vat) {
		this.goods_vat = goods_vat;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getSpec_name() {
		return spec_name;
	}

	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}

	public String getGoods_freight() {
		return goods_freight;
	}

	public void setGoods_freight(String goods_freight) {
		this.goods_freight = goods_freight;
	}

	public String getPlateid_top() {
		return plateid_top;
	}

	public void setPlateid_top(String plateid_top) {
		this.plateid_top = plateid_top;
	}

	public String getGoods_commend() {
		return goods_commend;
	}

	public void setGoods_commend(String goods_commend) {
		this.goods_commend = goods_commend;
	}

	public String getGoods_stcids() {
		return goods_stcids;
	}

	public void setGoods_stcids(String goods_stcids) {
		this.goods_stcids = goods_stcids;
	}

	public String getGc_name() {
		return gc_name;
	}

	public void setGc_name(String gc_name) {
		this.gc_name = gc_name;
	}

	public String getStarttime_life() {
		return starttime_life;
	}

	public void setStarttime_life(String starttime_life) {
		this.starttime_life = starttime_life;
	}

	public String getColor_id() {
		return color_id;
	}

	public void setColor_id(String color_id) {
		this.color_id = color_id;
	}

	public String getGoods_jingle() {
		return goods_jingle;
	}

	public void setGoods_jingle(String goods_jingle) {
		this.goods_jingle = goods_jingle;
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
