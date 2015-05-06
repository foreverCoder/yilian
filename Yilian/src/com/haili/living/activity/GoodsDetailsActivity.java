package com.haili.living.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.haili.living.adapter.OthersGoodItemAdapter;
import com.haili.living.entity.LivingGoodsVo;
import com.haili.living.view.HorizontalListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * @author melody
 * @version 创建时间：2015年4月25日 下午7:26:52 类说明
 */
public class GoodsDetailsActivity extends BaseActivity {
	
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
	@ViewInject(R.id.tx_ms)
	private TextView tx_ms;
	@ViewInject(R.id.tx_fw)
	private TextView tx_fw;
	@ViewInject(R.id.tx_fh)
	private TextView tx_fh;
	
	@ViewInject(R.id.horizon_listview)
	private HorizontalListView horizon_listview;

	@ViewInject(R.id.ratingBar)
	private RatingBar ratingBar;

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

	@OnClick(R.id.pj_layout)
	public void goToPjActivity(View v) {
		Toast.makeText(GoodsDetailsActivity.this, "查看评价详情", Toast.LENGTH_SHORT).show();
	}

	@ViewInject(R.id.add_m)
	private TextView add_m;

	@OnClick(R.id.add_l)
	public void reduce(View v) {
		int num = Integer.parseInt(add_m.getText().toString().trim());
		if (num > 1) {
			num--;
			add_m.setText(num + "");
		}
	}

	@OnClick(R.id.add_r)
	public void add(View v) {
		int num = Integer.parseInt(add_m.getText().toString().trim());
		num++;
		add_m.setText(num + "");
	}

   private OthersGoodItemAdapter adapter;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_details);
		ViewUtils.inject(this); // 注入view和事件、
		initView();
	}

	private void initView() {
		tx_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		ratingBar.setRating(4.5f);
		List<LivingGoodsVo> voList=new ArrayList<LivingGoodsVo>();
		voList.add(new LivingGoodsVo( "","含羞草开心果 80g","15.90"));
		voList.add(new LivingGoodsVo( "","含羞草开心果 80g","15.90"));
		voList.add(new LivingGoodsVo( "","含羞草开心果 80g","15.90"));
		voList.add(new LivingGoodsVo( "","含羞草开心果 80g","15.90"));
		
		adapter=new OthersGoodItemAdapter(context, voList);
		horizon_listview.setAdapter(adapter);
		
//		int a=com.haili.living.utils.Utils.dip2px(getBaseContext(), 35);
//		Drawable[] drawables =tx_fh.getCompoundDrawables();
//		drawables[2].setBounds(0, 0, a, a);
//		tx_fh.setCompoundDrawables(drawables[0],drawables[1],drawables[2],drawables[3]);
//		Drawable[] drawables2 =tx_fw.getCompoundDrawables();
//		drawables2[2].setBounds(0, 0, a, a);
//		tx_fw.setCompoundDrawables(drawables2[0],drawables2[1],drawables2[2],drawables2[3]);
//		Drawable[] drawables3 =tx_ms.getCompoundDrawables();
//		drawables3[2].setBounds(0, 0, a, a);
//		tx_ms.setCompoundDrawables(drawables3[0],drawables3[1],drawables3[2],drawables3[3]);
		
	}

}
