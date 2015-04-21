package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.GoodEntity;

/**
 * Created by liteng on 2015/4/21.
 */
public class GoodListInterfaceEntity {

    /**
     * datas : {"goods_list":[{"goods_price":"0.01","group_flag":false,"goods_marketprice":"1.00","goods_id":"95","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/12/12_04805292866680588_360.jpg","evaluation_count":"0","goods_name":"151631","evaluation_good_star":"5","goods_image":"12_04805292866680588.jpg","goods_salenum":"2","xianshi_flag":false},{"goods_price":"55.00","group_flag":false,"goods_marketprice":"555.00","goods_id":"80","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818904857823599_360.jpg","evaluation_count":"0","goods_name":"生活馆酒水茶饮晚餐","evaluation_good_star":"5","goods_image":"19_04818904857823599.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"222.00","group_flag":false,"goods_marketprice":"3333.00","goods_id":"79","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818904022502562_360.jpg","evaluation_count":"0","goods_name":"生活馆酒水茶饮中餐","evaluation_good_star":"5","goods_image":"19_04818904022502562.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"33.00","group_flag":false,"goods_marketprice":"333.00","goods_id":"78","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818903175386114_360.jpg","evaluation_count":"0","goods_name":"餐饮外卖早餐外卖","evaluation_good_star":"5","goods_image":"19_04818903175386114.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"22.00","group_flag":false,"goods_marketprice":"222.00","goods_id":"77","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818900484086831_360.jpg","evaluation_count":"0","goods_name":"生活馆休闲食品","evaluation_good_star":"5","goods_image":"19_04818900484086831.jpg","goods_salenum":"0","xianshi_flag":false}]}
     * result : 1
     * page_total : 6
     * code : 200
     * hasmore : true
     */
    private DatasEntity datas;
    private String result;
    private int page_total;
    private int code;
    private boolean hasmore;

    public void setDatas(DatasEntity datas) {
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

    public DatasEntity getDatas() {
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

    public class DatasEntity {
        /**
         * goods_list : [{"goods_price":"0.01","group_flag":false,"goods_marketprice":"1.00","goods_id":"95","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/12/12_04805292866680588_360.jpg","evaluation_count":"0","goods_name":"151631","evaluation_good_star":"5","goods_image":"12_04805292866680588.jpg","goods_salenum":"2","xianshi_flag":false},{"goods_price":"55.00","group_flag":false,"goods_marketprice":"555.00","goods_id":"80","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818904857823599_360.jpg","evaluation_count":"0","goods_name":"生活馆酒水茶饮晚餐","evaluation_good_star":"5","goods_image":"19_04818904857823599.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"222.00","group_flag":false,"goods_marketprice":"3333.00","goods_id":"79","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818904022502562_360.jpg","evaluation_count":"0","goods_name":"生活馆酒水茶饮中餐","evaluation_good_star":"5","goods_image":"19_04818904022502562.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"33.00","group_flag":false,"goods_marketprice":"333.00","goods_id":"78","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818903175386114_360.jpg","evaluation_count":"0","goods_name":"餐饮外卖早餐外卖","evaluation_good_star":"5","goods_image":"19_04818903175386114.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"22.00","group_flag":false,"goods_marketprice":"222.00","goods_id":"77","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/19/19_04818900484086831_360.jpg","evaluation_count":"0","goods_name":"生活馆休闲食品","evaluation_good_star":"5","goods_image":"19_04818900484086831.jpg","goods_salenum":"0","xianshi_flag":false}]
         */
        private List<GoodEntity> goods_list;

        public void setGoods_list(List<GoodEntity> goods_list) {
            this.goods_list = goods_list;
        }

        public List<GoodEntity> getGoods_list() {
            return goods_list;
        }

    }
    
    public boolean hasDatas(){
    	try {
			if (datas.goods_list != null && datas.goods_list.size() > 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
    	return false;
    }
}
