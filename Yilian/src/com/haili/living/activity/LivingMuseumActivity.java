package com.haili.living.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.R;
import com.haili.living.adapter.ClassifyItemAdapter;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.entity.ClassifyVo;
import com.haili.living.entity.LivingGoodsVo;
import com.haili.living.utils.Utils;
import com.haili.living.view.XListView;
import com.haili.living.view.XListView.IXListViewListener;

public class LivingMuseumActivity extends Activity implements CompoundButton.OnCheckedChangeListener, OnClickListener {
	private TextView top_title, top_right;
	private ImageView top_left;
	private EditText top_search;
	private ListView classify_list_view;
	private XListView mListView;
	private List<ClassifyVo> cVoList = new ArrayList<ClassifyVo>();
	private List<LivingGoodsVo> lVoList = new ArrayList<LivingGoodsVo>();
	private ClassifyItemAdapter cAdapter;
	private GoodsItemAdapter gAdapter;
	private int priceTag = -1;// -1 未选中 0正序 1倒叙

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_living_museum);
		initViews();
		setListeners();
		initData();
	}

	private void initViews() {
		top_title = (TextView) findViewById(R.id.top_title);
		top_title.setVisibility(View.GONE);
		top_left = (ImageView) findViewById(R.id.top_left);
		top_right = (TextView) findViewById(R.id.top_right);
		top_right.setVisibility(View.GONE);
		top_search = (EditText) findViewById(R.id.top_search);
		top_search.setVisibility(View.VISIBLE);
		initRadioBtns();
		classify_list_view = (ListView) findViewById(R.id.classify_list_view);
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullLoadEnable(true);
	}

	private void setListeners() {
		// 搜索跳转
		top_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					((InputMethodManager) top_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
							LivingMuseumActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
					if ("".equals(top_search.getText().toString().trim()) || top_search.getText() == null) {
						Toast.makeText(LivingMuseumActivity.this, "关键字不能为空", Toast.LENGTH_SHORT).show();
					} else {
						// TODO 跳转
						Intent intent = new Intent(LivingMuseumActivity.this, LivingSearchActivity.class);
						intent.putExtra("searchValue", top_search.getText().toString().trim());
						startActivity(intent);
					}
					return true;
				}
				return false;
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO 跳转
				Intent intent = new Intent(LivingMuseumActivity.this, LivingMuseumDetailsActivity.class);
				startActivity(intent);
			}
		});
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
		classify_list_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				cAdapter.setSelected((ClassifyVo) cAdapter.getItem(arg2));
				cAdapter.notifyDataSetChanged();
			}
		});
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	private void initData() {
		top_search.setHint("请输入类别或关键字");

		cVoList.add(new ClassifyVo("农庄"));
		cVoList.add(new ClassifyVo("牧场"));
		cVoList.add(new ClassifyVo("垂钓"));
		cVoList.add(new ClassifyVo("直供"));
		cVoList.add(new ClassifyVo("采摘"));
		cVoList.add(new ClassifyVo("蔬菜"));
		cVoList.add(new ClassifyVo("水果"));
		cVoList.add(new ClassifyVo("农庄"));
		cVoList.add(new ClassifyVo("牧场"));
		cVoList.add(new ClassifyVo("垂钓"));
		cVoList.add(new ClassifyVo("直供"));
		cVoList.add(new ClassifyVo("采摘"));
		cVoList.add(new ClassifyVo("蔬菜"));
		cVoList.add(new ClassifyVo("水果"));

		cAdapter = new ClassifyItemAdapter(LivingMuseumActivity.this, cVoList);

		classify_list_view.setAdapter(cAdapter);
		if (cVoList.size() > 0) {
			cAdapter.setSelected((ClassifyVo) cAdapter.getItem(0));
			cAdapter.notifyDataSetChanged();
		}

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

		gAdapter = new GoodsItemAdapter(LivingMuseumActivity.this, lVoList);

		mListView.setAdapter(gAdapter);
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
