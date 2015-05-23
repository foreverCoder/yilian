package com.haili.living.entity.interfaces;

import com.haili.living.entity.GoodEvaluation;

public class GoodEvaluationInterfaceEntity {
	/**
     * datas : {"bad_percent":0,"normal_percent":0,"normal":"0","good_percent":100,"good_star":5,"good":"0","bad":"0","all":0}
     * result : 1
     * code : 200
     */
    private GoodEvaluation datas;
    private String result;
    private int code;

    public void setDatas(GoodEvaluation datas) {
        this.datas = datas;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public GoodEvaluation getDatas() {
        return datas;
    }

    public String getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

}
