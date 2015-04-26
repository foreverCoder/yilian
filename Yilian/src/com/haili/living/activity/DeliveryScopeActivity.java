package com.haili.living.activity;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.haili.living.utils.InterfaceUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/** 
 * @author melody 
 * @version 创建时间：2015年4月25日 下午6:42:11 
 * 类说明 
 */
public class DeliveryScopeActivity extends BaseActivity{
	private ProgressDialog progressDialog;
	@ViewInject(R.id.mWebView)
	WebView mWebView;
	@ViewInject(R.id.top_left)
	TextView top_left;
	@ViewInject(R.id.top_title)
	TextView top_title;
	@OnClick(R.id.top_left)
	public void finish(View v) {
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delivery_scope);
		ViewUtils.inject(this); // 注入view和事件、
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		mWebView.setWebViewClient(new MyWebViewClient());
		progressDialog=new ProgressDialog(context);
		progressDialog.setIndeterminate(false);
		progressDialog.setMessage("请稍候...");
		progressDialog.show();
		top_title.setText("配送范围");
		String storeIdString=getIntent().getStringExtra("store_id");
		if (storeIdString!=null) {
			getShopDistribution(storeIdString);//传入生活馆ID
		}
		
	}
	private class MyWebViewClient extends WebViewClient {

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.getSettings().setJavaScriptEnabled(true);
			view.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			view.getSettings().setBuiltInZoomControls(true);
			view.getSettings().setLoadWithOverviewMode(true);
			view.getSettings().setUseWideViewPort(true);
			view.setWebViewClient(new MyWebViewClient());
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageFinished(view, url);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageStarted(view, url, favicon);
		}

	}
	/**
	 * 根据生活馆id获取配送范围
	 * **/
	public void getShopDistribution(String storeId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("store_id", storeId);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getShopDistribution(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("getShopDistribution = " + result);

				ObjectMapper m = new ObjectMapper();
				String shopDis;
				try {
					JsonNode rootNode = m.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").toString();
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {
						shopDis = rootNode.path("datas").path("info").toString();
						//TODO 展示html 格式数据
						mWebView.loadDataWithBaseURL(null, shopDis, "text/html", "utf-8", null);
						LogUtils.d("shopDistribution = " + shopDis);
					} else {
						LogUtils.d("--------数据异常");
					}
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				toastLong("请求失败");
			}
		});

	}
}
