package com.haili.living;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

@ContentView(R.layout.activity_interface_show)
public class InterfaceShowActivity extends BaseActivity{
	@ViewInject(R.id.tvShowResult)
	private TextView tvShowResult;
	
	@SuppressLint("HandlerLeak")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        tvShowResult.setText(result);
	};
	
}
