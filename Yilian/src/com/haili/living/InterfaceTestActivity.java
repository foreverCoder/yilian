package com.haili.living;

import org.apache.http.protocol.HTTP;

import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.UnicodeUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@ContentView(R.layout.activity_interface_test)
public class InterfaceTestActivity extends BaseActivity{
	@ViewInject(R.id.btnLbsShops)
	private Button btnLbsShops;
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
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("$gc_id", "1211");
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
                    	showResultActivity(result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    	toastLong("请求失败");
                    }
                });
	}
	@OnClick(R.id.btnGoodClassify)
	public void testGoodClassify(View view){
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("style", "1");
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
    public void testLbsShops(View view) {//获取用户当前位置 500米内的生活馆信息
		  	RequestParams params = new RequestParams();
	        params.addQueryStringParameter("lng", "117.21279144");
	        params.addQueryStringParameter("lat", "31.8297323");
//		  	params.addQueryStringParameter("tel", "15955155418");
	        HttpUtils http = new HttpUtils();
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
	                    	LogUtils.d(responseInfo.result);
	                    	toastLong("reply: " + responseInfo.result);
	                    	showResultActivity(responseInfo.result);
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
