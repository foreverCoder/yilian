package com.haili.living.entity;
/*
 * 商品评价详情内容实体
 */
public class Goods_evaluate_infoEntity {
    /**
     * geval_storeid : 1
     * geval_frommembername : oo7eat
     * geval_ordergoodsid : 180
     * geval_storename : 官方店铺
     * geval_goodsprice : 0.01
     * geval_addtime : 1431741056
     * geval_orderid : 139
     * geval_goodsname : gdsgsg
     * geval_goodsid : 125
     * geval_id : 3
     * geval_isanonymous : 1
     * geval_image : null
     * geval_content : 如今，“互联网+”正在深入改变人们的生活。昨天，记者获悉，支付宝在全国发起“智慧菜场计划”，并在合肥招募菜场参与此项计划，每家菜场将给予5万元资金支持智慧菜场建设。今后，“智慧菜场”将覆盖免费WiFi，市民逛菜场不用带现金，使用支付宝可直接买菜。
     * geval_explain : null
     * geval_remark : null
     * geval_state : 0
     * geval_frommemberid : 21
     * geval_scores : 5
     * avator : http://www.zq2014.com/haili/data/upload/shop/avatar/avatar_21.jpg
     * geval_orderno : 7000000000012801
     */
    private String geval_storeid;//店铺编号
    private String geval_frommembername;//评价人名称
    private String geval_ordergoodsid;//订单商品表编号
    private String geval_storename;//店铺名称
    private String geval_goodsprice;//商品价格
    private String geval_addtime;//评价时间
    private String geval_orderid;//订单表自增ID
    private String geval_goodsname;//商品名称
    private String geval_goodsid;//商品表编号
    private String geval_id;// 评价ID
    private String geval_isanonymous;//0表示不是 1表示是匿名评价
    private String geval_image;// 晒单图片     (多张图片以“，”区分开，如果没有图片，只显示文字)
    private String geval_content;//信誉评价内容
    private String geval_explain;//解释内容
    private String geval_remark;//管理员对评价的处理备注
    private String geval_state;//评价信息的状态 0为正常 1为禁止显示
    private String geval_frommemberid;//评价人编号
    private String geval_scores;//1-5分
    private String avator;//评价人 头像
    private String geval_orderno;// 订单编号

    public void setGeval_storeid(String geval_storeid) {
        this.geval_storeid = geval_storeid;
    }

    public void setGeval_frommembername(String geval_frommembername) {
        this.geval_frommembername = geval_frommembername;
    }

    public void setGeval_ordergoodsid(String geval_ordergoodsid) {
        this.geval_ordergoodsid = geval_ordergoodsid;
    }

    public void setGeval_storename(String geval_storename) {
        this.geval_storename = geval_storename;
    }

    public void setGeval_goodsprice(String geval_goodsprice) {
        this.geval_goodsprice = geval_goodsprice;
    }

    public void setGeval_addtime(String geval_addtime) {
        this.geval_addtime = geval_addtime;
    }

    public void setGeval_orderid(String geval_orderid) {
        this.geval_orderid = geval_orderid;
    }

    public void setGeval_goodsname(String geval_goodsname) {
        this.geval_goodsname = geval_goodsname;
    }

    public void setGeval_goodsid(String geval_goodsid) {
        this.geval_goodsid = geval_goodsid;
    }

    public void setGeval_id(String geval_id) {
        this.geval_id = geval_id;
    }

    public void setGeval_isanonymous(String geval_isanonymous) {
        this.geval_isanonymous = geval_isanonymous;
    }

    public void setGeval_image(String geval_image) {
        this.geval_image = geval_image;
    }

    public void setGeval_content(String geval_content) {
        this.geval_content = geval_content;
    }

    public void setGeval_explain(String geval_explain) {
        this.geval_explain = geval_explain;
    }

    public void setGeval_remark(String geval_remark) {
        this.geval_remark = geval_remark;
    }

    public void setGeval_state(String geval_state) {
        this.geval_state = geval_state;
    }

    public void setGeval_frommemberid(String geval_frommemberid) {
        this.geval_frommemberid = geval_frommemberid;
    }

    public void setGeval_scores(String geval_scores) {
        this.geval_scores = geval_scores;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public void setGeval_orderno(String geval_orderno) {
        this.geval_orderno = geval_orderno;
    }

    public String getGeval_storeid() {
        return geval_storeid;
    }

    public String getGeval_frommembername() {
        return geval_frommembername;
    }

    public String getGeval_ordergoodsid() {
        return geval_ordergoodsid;
    }

    public String getGeval_storename() {
        return geval_storename;
    }

    public String getGeval_goodsprice() {
        return geval_goodsprice;
    }

    public String getGeval_addtime() {
        return geval_addtime;
    }

    public String getGeval_orderid() {
        return geval_orderid;
    }

    public String getGeval_goodsname() {
        return geval_goodsname;
    }

    public String getGeval_goodsid() {
        return geval_goodsid;
    }

    public String getGeval_id() {
        return geval_id;
    }

    public String getGeval_isanonymous() {
        return geval_isanonymous;
    }

    public String getGeval_image() {
        return geval_image;
    }

    public String getGeval_content() {
        return geval_content;
    }

    public String getGeval_explain() {
        return geval_explain;
    }

    public String getGeval_remark() {
        return geval_remark;
    }

    public String getGeval_state() {
        return geval_state;
    }

    public String getGeval_frommemberid() {
        return geval_frommemberid;
    }

    public String getGeval_scores() {
        return geval_scores;
    }

    public String getAvator() {
        return avator;
    }

    public String getGeval_orderno() {
        return geval_orderno;
    }
}