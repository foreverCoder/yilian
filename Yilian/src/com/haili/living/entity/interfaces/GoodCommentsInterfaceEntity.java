package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.Eval_infoEntity;
import com.haili.living.entity.Goods_evaluate_infoEntity;

/**
 * Created by tengpangzhi on 15/6/11.
 */
public class GoodCommentsInterfaceEntity {

    /**
     * datas : {"goods_evaluate_info":[{"geval_storeid":"1","geval_frommembername":"oo7eat","geval_ordergoodsid":"180","geval_storename":"官方店铺","geval_goodsprice":"0.01","geval_addtime":"1431741056","geval_orderid":"139","geval_goodsname":"gdsgsg","geval_goodsid":"125","geval_id":"3","geval_isanonymous":"1","geval_image":null,"geval_content":"如今，\u201c互联网+\u201d正在深入改变人们的生活。昨天，记者获悉，支付宝在全国发起\u201c智慧菜场计划\u201d，并在合肥招募菜场参与此项计划，每家菜场将给予5万元资金支持智慧菜场建设。今后，\u201c智慧菜场\u201d将覆盖免费WiFi，市民逛菜场不用带现金，使用支付宝可直接买菜。","geval_explain":null,"geval_remark":null,"geval_state":"0","geval_frommemberid":"21","geval_scores":"5","avator":"http://www.zq2014.com/haili/data/upload/shop/avatar/avatar_21.jpg","geval_orderno":"7000000000012801"},{"geval_storeid":"1","geval_frommembername":"007eat","geval_ordergoodsid":"179","geval_storename":"官方店铺","geval_goodsprice":"0.01","geval_addtime":"1431740886","geval_orderid":"138","geval_goodsname":"gdsgsg","geval_goodsid":"125","geval_id":"2","geval_isanonymous":"0","geval_image":null,"geval_content":"据了解，在\u201c智慧菜市场\u201d，支持支付宝付款的摊位窗口玻璃上都贴着摊主的支付宝二维码，顾客在买菜后打开手机上的支付宝钱包\u201c扫一扫\u201d功能，对准二维码进行扫描，并输入需要支付的钱款即可完成支付。此次，支付宝宣布发起的\u201c智慧菜场计划\u201d，设立总额为500万元的\u201c智慧菜场\u201d创新基金，为全国100家农贸市场提供创新支持。（记者 梁昌军）","geval_explain":null,"geval_remark":null,"geval_state":"0","geval_frommemberid":"24","geval_scores":"5","avator":"http://www.zq2014.com/haili/data/upload/shop/common/default_user_portrait.gif","geval_orderno":"7000000000012701"}],"eval_info":{"bad_percent":0,"normal_percent":0,"normal":"0","good_percent":100,"good_star":5,"good":"2","bad":"0","all":2}}
     * result : 1
     * page_total : 1
     * code : 200
     * hasmore : false
     */
	
    private DatasEntity datas;
    public GoodCommentsInterfaceEntity() {
	}

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
         * goods_evaluate_info : [{"geval_storeid":"1","geval_frommembername":"oo7eat","geval_ordergoodsid":"180","geval_storename":"官方店铺","geval_goodsprice":"0.01","geval_addtime":"1431741056","geval_orderid":"139","geval_goodsname":"gdsgsg","geval_goodsid":"125","geval_id":"3","geval_isanonymous":"1","geval_image":null,"geval_content":"如今，\u201c互联网+\u201d正在深入改变人们的生活。昨天，记者获悉，支付宝在全国发起\u201c智慧菜场计划\u201d，并在合肥招募菜场参与此项计划，每家菜场将给予5万元资金支持智慧菜场建设。今后，\u201c智慧菜场\u201d将覆盖免费WiFi，市民逛菜场不用带现金，使用支付宝可直接买菜。","geval_explain":null,"geval_remark":null,"geval_state":"0","geval_frommemberid":"21","geval_scores":"5","avator":"http://www.zq2014.com/haili/data/upload/shop/avatar/avatar_21.jpg","geval_orderno":"7000000000012801"},{"geval_storeid":"1","geval_frommembername":"007eat","geval_ordergoodsid":"179","geval_storename":"官方店铺","geval_goodsprice":"0.01","geval_addtime":"1431740886","geval_orderid":"138","geval_goodsname":"gdsgsg","geval_goodsid":"125","geval_id":"2","geval_isanonymous":"0","geval_image":null,"geval_content":"据了解，在\u201c智慧菜市场\u201d，支持支付宝付款的摊位窗口玻璃上都贴着摊主的支付宝二维码，顾客在买菜后打开手机上的支付宝钱包\u201c扫一扫\u201d功能，对准二维码进行扫描，并输入需要支付的钱款即可完成支付。此次，支付宝宣布发起的\u201c智慧菜场计划\u201d，设立总额为500万元的\u201c智慧菜场\u201d创新基金，为全国100家农贸市场提供创新支持。（记者 梁昌军）","geval_explain":null,"geval_remark":null,"geval_state":"0","geval_frommemberid":"24","geval_scores":"5","avator":"http://www.zq2014.com/haili/data/upload/shop/common/default_user_portrait.gif","geval_orderno":"7000000000012701"}]
         * eval_info : {"bad_percent":0,"normal_percent":0,"normal":"0","good_percent":100,"good_star":5,"good":"2","bad":"0","all":2}
         */
        private List<Goods_evaluate_infoEntity> goods_evaluate_info;
        private Eval_infoEntity eval_info;

        public void setGoods_evaluate_info(List<Goods_evaluate_infoEntity> goods_evaluate_info) {
            this.goods_evaluate_info = goods_evaluate_info;
        }

        public void setEval_info(Eval_infoEntity eval_info) {
            this.eval_info = eval_info;
        }

        public List<Goods_evaluate_infoEntity> getGoods_evaluate_info() {
            return goods_evaluate_info;
        }

        public Eval_infoEntity getEval_info() {
            return eval_info;
        }
    }
    
    public boolean hasDatas(){
    	try {
			if (datas.goods_evaluate_info != null && datas.goods_evaluate_info.size() > 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
    	return false;
    }
}
