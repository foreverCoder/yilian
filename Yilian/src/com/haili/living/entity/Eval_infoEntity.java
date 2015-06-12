package com.haili.living.entity;

/*
 *  商品评价统计实体
 */
public class Eval_infoEntity {
    /**
     * bad_percent : 0
     * normal_percent : 0
     * normal : 0
     * good_percent : 100
     * good_star : 5
     * good : 2
     * bad : 0
     * all : 2
     */
    private int bad_percent;//差评%
    private int normal_percent;//中评%
    private String normal;//中评个数
    private int good_percent;//好评%
    private int good_star;
    private String good;//好评个数
    private String bad;//差评个数
    private int all;//评价个数

    public void setBad_percent(int bad_percent) {
        this.bad_percent = bad_percent;
    }

    public void setNormal_percent(int normal_percent) {
        this.normal_percent = normal_percent;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public void setGood_percent(int good_percent) {
        this.good_percent = good_percent;
    }

    public void setGood_star(int good_star) {
        this.good_star = good_star;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getBad_percent() {
        return bad_percent;
    }

    public int getNormal_percent() {
        return normal_percent;
    }

    public String getNormal() {
        return normal;
    }

    public int getGood_percent() {
        return good_percent;
    }

    public int getGood_star() {
        return good_star;
    }

    public String getGood() {
        return good;
    }

    public String getBad() {
        return bad;
    }

    public int getAll() {
        return all;
    }
}
