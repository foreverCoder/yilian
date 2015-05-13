package com.haili.living.activity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.interfaces.ShopSearchGoodsInterfaceEntity;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.LoadNetworkPic;
import com.haili.living.view.XListView;
import com.haili.living.view.XListView.IXListViewListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class LivingMuseumDetailsSearchActivity extends BaseActivity {
	protected LoadNetworkPic imageLoader;
	private ProgressDialog progressDialog;
	private GoodsItemAdapter gAdapter;
	private List<GoodEntity> lVoList = new ArrayList<GoodEntity>();
	private int pageNum = 1;// 当前页数
	private String searchStr;
	private String searchType;
	private String storeId;
	@ViewInject(R.id.top_bar)
	private View top_bar;
	@ViewInject(R.id.mlistview)
	private XListView mListView;
	@ViewInject(R.id.top_left)
	ImageView top_left;

	@OnClick(R.id.top_left)
	public void finish(View v) {
		finish();
	}

	@ViewInject(R.id.top_title)
	TextView top_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_living_details_search);
		preInitViews();
		ViewUtils.inject(this); // 注入view和事件、
		setListeners();
		initData();
	}

	private void preInitViews() {
		progressDialog = new ProgressDialog(LivingMuseumDetailsSearchActivity.this);
		progressDialog.setIndeterminate(false);
		progressDialog.setMessage("请稍候...");
	}

	private void setListeners() {
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				GoodEntity vo = (GoodEntity) gAdapter.getItem(position - 1);
				startActivity(new Intent(LivingMuseumDetailsSearchActivity.this, GoodsDetailsActivity.class).putExtra("vo", (Serializable) vo));
			}
		});
		mListView.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				pageNum = 1;
				if (searchType != null) {
					getGoodListByClass(searchStr, pageNum + "", false);
				} else {
					getShopSearchGoods(searchStr, pageNum + "", false);// 获取生活馆商品列表
				}
			}

			@Override
			public void onLoadMore() {
				pageNum += 1;
				if (searchType != null) {
					getGoodListByClass(searchStr, pageNum + "", false);
				} else {
					getShopSearchGoods(searchStr, pageNum + "", false);// 获取生活馆商品列表
				}
			}
		});
	}

	private void initData() {
		imageLoader = new LoadNetworkPic(LivingMuseumDetailsSearchActivity.this);
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(true);

		searchStr = getIntent().getStringExtra("searchValue");
		searchType = getIntent().getStringExtra("searchType");
		storeId = getIntent().getStringExtra("storeId");
		if (searchType != null) {
			top_title.setText(searchType);
			getGoodListByClass(searchStr, pageNum + "", true);
		} else {
			top_title.setText("搜索  " + searchStr);
			getShopSearchGoods(searchStr, pageNum + "", true);// 获取生活馆商品列表
		}
		if (!progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	/**
	 * 生活馆商品列表
	 * **/
	public void getShopSearchGoods(String keyWord, String curPage, final Boolean flag) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("life_search", keyWord);
		params.addBodyParameter("curpage", curPage);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getShopSearchGoods(), params, new RequestCallBack<String>() {
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
				LogUtils.d("**" + result);
				ObjectMapper mapper = new ObjectMapper();
				List<GoodEntity> goodEntities = new ArrayList<GoodEntity>();
				try {
					ShopSearchGoodsInterfaceEntity entity = mapper.readValue(result, ShopSearchGoodsInterfaceEntity.class);// 接口实体类
					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");
						goodEntities = entity.getDatas();
						if (flag) {// 刷新
							lVoList.clear();
							lVoList = goodEntities;
							if (lVoList.size() < 1) {
								toastShort("暂无数据");
							}
							gAdapter = new GoodsItemAdapter(LivingMuseumDetailsSearchActivity.this, lVoList);
							mListView.setAdapter(gAdapter);
						} else { // 加载更多
							if (goodEntities.size() > 0) {
								lVoList.addAll(entity.getDatas());
								gAdapter.notifyDataSetChanged();
							} else {
								toastShort("没有更多数据了");
							}
						}
					} else {
						if (flag) {
							lVoList.clear();
							gAdapter = new GoodsItemAdapter(LivingMuseumDetailsSearchActivity.this, lVoList);
							mListView.setAdapter(gAdapter);
							toastShort("暂无数据");
						} else {
							toastShort("没有更多数据了");
						}

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
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
			}
		});
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	/**
	 * 生活馆商品列表
	 * **/
	public void getGoodListByClass(String gcId, String curPage, final Boolean flag) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("gc_id", gcId);
		params.addBodyParameter("store_id", storeId);
		params.addBodyParameter("curpage", curPage);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodListByShopClass(), params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);
				ObjectMapper mapper = new ObjectMapper();
				List<GoodEntity> goodEntities = new ArrayList<GoodEntity>();
				try {
					ShopSearchGoodsInterfaceEntity entity = mapper.readValue(result, ShopSearchGoodsInterfaceEntity.class);// 接口实体类
					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");
						goodEntities = entity.getDatas();
						if (flag) {// 刷新
							lVoList.clear();
							lVoList = goodEntities;
							if (lVoList.size() < 1) {
								toastShort("暂无数据");
							}
							gAdapter = new GoodsItemAdapter(LivingMuseumDetailsSearchActivity.this, lVoList);
							mListView.setAdapter(gAdapter);
						} else { // 加载更多
							if (goodEntities.size() > 0) {
								lVoList.addAll(entity.getDatas());
								gAdapter.notifyDataSetChanged();
							} else {
								toastShort("没有更多数据了");
							}
						}
					} else {
						if (flag) {
							lVoList.clear();
							gAdapter = new GoodsItemAdapter(LivingMuseumDetailsSearchActivity.this, lVoList);
							mListView.setAdapter(gAdapter);
							toastShort("暂无数据");
						} else {
							toastShort("没有更多数据了");
						}
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
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
			}
		});
	}
}
