package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.GroupBuyGoodEntity;

/**
 * Created by tengpangzhi on 15/6/12.
 */
public class GroupBuyInterfaceEntity {

    /**
     * datas : [{"groupbuy_price":"555252.00","area_id":"0","remark":"","state":"20","goods_name":"1232541","recommended":"0","goods_url":"http://www.zq2014.com/haili/shop/index.php?act=goods&op=index&goods_id=98","goods_price":"44444.00","goods_commonid":"65","groupbuy_intro":"","end_time_text":"2015-07-31 00:00","store_name":"生活馆大学城店","start_time_text":"2015-04-27 11:39","groupbuy_id":"6","virtual_quantity":"0","upper_limit":"0","groupbuy_name":"453224324","store_id":"11","groupbuy_rebate":"124.93","buy_quantity":"0","groupbuy_image":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834499897549680_mid.jpg","groupbuy_image1":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834499897549680_mid.jpg","buyer_count":"0","class_id":"0","end_time":"1438272000","views":"1","goods_id":"98","start_time":"1430105940"},{"groupbuy_price":"55555.00","area_id":"0","remark":"","state":"20","goods_name":"77477","recommended":"0","goods_url":"http://www.zq2014.com/haili/shop/index.php?act=goods&op=index&goods_id=99","goods_price":"35345.00","goods_commonid":"66","groupbuy_intro":"","end_time_text":"2015-09-23 00:00","store_name":"生活馆大学城店","start_time_text":"2015-04-27 11:44","groupbuy_id":"7","virtual_quantity":"0","upper_limit":"0","groupbuy_name":"528752","store_id":"11","groupbuy_rebate":"15.72","buy_quantity":"0","groupbuy_image":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834502431367156_mid.jpg","groupbuy_image1":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834502431367156_mid.jpg","buyer_count":"0","class_id":"0","end_time":"1442937600","views":"0","goods_id":"99","start_time":"1430106240"},{"groupbuy_price":"4144.00","area_id":"0","remark":"","state":"20","goods_name":"8464646","recommended":"0","goods_url":"http://www.zq2014.com/haili/shop/index.php?act=goods&op=index&goods_id=100","goods_price":"23161.00","goods_commonid":"67","groupbuy_intro":"","end_time_text":"2015-08-21 00:00","store_name":"生活馆大学城店","start_time_text":"2015-04-27 11:46","groupbuy_id":"8","virtual_quantity":"0","upper_limit":"0","groupbuy_name":"64646","store_id":"11","groupbuy_rebate":"1.79","buy_quantity":"0","groupbuy_image":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834503543076832_mid.jpg","groupbuy_image1":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834503543076832_mid.jpg","buyer_count":"0","class_id":"0","end_time":"1440086400","views":"0","goods_id":"100","start_time":"1430106360"},{"groupbuy_price":"444.00","area_id":"0","remark":"","state":"20","goods_name":"5242452","recommended":"0","goods_url":"http://www.zq2014.com/haili/shop/index.php?act=goods&op=index&goods_id=101","goods_price":"2422.00","goods_commonid":"68","groupbuy_intro":"","end_time_text":"2015-09-27 00:00","store_name":"生活馆大学城店","start_time_text":"2015-04-27 11:51","groupbuy_id":"9","virtual_quantity":"0","upper_limit":"0","groupbuy_name":"56533","store_id":"11","groupbuy_rebate":"1.83","buy_quantity":"0","groupbuy_image":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834506733463044_mid.jpg","groupbuy_image1":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834506733463044_mid.jpg","buyer_count":"0","class_id":"0","end_time":"1443283200","views":"0","goods_id":"101","start_time":"1430106660"},{"groupbuy_price":"444.00","area_id":"0","remark":"","state":"20","goods_name":"54242","recommended":"0","goods_url":"http://www.zq2014.com/haili/shop/index.php?act=goods&op=index&goods_id=102","goods_price":"432542.00","goods_commonid":"69","groupbuy_intro":"","end_time_text":"2015-09-27 00:00","store_name":"生活馆大学城店","start_time_text":"2015-04-27 11:52","groupbuy_id":"10","virtual_quantity":"0","upper_limit":"0","groupbuy_name":"45544","store_id":"11","groupbuy_rebate":"0.01","buy_quantity":"0","groupbuy_image":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834507153999884_mid.jpg","groupbuy_image1":"http://www.zq2014.com/haili/data/upload/shop/groupbuy/11/11_04834507153999884_mid.jpg","buyer_count":"0","class_id":"0","end_time":"1443283200","views":"0","goods_id":"102","start_time":"1430106720"}]
     * result : 1
     * page_total : 2
     * code : 200
     * hasmore : true
     */
    private List<GroupBuyGoodEntity> datas;
    private String result;
    private int page_total;
    private int code;
    private boolean hasmore;

    public void setDatas(List<GroupBuyGoodEntity> datas) {
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

    public List<GroupBuyGoodEntity> getDatas() {
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
