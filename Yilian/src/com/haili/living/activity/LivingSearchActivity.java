package com.haili.living.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amap.api.location.f;
import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.adapter.LivingSearchItemAdapter;
import com.haili.living.entity.GoodForSearchEntity;
import com.haili.living.entity.interfaces.GoodSearchInterfaceEntity;
import com.haili.living.utils.ConstantValue;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.Utils;
import com.haili.living.view.XListView;
import com.haili.living.view.XListView.IXListViewListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

public class LivingSearchActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, OnClickListener {
	private TextView top_title, top_right;
	private TextView top_left;
	private XListView mListView;
	private List<GoodForSearchEntity> lVoList = new ArrayList<GoodForSearchEntity>();
	private LivingSearchItemAdapter gAdapter;
	private int priceTag = -1;// -1 未选中 0正序 1倒叙
	private String searchString;
	private int pageNum = 1;// 当前页数
	private String sortStyle = InterfaceUtils.SortStyle.DEFAULT;// 排序方式
	private String sortDirect = InterfaceUtils.SortDirect.POSITIVE;// 排序大小
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_living_search);
		initViews();
		setListeners();
		initData();
	}

	private void initViews() {
		progressDialog = new ProgressDialog(LivingSearchActivity.this);
		progressDialog.setIndeterminate(false);
		progressDialog.setMessage("请稍候...");
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
				pageNum = 1;
				getSearchGoodList(searchString, sortStyle, sortDirect, pageNum+"", ConstantValue.PAGE_COUNT+"",true);
			}

			@Override
			public void onLoadMore() {
				pageNum += 1;
				getSearchGoodList(searchString, sortStyle, sortDirect, pageNum+"", ConstantValue.PAGE_COUNT+"",false);
			}
		});
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	private void initData() {
		searchString = getIntent().getStringExtra("searchValue");
		top_title.setText("搜索  " + searchString);
		if (!progressDialog.isShowing()) {
			progressDialog.show();
		}
		getSearchGoodList(searchString, InterfaceUtils.SortStyle.DEFAULT, InterfaceUtils.SortDirect.POSITIVE, pageNum+"", ConstantValue.PAGE_COUNT+"",true);
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
			pageNum = 1;
			sortDirect = InterfaceUtils.SortDirect.POSITIVE;// 排序大小
			switch (id) {
			// 综合
			case R.id.radio_zh:
				sortStyle = InterfaceUtils.SortStyle.MULTIPLE;
				initJgButton();
				getSearchGoodList(searchString, sortStyle, sortDirect, pageNum+"", ConstantValue.PAGE_COUNT+"",true);
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				break;
			// 销量
			case R.id.radio_xl:
				sortStyle = InterfaceUtils.SortStyle.SALES;
				initJgButton();
				getSearchGoodList(searchString, sortStyle, sortDirect, pageNum+"", ConstantValue.PAGE_COUNT+"",true);
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				break;
			// 价格
			case R.id.radio_jg:
				sortStyle = InterfaceUtils.SortStyle.PRICE;
				getSearchGoodList(searchString, sortStyle, sortDirect, pageNum+"", ConstantValue.PAGE_COUNT+"",true);
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
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
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				sortStyle = InterfaceUtils.SortStyle.PRICE;
				sortDirect = InterfaceUtils.SortDirect.REVERSE;
				drawable = getResources().getDrawable(R.drawable.ic_jg2);
				priceTag = 1;
				getSearchGoodList(searchString, sortStyle, sortDirect, pageNum+"", ConstantValue.PAGE_COUNT+"",true);
			} else if (priceTag == 1) {
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				sortStyle = InterfaceUtils.SortStyle.PRICE;
				sortDirect = InterfaceUtils.SortDirect.POSITIVE;
				drawable = getResources().getDrawable(R.drawable.ic_jg1);
				priceTag = 0;
				getSearchGoodList(searchString, sortStyle, sortDirect, pageNum+"", ConstantValue.PAGE_COUNT+"",true);
			}
			drawable.setBounds(0, 0, a, a);
			btn.setCompoundDrawables(null, null, drawable, null);
			break;

		default:
			break;
		}
	}

	/**
	 * 搜索商品
	 * **/
	private void getSearchGoodList(String keyWord, String key, String order, String curpage, String pagesize,final Boolean flag) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("keyword", keyWord);
		params.addBodyParameter("key", key);
		params.addBodyParameter("order", order);
		params.addBodyParameter("curpage", curpage);
		params.addBodyParameter("pagesize", pagesize);
		System.out.println("   当前页面    ：" + curpage + "    每页数据：" + pagesize+"  关键字："+keyWord+"  排序类型："+key +" 排序方向："+order);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.GET, InterfaceUtils.getSearchGoodList(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				onLoad();
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("--===" + result);
				ObjectMapper mapper = new ObjectMapper();
				List<GoodForSearchEntity> goodSearchList = new ArrayList<GoodForSearchEntity>();// 搜索的商品列表
				try {
					GoodSearchInterfaceEntity entity = mapper.readValue(result, GoodSearchInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");
						goodSearchList = entity.getDatas();
						if (flag) {// 刷新
							lVoList.clear();
							lVoList = goodSearchList;
							if (goodSearchList.size() < 1) {
								toastShort("暂无数据");
							}
							gAdapter = new LivingSearchItemAdapter(LivingSearchActivity.this, lVoList);
							mListView.setAdapter(gAdapter);
						} else { // 加载更多
							if (goodSearchList.size() > 0) {
								lVoList.addAll(entity.getDatas());
								gAdapter.notifyDataSetChanged();
							} else {
								toastShort("没有更多数据了");
							}
						}
					} else {
						lVoList.clear();
						gAdapter = new LivingSearchItemAdapter(LivingSearchActivity.this, lVoList);
						mListView.setAdapter(gAdapter);
						toastShort("暂无数据");
					}
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				toastLong("请求失败");
				onLoad();
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
			}
		});

	}
}
