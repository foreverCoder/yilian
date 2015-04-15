package com.haili.living.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.R;
import com.haili.living.view.XListView;
import com.haili.living.view.XListView.IXListViewListener;

public class LivingMuseumActivity extends Activity implements CompoundButton.OnCheckedChangeListener ,OnClickListener{
	private TextView top_title, top_right;
	private ImageView top_left;
	private EditText top_search;
	private ListView classify_list_view;
	private XListView mListView;
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
		classify_list_view=(ListView)findViewById(R.id.classify_list_view);
		mListView=(XListView)findViewById(R.id.mlistview);
		mListView.setPullLoadEnable(true);
	}

	private void setListeners() {
		//搜索跳转
		top_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId ==EditorInfo.IME_ACTION_SEARCH){
					((InputMethodManager) top_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
							LivingMuseumActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
					if ("".equals(top_search.getText().toString().trim())||top_search.getText()==null) {
						Toast.makeText(LivingMuseumActivity.this, "关键字不能为空", Toast.LENGTH_SHORT).show();
					}else{
						//TODO 跳转
//						Intent intent=new Intent(LivingMuseumActivity.this,GroupBuyingClassifySearchActivity.class);
//						intent.putExtra("searchValue", top_search.getText().toString().trim());
//						startActivity(intent);
					}
					return true;
				}
				return false;
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
	}
	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
	private void initData() {
		top_search.setHint("请输入类别或关键字");
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
		btn.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			int id = arg0.getId();
			switch (id) {
			// 综合
			case R.id.radio_zh:

				break;
			// 销量
			case R.id.radio_xl:

				break;
			// 价格
			case R.id.radio_jg:

				break;
			// 距离
			case R.id.radio_jl:

				break;

			default:
				break;
			}
		}
	}

	@Override
	public void onClick(View arg0) {
		//跳转搜索页面
	}
}
