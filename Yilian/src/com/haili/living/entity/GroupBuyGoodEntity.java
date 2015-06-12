package com.haili.living.entity;

public class GroupBuyGoodEntity {
	 /**
     * groupbuy_price : 555252.00
     * area_id : 0
     * remark :
     * state : 20
     * goods_name : 1232541
     * recommended : 0
     * goods_url : http://www.zq2014.com/haili/shop/index.php?act=goods&op=index&goods_id=98
     * goods_price : 44444.00
     * goods_commonid : 65
     * groupbuy_intro :
     * end_time_text : 2015-07-31 00:00
     * store_name : 生活馆大学城店
     * start_time_text : 2015-04-27 11:39
     * groupbuy_id : 6
     * virtual_quantity : 0
     * upper_limit : 0
     * groupbuy_name : 453224324
     * store_id : 11
     * groupbuy_rebate : 124.93
     * buy_quantity : 0
     * groupbuy_image : http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834499897549680_mid.jpg
     * groupbuy_image1 : http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834499897549680_mid.jpg
     * buyer_count : 0
     * class_id : 0
     * end_time : 1438272000
     * views : 1
     * goods_id : 98
     * start_time : 1430105940
     */
    private String groupbuy_price;//团购价格
    private String area_id;// 团购地区编号
    private String remark;// 备注
    private String state;//团购状态 1.未发布 2.已取消 3.进行中 4.已完成 5.已结束
    private String goods_name;// 商品名称
    private String recommended;//是否推荐 0.未推荐 1.已推荐
    private String goods_url;// 商品链接 
    private String goods_price;//商品原价
    private String goods_commonid;//  商品公共表ID 
    private String groupbuy_intro;//本团介绍
    private String end_time_text;//  结束时间
    private String store_name;// 店铺名称
    private String start_time_text;//开始时间
    private String groupbuy_id;// 团购ID
    private String virtual_quantity;//虚拟购买数量
    private String upper_limit;// 购买上限
    private String groupbuy_name;// 活动名称
    private String store_id;//店铺ID
    private String groupbuy_rebate;// 折扣
    private String buy_quantity;// 购买数量
    private String groupbuy_image;// 团购图片
    private String groupbuy_image1;// 团购图片1
    private String buyer_count;//已购买人数
    private String class_id;//  团购类别编号
    private String end_time;//结束时间
    private String views;//查看次数
    private String goods_id;//商品ID
    private String start_time;// 开始时间

    public void setGroupbuy_price(String groupbuy_price) {
        this.groupbuy_price = groupbuy_price;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public void setGoods_commonid(String goods_commonid) {
        this.goods_commonid = goods_commonid;
    }

    public void setGroupbuy_intro(String groupbuy_intro) {
        this.groupbuy_intro = groupbuy_intro;
    }

    public void setEnd_time_text(String end_time_text) {
        this.end_time_text = end_time_text;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setStart_time_text(String start_time_text) {
        this.start_time_text = start_time_text;
    }

    public void setGroupbuy_id(String groupbuy_id) {
        this.groupbuy_id = groupbuy_id;
    }

    public void setVirtual_quantity(String virtual_quantity) {
        this.virtual_quantity = virtual_quantity;
    }

    public void setUpper_limit(String upper_limit) {
        this.upper_limit = upper_limit;
    }

    public void setGroupbuy_name(String groupbuy_name) {
        this.groupbuy_name = groupbuy_name;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public void setGroupbuy_rebate(String groupbuy_rebate) {
        this.groupbuy_rebate = groupbuy_rebate;
    }

    public void setBuy_quantity(String buy_quantity) {
        this.buy_quantity = buy_quantity;
    }

    public void setGroupbuy_image(String groupbuy_image) {
        this.groupbuy_image = groupbuy_image;
    }

    public void setGroupbuy_image1(String groupbuy_image1) {
        this.groupbuy_image1 = groupbuy_image1;
    }

    public void setBuyer_count(String buyer_count) {
        this.buyer_count = buyer_count;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getGroupbuy_price() {
        return groupbuy_price;
    }

    public String getArea_id() {
        return area_id;
    }

    public String getRemark() {
        return remark;
    }

    public String getState() {
        return state;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getRecommended() {
        return recommended;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public String getGoods_commonid() {
        return goods_commonid;
    }

    public String getGroupbuy_intro() {
        return groupbuy_intro;
    }

    public String getEnd_time_text() {
        return end_time_text;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getStart_time_text() {
        return start_time_text;
    }

    public String getGroupbuy_id() {
        return groupbuy_id;
    }

    public String getVirtual_quantity() {
        return virtual_quantity;
    }

    public String getUpper_limit() {
        return upper_limit;
    }

    public String getGroupbuy_name() {
        return groupbuy_name;
    }

    public String getStore_id() {
        return store_id;
    }

    public String getGroupbuy_rebate() {
        return groupbuy_rebate;
    }

    public String getBuy_quantity() {
        return buy_quantity;
    }

    public String getGroupbuy_image() {
        return groupbuy_image;
    }

    public String getGroupbuy_image1() {
        return groupbuy_image1;
    }

    public String getBuyer_count() {
        return buyer_count;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getViews() {
        return views;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getStart_time() {
        return start_time;
    }

}
