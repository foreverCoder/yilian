package com.haili.living.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/** 
 * @author melody 
 * @version 创建时间：2015年4月25日 下午7:26:52 
 * 类说明 
 */
public class GoodsDetailsActivity extends BaseActivity{
	@ViewInject(R.id.img_good)
	private ImageView img_good;
	@ViewInject(R.id.top_left)
	private ImageView top_left;
	@ViewInject(R.id.btn_fx)
	private ImageView btn_fx;
	@ViewInject(R.id.top_right)
	private ImageView top_right;
	
	
	@ViewInject(R.id.tx_goodName)
	private TextView tx_goodName;
	
	@ViewInject(R.id.tx_price)
	private TextView tx_price;
	
	@ViewInject(R.id.tx_market_price)
	private TextView tx_market_price;
	
	@ViewInject(R.id.tx_salesNum)
	private TextView tx_salesNum;
	
	
	@OnClick(R.id.top_left)
	public void finsh(View v) {
		finish();
	}
	
	@OnClick(R.id.btn_fx)
	public void share(View v) {
		Toast.makeText(GoodsDetailsActivity.this, "分享", Toast.LENGTH_SHORT).show();
	}
	
	@OnClick(R.id.top_right)
	public void goToGwc(View v) {
		Toast.makeText(GoodsDetailsActivity.this, "购物车", Toast.LENGTH_SHORT).show();
	}
	
	@OnClick(R.id.img_details)
	public void goTodetails(View v) {
		Toast.makeText(GoodsDetailsActivity.this, "图文详情", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_details);
		ViewUtils.inject(this); // 注入view和事件、
	}

}
