package com.haili.living.entity.interfaces;
import java.util.List;

import com.haili.living.entity.GoodEntity;

/**
 * Created by Administrator on 2015/4/21.
 */
public class GoodListInterfaceEntity {

    /**
     * datas : {"goods_list":[{"goods_price":"0.01","group_flag":false,"goods_marketprice":"10.00","goods_id":"97","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/21/21_04828525927007214_360.jpg","evaluation_count":"0","goods_name":"产地直供","evaluation_good_star":"5","goods_image":"21_04828525927007214.jpg","goods_salenum":"1","xianshi_flag":false},{"goods_price":"0.01","group_flag":false,"goods_marketprice":"10.00","goods_id":"96","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/21/21_04828524655658741_360.jpg","evaluation_count":"0","goods_name":"农家乐","evaluation_good_star":"5","goods_image":"21_04828524655658741.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"0.01","group_flag":false,"goods_marketprice":"1.00","goods_id":"95","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/12/12_04805292866680588_360.jpg","evaluation_count":"0","goods_name":"151631","evaluation_good_star":"5","goods_image":"12_04805292866680588.jpg","goods_salenum":"2","xianshi_flag":false},{"goods_price":"22.00","group_flag":false,"goods_marketprice":"222.00","goods_id":"93","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/20/20_04819010897802965_360.jpg","evaluation_count":"0","goods_name":"开心农庄住宿","evaluation_good_star":"5","goods_image":"20_04819010897802965.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"22.00","group_flag":false,"goods_marketprice":"222.00","goods_id":"92","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/20/20_04819010494438533_360.jpg","evaluation_count":"0","goods_name":"开心农专牧场庄主","evaluation_good_star":"5","goods_image":"20_04819010494438533.jpg","goods_salenum":"0","xianshi_flag":false}]}
     * page_total : 12
     * code : 200
     * hasmore : true
     */
    private DatasEntity datas;
    private int page_total;
    private int code;
    private boolean hasmore;

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
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
         * goods_list : [{"goods_price":"0.01","group_flag":false,"goods_marketprice":"10.00","goods_id":"97","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/21/21_04828525927007214_360.jpg","evaluation_count":"0","goods_name":"产地直供","evaluation_good_star":"5","goods_image":"21_04828525927007214.jpg","goods_salenum":"1","xianshi_flag":false},{"goods_price":"0.01","group_flag":false,"goods_marketprice":"10.00","goods_id":"96","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/21/21_04828524655658741_360.jpg","evaluation_count":"0","goods_name":"农家乐","evaluation_good_star":"5","goods_image":"21_04828524655658741.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"0.01","group_flag":false,"goods_marketprice":"1.00","goods_id":"95","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/12/12_04805292866680588_360.jpg","evaluation_count":"0","goods_name":"151631","evaluation_good_star":"5","goods_image":"12_04805292866680588.jpg","goods_salenum":"2","xianshi_flag":false},{"goods_price":"22.00","group_flag":false,"goods_marketprice":"222.00","goods_id":"93","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/20/20_04819010897802965_360.jpg","evaluation_count":"0","goods_name":"开心农庄住宿","evaluation_good_star":"5","goods_image":"20_04819010897802965.jpg","goods_salenum":"0","xianshi_flag":false},{"goods_price":"22.00","group_flag":false,"goods_marketprice":"222.00","goods_id":"92","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/20/20_04819010494438533_360.jpg","evaluation_count":"0","goods_name":"开心农专牧场庄主","evaluation_good_star":"5","goods_image":"20_04819010494438533.jpg","goods_salenum":"0","xianshi_flag":false}]
         */
        private List<GoodEntity> goods_list;

        public void setGoods_list(List<GoodEntity> goods_list) {
            this.goods_list = goods_list;
        }

        public List<GoodEntity> getGoods_list() {
            return goods_list;
        }
    }
}
