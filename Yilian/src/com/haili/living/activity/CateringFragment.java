package com.haili.living.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haili.living.R;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.interfaces.ShopSearchGoodsInterfaceEntity;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.view.XListView;
import com.haili.living.view.XListView.IXListViewListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author melody
 * @version 创建时间：2015年6月14日 上午8:09:15 类说明 餐饮
 */
@SuppressLint("ValidFragment")
public class CateringFragment extends Fragment {
	private View rootView;// 缓存Fragment view
	private XListView mlistview;
	private String life_type;// 为餐饮外卖-早餐(life_type=3），4为餐饮外卖-午餐(life_type=4），为餐饮外卖-晚餐(life_type=5）
	private String storeId;
	private List<GoodEntity> lVoList = new ArrayList<GoodEntity>();// 一般商品
	private int pageNum = 1;
	private boolean firstLoad = true;
	private GoodsItemAdapter gAdapter;

	public CateringFragment() {
		super();
	}

	public CateringFragment(String life_type, String storeId) {
		this.life_type = life_type;
		this.storeId = storeId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(R.layout.comment_layout, null);
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		initViews(rootView);
		setListeners();
		if (firstLoad) {
			getGoodListByClass(life_type, pageNum + "", true);
			firstLoad = false;
		}
		return rootView;
	}

	private void setListeners() {
		mlistview.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				pageNum = 1;
				getGoodListByClass(life_type, pageNum + "", true);
			}

			@Override
			public void onLoadMore() {
				pageNum += 1;
				getGoodListByClass(life_type, pageNum + "", false);
			}
		});
	}

	private void initViews(View rootView2) {
		mlistview = (XListView) rootView2.findViewById(R.id.mlistview);
		mlistview.setPullLoadEnable(true);
	}

	public void getGoodListByClass(String gcId, String curPage, final Boolean flag) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("life_type", gcId);
		params.addBodyParameter("store_id", storeId);
		params.addBodyParameter("curpage", curPage);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodsByTodayType(), params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				onLoad();
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
							gAdapter = new GoodsItemAdapter(getActivity(), lVoList);
							mlistview.setAdapter(gAdapter);
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
							gAdapter = new GoodsItemAdapter(getActivity(), lVoList);
							mlistview.setAdapter(gAdapter);
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
				toastShort("请求失败");
				onLoad();
			}
		});
	}

	private void onLoad() {
		mlistview.stopRefresh();
		mlistview.stopLoadMore();
		mlistview.setRefreshTime("刚刚");
	}

	private void initData() {

	}

	protected void toastShort(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
}
