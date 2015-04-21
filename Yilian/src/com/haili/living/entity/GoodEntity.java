package com.haili.living.entity;

/**
 * 商品实体
 * Created by Administrator on 2015/4/21.
 */
public class GoodEntity {

    /**
     * goods_price : 0.01
     * group_flag : false
     * goods_marketprice : 10.00
     * goods_id : 97
     * goods_image_url : http://www.zq2014.com/haili/data/upload/shop/store/goods/21/21_04828525927007214_360.jpg
     * evaluation_count : 0
     * goods_name : 产地直供
     * evaluation_good_star : 5
     * goods_image : 21_04828525927007214.jpg
     * goods_salenum : 1
     * xianshi_flag : false
     */
    private String goods_price;//商品价格
    private boolean group_flag;
    private String goods_marketprice;//商品超市价格
    private String goods_id;//商品ID
    private String goods_image_url;//商品图片地址
    private String evaluation_count;
    private String goods_name;
    private String evaluation_good_star;//评价星级
    private String goods_image;
    private String goods_salenum;
    private boolean xianshi_flag;

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
