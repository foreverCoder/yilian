package com.haili.living.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.haili.living.R;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.entity.LivingGoodsVo;
import com.haili.living.utils.Utils;
import com.haili.living.view.XListView;
import com.haili.living.view.XListView.IXListViewListener;

public class LivingSearchActivity extends Activity implements CompoundButton.OnCheckedChangeListener, OnClickListener {
	private TextView top_title, top_right;
	private TextView top_left;
	private XListView mListView;
	private List<LivingGoodsVo> lVoList = new ArrayList<LivingGoodsVo>();
	private GoodsItemAdapter gAdapter;
	private int priceTag = -1;// -1 未选中 0正序 1倒叙
    private String  searchString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_living_search);
		initViews();
		setListeners();
		initData();
	}

	private void initViews() {
		top_title = (TextView) findViewById(R.id.top_title);
		top_left = (TextView) findViewById(R.id.top_left);
		top_right = (TextView) findViewById(R.id.top_right);
		top_right.setVisibility(View.GONE);
		initRadioBtns();
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullLoadEnable(true);
	}

	private void setListeners() {
		top_left.setOnClickListener(this);
		mListView.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
			}
		});
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	private void initData() {
		searchString=getIntent().getStringExtra("searchValue");
		top_title.setText("搜索  "+searchString);
		lVoList.add(new LivingGoodsVo("http://101.231.141.156/upl/uploads/images/goodLogo/2015-03-10/10020_1425950595365640.png",
				"美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("http://101.231.141.156/upl/uploads/images/goodLogo/2015-03-10/10020_1425950595365640.png",
				"美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("http://101.231.141.156/upl/uploads/images/goodLogo/2015-03-10/10020_1425950595365640.png",
				"美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("http://101.231.141.156/upl/uploads/images/goodLogo/2015-03-10/10020_1425950595365640.png",
				"美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("http://101.231.141.156/upl/uploads/images/goodLogo/2015-03-10/10020_1425950595365640.png",
				"美味七七碧根果", "19.7", "250g"));

//		gAdapter = new GoodsItemAdapter(LivingSearchActivity.this, lVoList);
//
//		mListView.setAdapter(gAdapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	protected void initRadioBtns() {
		initRadioBtn(R.id.radio_jg);
		initRadioBtn(R.id.radio_jl);
		initRadioBtn(R.id.radio_zh);
		initRadioBtn(R.id.radio_xl);
	}

	protected void initRadioBtn(int id) {
		RadioButton btn = (RadioButton) findViewById(id);
		if (id == R.id.radio_jg) {
			Drawable[] drawables = btn.getCompoundDrawables();
			int a = Utils.dip2px(getBaseContext(), 15);
			drawables[2].setBounds(0, 0, a, a);
			btn.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
			btn.setOnClickListener(this);
		}
		btn.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			int id = arg0.getId();
			switch (id) {
			// 综合
			case R.id.radio_zh:
				initJgButton();
				break;
			// 销量
			case R.id.radio_xl:
				initJgButton();
				break;
			// 价格
			case R.id.radio_jg:

				break;
			// 距离
			case R.id.radio_jl:
				initJgButton();
				break;

			default:
				break;
			}
		}
	}

	private void initJgButton() {
		Drawable drawable = null;
		RadioButton btn = (RadioButton) findViewById(R.id.radio_jg);
		int a = Utils.dip2px(getBaseContext(), 15);
		drawable = getResources().getDrawable(R.drawable.ic_jg0);
		priceTag = -1;
		drawable.setBounds(0, 0, a, a);
		btn.setCompoundDrawables(null, null, drawable, null);
	}

	@Override
	public void onClick(View arg0) {
		// 跳转搜索页面
		switch (arg0.getId()) {
		case R.id.top_left:
			finish();
			break;
		case R.id.radio_jg:
			Drawable drawable = null;
			RadioButton btn = (RadioButton) findViewById(arg0.getId());
			int a = Utils.dip2px(getBaseContext(), 15);
			if (priceTag == -1) {
				drawable = getResources().getDrawable(R.drawable.ic_jg1);
				priceTag = 0;
			} else if (priceTag == 0) {
				drawable = getResources().getDrawable(R.drawable.ic_jg2);
				priceTag = 1;
			} else if (priceTag == 1) {
				drawable = getResources().getDrawable(R.drawable.ic_jg1);
				priceTag = 0;
			}
			drawable.setBounds(0, 0, a, a);
			btn.setCompoundDrawables(null, null, drawable, null);
			break;

		default:
			break;
		}
	}
}
