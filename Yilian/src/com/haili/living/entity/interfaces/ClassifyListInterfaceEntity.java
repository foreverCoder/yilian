package com.haili.living.entity.interfaces;

import java.util.List;

import com.haili.living.entity.ClassifyVo;

/**
 * Created by liteng on 2015/4/21.
 */
public class ClassifyListInterfaceEntity {

    /**
     * datas : {"good_class":[{"gc_id":"1073","gc_name":"水      果","type_id":"38","gc_parent_id":"0","gc_sort":"1"},{"gc_id":"1074","gc_name":"时蔬素菜","type_id":"35","gc_parent_id":"0","gc_sort":"2"},{"gc_id":"1075","gc_name":"蛋肉家禽","type_id":"35","gc_parent_id":"0","gc_sort":"3"},{"gc_id":"1076","gc_name":"海鲜水产","type_id":"35","gc_parent_id":"0","gc_sort":"4"},{"gc_id":"1088","gc_name":"保健品","type_id":"37","gc_parent_id":"0","gc_sort":"5"},{"gc_id":"1087","gc_name":"土特产品","type_id":"0","gc_parent_id":"0","gc_sort":"6"},{"gc_id":"1086","gc_name":"酒水茶饮","type_id":"36","gc_parent_id":"0","gc_sort":"7"},{"gc_id":"1085","gc_name":"粮油副食","type_id":"36","gc_parent_id":"0","gc_sort":"8"},{"gc_id":"1084","gc_name":"方便速食","type_id":"34","gc_parent_id":"0","gc_sort":"9"},{"gc_id":"1083","gc_name":"休闲食品","type_id":"34","gc_parent_id":"0","gc_sort":"10"},{"gc_id":"1082","gc_name":"牛奶乳品","type_id":"34","gc_parent_id":"0","gc_sort":"11"},{"gc_id":"1090","gc_name":"每日新品","type_id":"0","gc_parent_id":"0","gc_sort":"12"},{"gc_id":"1091","gc_name":"无厨","type_id":"39","gc_parent_id":"0","gc_sort":"13"},{"gc_id":"1092","gc_name":"母婴用品","type_id":"0","gc_parent_id":"0","gc_sort":"14"},{"gc_id":"1211","gc_name":"生活馆","type_id":"0","gc_parent_id":"0","gc_sort":"254"},{"gc_id":"1212","gc_name":"开心农庄","type_id":"40","gc_parent_id":"0","gc_sort":"255"}]}
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
        private List<ClassifyVo> good_class;

        public void setClassify_list(List<ClassifyVo> good_class) {
            this.good_class = good_class;
        }

        public List<ClassifyVo> getClasify_list() {
            return good_class;
        }

    }
    
    public boolean hasDatas(){
    	try {
			if (datas.good_class != null && datas.good_class.size() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return false;
    }
}
