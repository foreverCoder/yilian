package com.haili.living.entity.interfaces;

import java.util.List;

public class ImgsTheGoodInterfaceEntity {

    /**
     * datas : ["http://www.zq2014.com/haili/data/upload/shop/store/goods/1/1_04751630111395513_240.jpg","http://www.zq2014.com/haili/data/upload/shop/store/goods/1/1_04751630533474817_240.jpg","http://www.zq2014.com/haili/data/upload/shop/store/goods/1/1_04751630150558216_240.jpg"]
     * result : 1
     * code : 200
     */
    private List<String> datas;
    private String result;
    private int code;

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getDatas() {
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
