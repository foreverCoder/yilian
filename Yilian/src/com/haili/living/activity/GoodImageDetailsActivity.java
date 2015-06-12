package com.haili.living.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * @ClassName:
 * @Description: 图文 详情 webview
 * @author melody
 * @date 2015-6-2 下午5:09:43
 * 
 */
public class GoodImageDetailsActivity extends BaseActivity {
	private boolean flag = false;
	private ValueCallback<Uri> mUploadMessage;
	@ViewInject(R.id.webview)
	private WebView webview;
	@ViewInject(R.id.top_title)
	private TextView top_title;

	@OnClick(R.id.top_right)
	public void goToGwc(View v) {
		Toast.makeText(GoodImageDetailsActivity.this, "购物车", Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.top_left)
	public void finsh(View v) {
		finish();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goodsimge_detail);
		ViewUtils.inject(this); // 注入view和事件、
		initViews();
	}

	private void initViews() {
		String content = getIntent().getStringExtra("content");
		top_title.setText("图文详情");
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		settings.setDomStorageEnabled(true);

		webview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
		webview.setWebViewClient(new MyWebViewClient());
		webview.setWebChromeClient(new MyWebChromeClient());

	}

	public void back(View view) {
		finish();
	}

	private class MyWebViewClient extends WebViewClient {

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.getSettings().setJavaScriptEnabled(true);
			view.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			view.getSettings().setBuiltInZoomControls(true);
			view.getSettings().setLoadWithOverviewMode(true);
			view.getSettings().setUseWideViewPort(true);
			view.setWebViewClient(new MyWebViewClient());
			// view.addJavascriptInterface(new DemoJavaScriptInterface(),
			// "upl");
			view.setWebChromeClient(new MyWebChromeClient());
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageFinished(view, url);
			// addInputClickListner();
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

	private class MyWebChromeClient extends WebChromeClient {
		// For Android 3.0+
		public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
			System.out.println("	// For Android 3.0+");
			// flag=true;
			// if (mUploadMessage != null)
			// return;
			// mUploadMessage = uploadMsg;
			// selectImage();
		}

		// For Android < 3.0
		@SuppressWarnings("unused")
		public void openFileChooser(ValueCallback<Uri> uploadMsg) {
			System.out.println("	// For Android < 3.0");
			openFileChooser(uploadMsg, "");
		}

		// For Android > 4.1.1
		@SuppressWarnings("unused")
		public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
			System.out.println("// For Android > 4.1.1");
			openFileChooser(uploadMsg, acceptType);
		}

		@Override
		public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
			AlertDialog.Builder b2 = new AlertDialog.Builder(GoodImageDetailsActivity.this).setTitle("温馨提示").setMessage(message).setPositiveButton("确认", new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					result.confirm();
				}
			});
			b2.setCancelable(false);
			b2.create();
			b2.show();
			return true;
		}

	}
}
