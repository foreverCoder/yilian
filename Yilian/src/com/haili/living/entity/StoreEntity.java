package com.haili.living.entity;
/**
 * 生活馆实体
 * Created by liteng on 2015/4/21.
 */
public class StoreEntity {

    /**
     * maplnglat : 117.18365190,31.81160903
     * store_id : 9
     * store_name : 美味生活馆
     * type_id : 2
     */
    private String maplnglat;//地图经纬度
    private String store_id;//生活馆ID
    private String store_name;//生活馆名称
    private String type_id;//生活馆店铺类型   1商城 2，生活馆；3农庄

    public void setMaplnglat(String maplnglat) {
        this.maplnglat = maplnglat;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getMaplnglat() {
        return maplnglat;
    }

    public String getStore_id() {
        return store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getType_id() {
        return type_id;
    }
}
