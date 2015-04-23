package com.haili.living.entity;

/**
 * Created by liteng on 2015/4/22.
 */
public class GoodClassEntity {

    /**
     * gc_id : 1073
     * gc_name : 水      果
     * gc_sort : 1
     * type_id : 38
     * gc_parent_id : 0
     */
    private String gc_id;//分类ID，获取商品列表时传入
    private String gc_name;
    private String gc_sort;
    private String type_id;
    private String gc_parent_id;

    public void setGc_id(String gc_id) {
        this.gc_id = gc_id;
    }

    public void setGc_name(String gc_name) {
        this.gc_name = gc_name;
    }

    public void setGc_sort(String gc_sort) {
        this.gc_sort = gc_sort;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public void setGc_parent_id(String gc_parent_id) {
        this.gc_parent_id = gc_parent_id;
    }

    public String getGc_id() {
        return gc_id;
    }

    public String getGc_name() {
        return gc_name;
    }

    public String getGc_sort() {
        return gc_sort;
    }

    public String getType_id() {
        return type_id;
    }

    public String getGc_parent_id() {
        return gc_parent_id;
    }
}

