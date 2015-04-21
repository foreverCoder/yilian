package com.haili.living;

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.interfaces.GoodListInterfaceEntity;
import com.haili.living.utils.InterfaceUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.annotation.SuppressLint;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@ContentView(R.layout.activity_interface_test)
public class InterfaceTestActivity extends BaseActivity{
	static class HandleMsg {
		static int ERROR = -1;// 请求失败
		static int NO_FAULTS = 0;// 没有历史故障
		static int SUCCESS = 1;// 请求成功

	}
	@SuppressLint("HandlerLeak")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
	};
	
	@OnClick(R.id.btnSearchGoodList)
	public void testSearchGoodList(View view){
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("$goods_id", "20");
//		params.addQueryStringParameter("$key", InterfaceUtils.SortStyle.MULTIPLE);//排序类型综合
//		params.addQueryStringParameter("$order",InterfaceUtils.SortDirect.REVERSE );//排序方向倒序
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
        		InterfaceUtils.getSearchGoodList(),
                params,
                new RequestCallBack<String>() {

                    @Override
                    public void onStart() {
                    	toastLong("请求服务器");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                    	
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                    	String result = InterfaceUtils.getResponseResult(responseInfo.result);
                    	LogUtils.d(result);
                    	toastLong("reply: " + result);
                    	showResultActivity(result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    	toastLong("请求失败");
                    }
                });
	}
	
	@OnClick(R.id.btnGoodlistByClassify)
	public void testGoodListByClassify(View view){
		getGoodListByClassify("1211",InterfaceUtils.SortStyle.MULTIPLE,InterfaceUtils.SortDirect.POSITIVE,"20","1");
	}
	@OnClick(R.id.btnGoodClassify)
	public void testGoodClassify(View view){
		RequestParams params = new RequestParams();
		params.addBodyParameter("style", "1");
//		params.addQueryStringParameter("style", "1");
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
        		InterfaceUtils.getGoodClassify(),
                params,
                new RequestCallBack<String>() {

                    @Override
                    public void onStart() {
                    	toastLong("请求服务器");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                    	
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                    	String result = InterfaceUtils.getResponseResult(responseInfo.result);
                    	LogUtils.d(result);
                    	toastLong("reply: " + result);
                    	showResultActivity(result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    	toastLong("请求失败");
                    }
                });
	}
	
	@OnClick(R.id.btnLbsShops)
    public void testLbsShops(View view) {//获取用户当前位置 3公里内的生活馆信息
		  	RequestParams params = new RequestParams();
		  	params.addBodyParameter("lng", "117.18365190");
		  	params.addBodyParameter("lat", "31.81160903");
//	        params.addQueryStringParameter("lng", "117.18365190");
//	        params.addQueryStringParameter("lat", "31.81160903");
//		  	params.addQueryStringParameter("tel", "13170198790");
	        HttpUtils http = new HttpUtils();
//	        http.configResponseTextCharset("GBK");
	        http.send(HttpRequest.HttpMethod.POST,
//	               "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm",
	        		InterfaceUtils.getLbsShops(),
	                params,
	                new RequestCallBack<String>() {

	                    @Override
	                    public void onStart() {
	                    	toastLong("请求服务器");
	                    }

	                    @Override
	                    public void onLoading(long total, long current, boolean isUploading) {
	                    	
	                    }

	                    @Override
	                    public void onSuccess(ResponseInfo<String> responseInfo) {
	                    	String result = InterfaceUtils.getResponseResult(responseInfo.result);
	                    	LogUtils.d(result);
	                    	toastLong("reply: " + result);
	                    	showResultActivity(result);
	                    }

	                    @Override
	                    public void onFailure(HttpException error, String msg) {
	                    	toastLong("请求失败");
	                    }
	                });
		
	}
	
	/**
	 * @param gcId 分类ID
	 * @param key 排序类型
	 * @param order 排序方向
	 * @param page 每页数目
	 * @param curpage 当前页
	 */
	private void getGoodListByClassify(String gcId,String key,String order,String page,String curpage){
		RequestParams params = new RequestParams();
		params.addBodyParameter("gc_id", gcId);
		params.addBodyParameter("key", gcId);
		params.addBodyParameter("order",order);
//		params.addBodyParameter("page", page);
//		params.addBodyParameter("curpage", curpage);
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
        		InterfaceUtils.getGoodListByClassify(),
                params,
                new RequestCallBack<String>() {

                    @Override
                    public void onStart() {
                    	toastLong("请求服务器");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                    	
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                    	String result = InterfaceUtils.getResponseResult(responseInfo.result);
                    	
                    	LogUtils.d(result);
                    	toastLong("reply: " + result);
//                    	showResultActivity(result);
                    	
                    	ObjectMapper mapper = new ObjectMapper();
                    	
                    	try {
							GoodListInterfaceEntity entity = mapper.readValue(result, GoodListInterfaceEntity.class);
							LogUtils.d("entity "+entity.getCode()+" "+entity.getResult()+" "+entity.getPage_total());
							
							Iterator<GoodEntity> iterator = entity.getDatas().getGoods_list().iterator();
							
							while(iterator.hasNext()){
								GoodEntity good = iterator.next();
								
								LogUtils.d("good "+ good.getEvaluation_count()+" "+good.getEvaluation_good_star()+" "+good.getGoods_id()+" "+good.getGoods_image()+" "+good.getGoods_image_url()+" "+good.getGoods_marketprice()+" "+good.getGoods_name()+" "+good.getGoods_price()+" "+good.getGoods_salenum());
							}
						} catch (JsonParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    	toastLong("请求失败");
                    }
                });
	}
	
	private void showResultActivity(String result){
		Intent intent = new Intent();
    	intent.putExtra("result", result);
    	intent.setClass(InterfaceTestActivity.this, InterfaceShowActivity.class);
    	startActivity(intent);
	}
}
