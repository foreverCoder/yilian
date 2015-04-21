package com.haili.living;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.amap.api.mapcore.util.n;
import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.StoreEntity;
import com.haili.living.entity.interfaces.GoodListInterfaceEntity;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.JacksonUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.annotation.SuppressLint;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@ContentView(R.layout.activity_interface_test)
public class InterfaceTestActivity extends BaseActivity {
	static class HandleMsg {
		static int ERROR = -1;// 请求失败
		static int NO_FAULTS = 0;// 没有历史故障
		static int SUCCESS = 1;// 请求成功

	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
	};

	@OnClick(R.id.btnSearchGoodList)
	public void testSearchGoodList(View view) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("$goods_id", "20");
		// params.addQueryStringParameter("$key",
		// InterfaceUtils.SortStyle.MULTIPLE);//排序类型综合
		// params.addQueryStringParameter("$order",InterfaceUtils.SortDirect.REVERSE
		// );//排序方向倒序
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				InterfaceUtils.getSearchGoodList(), params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						toastLong("请求服务器");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = InterfaceUtils
								.getResponseResult(responseInfo.result);
						LogUtils.d(result);
						toastLong("reply: " + result);
						showResultActivity(result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						toastLong("请求失败");
					}
				});
	}

	@OnClick(R.id.btnGoodlistByClassify)
	public void testGoodListByClassify(View view) {
		getGoodListByClassify("1211", InterfaceUtils.SortStyle.MULTIPLE,
				InterfaceUtils.SortDirect.POSITIVE, "20", "1");
	}

	@OnClick(R.id.btnGoodClassify)
	public void testGoodClassify(View view) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("style", "1");
		// params.addQueryStringParameter("style", "1");
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				InterfaceUtils.getGoodClassify(), params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						toastLong("请求服务器");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = InterfaceUtils
								.getResponseResult(responseInfo.result);
						LogUtils.d(result);
						toastLong("reply: " + result);
						showResultActivity(result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						toastLong("请求失败");
					}
				});
	}

	@OnClick(R.id.btnLbsShops)
	public void testLbsShops(View view) {// 获取用户当前位置 3公里内的生活馆信息

		getLbsShops("117.18365190", "31.81160903");
	}

	/**
	 * // 获取用户当前位置 3公里内的生活馆信息
	 * 
	 * @param lng
	 * @param lat
	 */
	private void getLbsShops(String lng, String lat) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("lng", lng);
		params.addBodyParameter("lat", lat);
		HttpUtils http = new HttpUtils();
		// http.configResponseTextCharset("GBK");
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getLbsShops(),
				params, new RequestCallBack<String>() {

					@Override
					public void onStart() {
						toastLong("请求服务器");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = InterfaceUtils
								.getResponseResult(responseInfo.result);
						LogUtils.d(result);

						ObjectMapper m = new ObjectMapper();
						List<StoreEntity> storeList = new ArrayList<StoreEntity>();//生活馆列表
						try {
							JsonNode rootNode = m.readValue(result,
									JsonNode.class);
							String jsonResult = rootNode.path("result")
									.getTextValue();
							if (InterfaceUtils.RESULT_SUCCESS
									.equals(jsonResult)) {

								JavaType javaType = JacksonUtils
										.getCollectionType(ArrayList.class,
												StoreEntity.class);
								String jsonList = rootNode.path("datas").toString();
//								StoreEntity[] s = m.readValue(jsonList,
//										StoreEntity[].class);
								
								storeList = m.readValue(jsonList,
										javaType);

							} else {
								LogUtils.d("--------数据异常");
							}
						} catch (JsonParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// toastLong("reply: " + result);
						// showResultActivity(result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						toastLong("请求失败");
					}
				});
	}

	/**
	 * @param gcId
	 *            分类ID
	 * @param key
	 *            排序类型
	 * @param order
	 *            排序方向
	 * @param page
	 *            每页数目
	 * @param curpage
	 *            当前页
	 */
	private void getGoodListByClassify(String gcId, String key, String order,
			String page, String curpage) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("gc_id", gcId);
		params.addBodyParameter("key", gcId);
		params.addBodyParameter("order", order);
		// params.addBodyParameter("page", page);
		// params.addBodyParameter("curpage", curpage);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				InterfaceUtils.getGoodListByClassify(), params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						toastLong("请求服务器");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = InterfaceUtils
								.getResponseResult(responseInfo.result);

						LogUtils.d(result);
						// toastLong("reply: " + result);
						// showResultActivity(result);

						ObjectMapper mapper = new ObjectMapper();
						List<GoodEntity> goodList = new ArrayList<GoodEntity>();
						try {
							GoodListInterfaceEntity entity = mapper.readValue(
									result, GoodListInterfaceEntity.class);// 接口实体类

							if (InterfaceUtils.RESULT_SUCCESS.equals(entity
									.getResult())) {// 如果result返回1
								LogUtils.d("entity " + entity.getCode() + " "
										+ entity.getResult() + " "
										+ entity.getPage_total());

								if (entity.hasDatas()) {
									goodList = entity.getDatas()
											.getGoods_list();

									/*
									 * 业务逻辑处理
									 */
									Iterator<GoodEntity> iterator = goodList
											.iterator();
									while (iterator.hasNext()) {
										GoodEntity good = iterator.next();

										LogUtils.d("good "
												+ good.getEvaluation_count()
												+ " "
												+ good.getEvaluation_good_star()
												+ " " + good.getGoods_id()
												+ " " + good.getGoods_image()
												+ " "
												+ good.getGoods_image_url()
												+ " "
												+ good.getGoods_marketprice()
												+ " " + good.getGoods_name()
												+ " " + good.getGoods_price()
												+ " " + good.getGoods_salenum());
									}
								}
							} else {
								LogUtils.d("--------数据异常");
							}
						} catch (JsonParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						toastLong("请求失败");
					}
				});
	}

	private void showResultActivity(String result) {
		Intent intent = new Intent();
		intent.putExtra("result", result);
		intent.setClass(InterfaceTestActivity.this, InterfaceShowActivity.class);
		startActivity(intent);
	}
}
