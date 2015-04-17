package com.haili.living.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.haili.living.R;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LivingMuseumDetailsActivity  extends Activity{
	@ViewInject(R.id.top_left)
	TextView top_left;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_living_details);
		initData();
	}


	private void initData() {
		// TODO Auto-generated method stub
		
	}
}
