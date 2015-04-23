package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.GoodClassEntity;

/**
 * Created by liteng on 2015/4/22.
 */
public class GoodClassListInterfaceEntity {

    /**
     * datas : {"good_class":[{"gc_id":"1073","gc_name":"水      果","gc_sort":"1","type_id":"38","gc_parent_id":"0"},{"gc_id":"1074","gc_name":"时蔬素菜","gc_sort":"2","type_id":"35","gc_parent_id":"0"},{"gc_id":"1075","gc_name":"蛋肉家禽","gc_sort":"3","type_id":"35","gc_parent_id":"0"},{"gc_id":"1076","gc_name":"海鲜水产","gc_sort":"4","type_id":"35","gc_parent_id":"0"},{"gc_id":"1088","gc_name":"保健品","gc_sort":"5","type_id":"37","gc_parent_id":"0"},{"gc_id":"1087","gc_name":"土特产品","gc_sort":"6","type_id":"0","gc_parent_id":"0"},{"gc_id":"1086","gc_name":"酒水茶饮","gc_sort":"7","type_id":"36","gc_parent_id":"0"},{"gc_id":"1085","gc_name":"粮油副食","gc_sort":"8","type_id":"36","gc_parent_id":"0"},{"gc_id":"1084","gc_name":"方便速食","gc_sort":"9","type_id":"34","gc_parent_id":"0"},{"gc_id":"1083","gc_name":"休闲食品","gc_sort":"10","type_id":"34","gc_parent_id":"0"},{"gc_id":"1082","gc_name":"牛奶乳品","gc_sort":"11","type_id":"34","gc_parent_id":"0"},{"gc_id":"1090","gc_name":"每日新品","gc_sort":"12","type_id":"0","gc_parent_id":"0"},{"gc_id":"1091","gc_name":"无厨","gc_sort":"13","type_id":"39","gc_parent_id":"0"},{"gc_id":"1092","gc_name":"母婴用品","gc_sort":"14","type_id":"0","gc_parent_id":"0"},{"gc_id":"1211","gc_name":"生活馆","gc_sort":"254","type_id":"0","gc_parent_id":"0"},{"gc_id":"1212","gc_name":"开心农庄","gc_sort":"255","type_id":"40","gc_parent_id":"0"}]}
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
         * good_class : [{"gc_id":"1073","gc_name":"水      果","gc_sort":"1","type_id":"38","gc_parent_id":"0"},{"gc_id":"1074","gc_name":"时蔬素菜","gc_sort":"2","type_id":"35","gc_parent_id":"0"},{"gc_id":"1075","gc_name":"蛋肉家禽","gc_sort":"3","type_id":"35","gc_parent_id":"0"},{"gc_id":"1076","gc_name":"海鲜水产","gc_sort":"4","type_id":"35","gc_parent_id":"0"},{"gc_id":"1088","gc_name":"保健品","gc_sort":"5","type_id":"37","gc_parent_id":"0"},{"gc_id":"1087","gc_name":"土特产品","gc_sort":"6","type_id":"0","gc_parent_id":"0"},{"gc_id":"1086","gc_name":"酒水茶饮","gc_sort":"7","type_id":"36","gc_parent_id":"0"},{"gc_id":"1085","gc_name":"粮油副食","gc_sort":"8","type_id":"36","gc_parent_id":"0"},{"gc_id":"1084","gc_name":"方便速食","gc_sort":"9","type_id":"34","gc_parent_id":"0"},{"gc_id":"1083","gc_name":"休闲食品","gc_sort":"10","type_id":"34","gc_parent_id":"0"},{"gc_id":"1082","gc_name":"牛奶乳品","gc_sort":"11","type_id":"34","gc_parent_id":"0"},{"gc_id":"1090","gc_name":"每日新品","gc_sort":"12","type_id":"0","gc_parent_id":"0"},{"gc_id":"1091","gc_name":"无厨","gc_sort":"13","type_id":"39","gc_parent_id":"0"},{"gc_id":"1092","gc_name":"母婴用品","gc_sort":"14","type_id":"0","gc_parent_id":"0"},{"gc_id":"1211","gc_name":"生活馆","gc_sort":"254","type_id":"0","gc_parent_id":"0"},{"gc_id":"1212","gc_name":"开心农庄","gc_sort":"255","type_id":"40","gc_parent_id":"0"}]
         */
        private List<GoodClassEntity> good_class;

        public void setGood_class(List<GoodClassEntity> good_class) {
            this.good_class = good_class;
        }

        public List<GoodClassEntity> getGood_class() {
            return good_class;
        }
    }
    
    public boolean hasDatas(){
    	try {
			if (datas.good_class != null && datas.good_class.size() > 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
    	return false;
    }
}
