package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.StoreEntity;

/**
 * Created by liteng on 2015/4/24.
 */
public class ShopInfoInterfaceEntity {

    /**
     * datas : [{"store_label":null,"store_tel":"","street":"黎阳家苑","store_id":"9","store_workingtime":null,"store_name":"美味生活馆","store_address":""}]
     * result : 1
     * code : 200
     */
    private List<StoreEntity> datas;
    private String result;
    private int code;

    public void setDatas(List<StoreEntity> datas) {
        this.datas = datas;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<StoreEntity> getDatas() {
        return datas;
    }

    public String getResult() {
        return result;
    }

    public int getCode() {
        return code;
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
