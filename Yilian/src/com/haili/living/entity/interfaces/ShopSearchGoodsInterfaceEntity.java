package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.GoodEntity;

/**
 * Created by liteng on 2015/4/27.
 * 生活馆搜索商品接口类
 */
public class ShopSearchGoodsInterfaceEntity {
	/**
     * datas : [{"gc_id":"1217","goods_price":"11.00","farm_type":null,"goods_commonid":"20","goods_id":"43","store_id":"11","goods_name":"测试生活馆2","goods_image":"http://www.zq2014.com/haili/data/upload/shop/store/goods/11/11_04805073382771374_360.jpg","store_name":"生活馆大学城店"}]
     * result : 1
     * page_total : 1
     * code : 200
     * hasmore : false
     */
    private List<GoodEntity> datas;
    private String result;
    private int page_total;
    private int code;
    private boolean hasmore;

    public void setDatas(List<GoodEntity> datas) {
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

    public List<GoodEntity> getDatas() {
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
