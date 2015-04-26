package com.haili.living.activity;

import android.os.Bundle;

import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.lidroid.xutils.ViewUtils;

/** 
 * @author melody 
 * @version 创建时间：2015年4月25日 下午7:26:52 
 * 类说明 
 */
public class GoodsDetailsActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_details);
		ViewUtils.inject(this); // 注入view和事件、
	}

}
