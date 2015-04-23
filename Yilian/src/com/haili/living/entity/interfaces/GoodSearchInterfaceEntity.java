package com.haili.living.entity.interfaces;


import java.util.List;

import com.haili.living.entity.GoodForSearchEntity;

/**
 * Created by liteng on 2015/4/23.
 */
public class GoodSearchInterfaceEntity {

    /**
     * datas : [{"nc_distinct":"64,0","gc_id":"1230","goods_marketprice":"10.00","store_domain":null,"evaluation_count":"0","goods_name":"产地直供","store_id":"21","goods_storage":"54","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/21/21_04828525927007214_240.jpg","goods_salenum":"1","member_id":"38","goods_freight":"18.00","goods_price":"0.01","color_id":"0","goods_id":"97","goods_commonid":"64","goods_jingle":"","evaluation_good_star":"5","store_name":"xiamoran"},{"nc_distinct":"63,0","gc_id":"1213","goods_marketprice":"10.00","store_domain":null,"evaluation_count":"0","goods_name":"农家乐","store_id":"21","goods_storage":"22","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/21/21_04828524655658741_240.jpg","goods_salenum":"0","member_id":"38","goods_freight":"0.00","goods_price":"0.01","color_id":"0","goods_id":"96","goods_commonid":"63","goods_jingle":"","evaluation_good_star":"5","store_name":"xiamoran"},{"nc_distinct":"62,0","gc_id":"1217","goods_marketprice":"1.00","store_domain":null,"evaluation_count":"0","goods_name":"151631","store_id":"12","goods_storage":"8","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/12/12_04805292866680588_240.jpg","goods_salenum":"2","member_id":"33","goods_freight":"8.00","goods_price":"0.01","color_id":"0","goods_id":"95","goods_commonid":"62","goods_jingle":"","evaluation_good_star":"5","store_name":"动漫基地店"},{"nc_distinct":"60,0","gc_id":"1229","goods_marketprice":"222.00","store_domain":null,"evaluation_count":"0","goods_name":"开心农庄住宿","store_id":"20","goods_storage":"22","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/20/20_04819010897802965_240.jpg","goods_salenum":"0","member_id":"8","goods_freight":"0.00","goods_price":"22.00","color_id":"0","goods_id":"93","goods_commonid":"60","goods_jingle":"住宿","evaluation_good_star":"5","store_name":"112"},{"nc_distinct":"59,0","gc_id":"1222","goods_marketprice":"222.00","store_domain":null,"evaluation_count":"0","goods_name":"开心农专牧场庄主","store_id":"20","goods_storage":"22","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/20/20_04819010494438533_240.jpg","goods_salenum":"0","member_id":"8","goods_freight":"0.00","goods_price":"22.00","color_id":"0","goods_id":"92","goods_commonid":"59","goods_jingle":"牧场庄主","evaluation_good_star":"5","store_name":"112"}]
     * result : 1
     * page_total : 12
     * code : 200
     * hasmore : true
     */
    private List<GoodForSearchEntity> datas;
    private String result;
    private int page_total;
    private int code;
    private boolean hasmore;

    public void setDatas(List<GoodForSearchEntity> datas) {
        this.datas = datas;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public List<GoodForSearchEntity> getDatas() {
        return datas;
    }

    public String getResult() {
        return result;
    }

    public int getPage_total() {
        return page_total;
    }

    public int getCode() {
        return code;
    }

    public boolean isHasmore() {
        return hasmore;
    }
    public boolean hasDatas(){
    	try {
			if (datas != null && datas.size() > 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
    	return false;
    }
}
