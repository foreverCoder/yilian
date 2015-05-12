package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.GoodEntity;

public class GoodInfoAndRecommendInterfaceEntity {


    /**
     * datas : {"goods_info":{"goods_collect":"0","gc_id":"1217","life_type":null,"goods_marketprice":"12.00","evaluation_count":"0","goods_name":"生活馆中商品类测试添加商品4","store_id":"9","goods_storage":"12","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_salenum":"0","goods_click":9,"goods_price":"12.00","goods_commend":"1","farm_type":null,"goods_commonid":"16","goods_id":"15","evaluation_good_star":"5","store_name":"美味生活馆"},"goods_commend_list":[{"goods_price":"10.00","goods_id":"11","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆类测试"},{"goods_price":"12.00","goods_id":"13","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品"},{"goods_price":"12.00","goods_id":"14","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708560323065_240.jpg","goods_name":"生活馆中商品类测试添加商品２"},{"goods_price":"12.00","goods_id":"15","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品4"},{"goods_price":"120.00","goods_id":"17","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品5 水田 水稻 全托管"},{"goods_price":"121.00","goods_id":"18","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品5 水田 水稻 半托管"}]}
     * result : 1
     * code : 200
     */
    private DatasEntity datas;
    private String result;
    private int code;

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public String getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public class DatasEntity {
        /**
         * goods_info : {"goods_collect":"0","gc_id":"1217","life_type":null,"goods_marketprice":"12.00","evaluation_count":"0","goods_name":"生活馆中商品类测试添加商品4","store_id":"9","goods_storage":"12","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_salenum":"0","goods_click":9,"goods_price":"12.00","goods_commend":"1","farm_type":null,"goods_commonid":"16","goods_id":"15","evaluation_good_star":"5","store_name":"美味生活馆"}
         * goods_commend_list : [{"goods_price":"10.00","goods_id":"11","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆类测试"},{"goods_price":"12.00","goods_id":"13","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品"},{"goods_price":"12.00","goods_id":"14","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708560323065_240.jpg","goods_name":"生活馆中商品类测试添加商品２"},{"goods_price":"12.00","goods_id":"15","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品4"},{"goods_price":"120.00","goods_id":"17","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品5 水田 水稻 全托管"},{"goods_price":"121.00","goods_id":"18","goods_image_url":"http://www.zq2014.com/haili/data/upload/shop/store/goods/9/9_04762708400105268_240.jpg","goods_name":"生活馆中商品类测试添加商品5 水田 水稻 半托管"}]
         */
        private GoodEntity goods_info;//商品详情
        private List<GoodEntity> goods_commend_list;//推荐的商品集合

        public void setGoods_info(GoodEntity goods_info) {
            this.goods_info = goods_info;
        }

        public void setGoods_commend_list(List<GoodEntity> goods_commend_list) {
            this.goods_commend_list = goods_commend_list;
        }

        public GoodEntity getGoods_info() {
            return goods_info;
        }

        public List<GoodEntity> getGoods_commend_list() {
            return goods_commend_list;
        }

    }
    
    public boolean hasDatas(){
    	if(datas.goods_commend_list !=null && datas.goods_commend_list.size()>0){
    		return true;
    	}else{
    		return false;
    	}
    }
}
