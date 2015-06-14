package com.haili.living.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
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
import com.haili.living.adapter.CommentAdapter;
import com.haili.living.entity.Goods_evaluate_infoEntity;
import com.haili.living.entity.interfaces.GoodCommentsInterfaceEntity;
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

@SuppressLint("ValidFragment")
public class CommentFragment extends Fragment {
	private View rootView;// 缓存Fragment view
	private XListView mlistview;
	private String goodsId;
	private int curPage = 1;
	private boolean firstLoad = true;
	private CommentAdapter adapter;
	private List<Goods_evaluate_infoEntity> lVoList = new ArrayList<Goods_evaluate_infoEntity>();

	public CommentFragment() {
		super();
	}

	public CommentFragment(String goodsId) {
		this.goodsId = goodsId;
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
			getGoodEvaluation(goodsId, curPage);
			firstLoad = false;
		}
		return rootView;
	}

	private void setListeners() {
		mlistview.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				curPage = 1;
				getGoodEvaluation(goodsId, curPage);
			}

			@Override
			public void onLoadMore() {
				curPage += 1;
				getGoodEvaluation(goodsId, curPage);
			}
		});
	}

	private void initViews(View rootView2) {
		mlistview = (XListView) rootView2.findViewById(R.id.mlistview);
		mlistview.setPullLoadEnable(true);
	}

	/**
	 * @Title: getGoodEvaluation
	 * @Description: 获取评价
	 * @param @param goodsId
	 * @param @param curPage 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void getGoodEvaluation(String goodsId, final int curPage) {
		RequestParams params = new RequestParams();
		System.out.println(goodsId);
		params.addBodyParameter("goods_id", goodsId);
		params.addBodyParameter("curpage", curPage + "");

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodEvaluation(), params, new RequestCallBack<String>() {

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
				List<Goods_evaluate_infoEntity> goods_evaluate_infoEntities = new ArrayList<Goods_evaluate_infoEntity>();// 商品评价集合实体
				try {

					JsonNode rootNode = mapper.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").getTextValue();
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {
						GoodCommentsInterfaceEntity entity = mapper.readValue(result, GoodCommentsInterfaceEntity.class);// 接口实体类
						goods_evaluate_infoEntities = entity.getDatas().getGoods_evaluate_info();
						;
						if (curPage == 1) {// 刷新
							lVoList.clear();
							lVoList = goods_evaluate_infoEntities;
							if (goods_evaluate_infoEntities.size() < 1) {
								toastShort("暂无数据");
							}
							adapter = new CommentAdapter(getActivity(), lVoList);
							mlistview.setAdapter(adapter);
						} else { // 加载更多
							if (goods_evaluate_infoEntities.size() > 0) {
								lVoList.addAll(goods_evaluate_infoEntities);
								adapter.notifyDataSetChanged();
							} else {
								toastShort("没有更多数据了");
							}
						}
					} else {
						if (curPage == 1) {
							lVoList.clear();
							adapter = new CommentAdapter(getActivity(), lVoList);
							mlistview.setAdapter(adapter);
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
