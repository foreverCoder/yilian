package com.haili.living;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.haili.living.entity.Eval_infoEntity;
import com.haili.living.entity.GoodClassEntity;
import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.GoodEvaluation;
import com.haili.living.entity.GoodForSearchEntity;
import com.haili.living.entity.Goods_evaluate_infoEntity;
import com.haili.living.entity.StoreEntity;
import com.haili.living.entity.TestEntity;
import com.haili.living.entity.interfaces.GoodClassListInterfaceEntity;
import com.haili.living.entity.interfaces.GoodCommentsInterfaceEntity;
import com.haili.living.entity.interfaces.GoodEvaluationInterfaceEntity;
import com.haili.living.entity.interfaces.GoodInfoAndRecommendInterfaceEntity;
import com.haili.living.entity.interfaces.GoodListInterfaceEntity;
import com.haili.living.entity.interfaces.GoodSearchInterfaceEntity;
import com.haili.living.entity.interfaces.ImgsTheGoodInterfaceEntity;
import com.haili.living.entity.interfaces.ShopInfoInterfaceEntity;
import com.haili.living.entity.interfaces.ShopSearchGoodsInterfaceEntity;
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

	// ============================================地图接口=======================================

	@OnClick(R.id.btnLbsShops)
	public void testLbsShops(View view) {// 获取用户当前位置 3公里内的生活馆信息

		getLbsShops("117.18365190", "31.81160903");
	}

	@OnClick(R.id.btnNearShop)
	public void testNearShop(View view) {// 最近的生活馆
		getNearShop("117.18365190", "31.81160903");
	}

	// ======================================商品展示页面接口============================================
	@OnClick(R.id.btnSearchGoodList)
	public void testSearchGoodList(View view) {// 搜索指定商品
		getSearchGoodList("ddd", InterfaceUtils.SortStyle.DEFAULT, InterfaceUtils.SortDirect.POSITIVE, "1", "20");
	}

	@OnClick(R.id.btnGoodClassify)
	public void testGoodClassify(View view) {// 获取商品分类
		getGoodClassify(InterfaceUtils.GoodType.ALL);
	}

	@OnClick(R.id.btnGoodlistByClassify)
	public void testGoodListByClassify(View view) {// 根据分类获得商品列表
		getGoodListByClassify("1211", InterfaceUtils.SortStyle.MULTIPLE, InterfaceUtils.SortDirect.POSITIVE, "20", "1");
	}

	// ======================================生活馆首页接口============================================
	@OnClick(R.id.btnLiveShopInfo)
	public void testLiveShopInfo(View view) {// 获取当前生活馆信息
		getLiveShopInfo("12");// 传入生活馆ID
	}

	@OnClick(R.id.btnShopDistribution)
	public void testShopDistribution(View view) {// 获取生活馆配送范围
		getShopDistribution("19");// 传入生活馆ID
	}

	@OnClick(R.id.btnShopHeadPic)
	public void testShopHeadPic(View view) {// 获取生活馆头部四张图片
		getShopHeadPic();// 传入生活馆ID
	}

	@OnClick(R.id.btnShopSearchGood)
	public void testShopSearchGoods(View view) {// 搜索生活馆商品列表
		// getShopSearchGoods("猪肉", "1");
		// getShopSearchGoods("蔬菜", "1");
		getShopSearchGoods("测试", "1");
	}

	@OnClick(R.id.btnShopListByClass)
	public void testGoodListByClass(View view) {// 获取固定写死分类下的商品列表
		getGoodListByClass("1221", "9", "1");
	}

	// ======================================商品详情页接口============================================
	@OnClick(R.id.btnImgListByGood)
	public void testImgListByGood(View view) {// 获取指定商品的图片列表
		getImgListByGood("4");
	}

	@OnClick(R.id.btnInfoAndRecommendByGood)
	public void testInfoAndRecommendByGood(View view) {// 获取指定商品的信息和推荐商品列表
		getShopInfoAndRecommendByGood("15");
	}

	@OnClick(R.id.btnAddGoodToCart)
	public void testAddGoodToCart(View view) {// 商品添加到购物车 TODO debug
		addGoodToCart("15", 1);
	}

	@OnClick(R.id.btnGetGoodEvaluation)
	public void testGetGoodEvaluation(View view) {// 获取商品的全部评价 TODO debug
		getGoodEvaluation("125","1");//获取第一页商品评价
	}
	@OnClick(R.id.btnGetGoodBody)
	public void testGetGoodBody(View view){//获取商品的图文详情
		getGoodBody("15");
	}
	@OnClick(R.id.btnGetQuestionAndAnswer)
	public void testGetQuestionAndAnswer(View view){//获取常见疑问网页
		getQuestionAndAnswer();
	}
	
	public void getQuestionAndAnswer(){
		RequestParams params = new RequestParams();

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getQuestionAndAnswer(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

			}

			@Override
			public void onFailure(HttpException error, String msg) {
				toastLong("请求失败");
			}
		});
	}
	public void getGoodBody(String goodsId){
		RequestParams params = new RequestParams();
		params.addBodyParameter("goods_id", goodsId);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodBody(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

				
				ObjectMapper m = new ObjectMapper();

				Map<String, String> picMap = new HashMap<String, String>();
				try {
					JsonNode rootNode = m.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").getTextValue();
					LogUtils.d("getGoodBody jsonResult = " + jsonResult);
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {

						

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
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				toastLong("请求失败");
			}
		});
	}
	public void getGoodEvaluation(String goodsId,String curPage) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goods_id", goodsId);
		params.addBodyParameter("curpage", curPage);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodEvaluation(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

				ObjectMapper mapper = new ObjectMapper();
				List<Goods_evaluate_infoEntity> goods_evaluate_infoEntities = new ArrayList<Goods_evaluate_infoEntity>();// 商品评价集合实体
				Eval_infoEntity eval_infoEntity = new Eval_infoEntity();
				try {
					GoodCommentsInterfaceEntity entity = mapper.readValue(result, GoodCommentsInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

						if(entity.hasDatas()){//如果有评价内容
							goods_evaluate_infoEntities = entity.getDatas().getGoods_evaluate_info();
							eval_infoEntity = entity.getDatas().getEval_info();
							
							LogUtils.d("商品好评比为＝＝"+eval_infoEntity.getGood_percent()+"评价人:"+goods_evaluate_infoEntities.get(0).getGeval_frommembername()+" 评价商品："+goods_evaluate_infoEntities.get(0).getGeval_content());
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

	public void addGoodToCart(String goodsId, int quantity) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goods_id", goodsId);
		params.addBodyParameter("quantity", String.valueOf(quantity));
		params.addBodyParameter("key","d19ce4b6994129e732f9a8bb2e4b85f0");
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.addGoodToCart(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("addGoodToCart = " + result);

				ObjectMapper m = new ObjectMapper();

				Map<String, String> picMap = new HashMap<String, String>();
				try {
					JsonNode rootNode = m.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").getTextValue();
					LogUtils.d("addGoodToCart jsonResult = " + jsonResult);
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {

						LogUtils.d("addGoodToCart 添加商品到购物车成功");

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
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				toastLong("请求失败");
			}
		});
	}

	public void getShopInfoAndRecommendByGood(String goodsId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goods_id", goodsId);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getTheGoodInfoAndRecommends(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String historyResult = responseInfo.result;
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

				ObjectMapper mapper = new ObjectMapper();
				GoodEntity goodEntity = new GoodEntity();// 商品详情实体
				List<GoodEntity> goodEntityList = new ArrayList<GoodEntity>();// 推荐的商品实体列表
				try {
					GoodInfoAndRecommendInterfaceEntity entity = mapper.readValue(result, GoodInfoAndRecommendInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

						goodEntity = entity.getDatas().getGoods_info();// 获得商品详情实体
						LogUtils.d("商品详情" + goodEntity.getGoods_name() + "--" + goodEntity.getGoods_price());
						if (entity.hasDatas()) {
							goodEntityList = entity.getDatas().getGoods_commend_list();// 获得推荐的商品列表

							/*
							 * 业务逻辑处理 获取的图片列表
							 */
							Iterator<GoodEntity> iterator = goodEntityList.iterator();
							while (iterator.hasNext()) {
								GoodEntity recommendGood = iterator.next();

								LogUtils.d("推荐的商品是" + recommendGood.getGoods_name());
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

	public void getImgListByGood(String goodsId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goods_id", goodsId);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getTheGoodImgs(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String historyResult = responseInfo.result;
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

				ObjectMapper mapper = new ObjectMapper();
				List<String> imgList = new ArrayList<String>();
				try {
					ImgsTheGoodInterfaceEntity entity = mapper.readValue(result, ImgsTheGoodInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

						if (entity.hasDatas()) {
							imgList = entity.getDatas();

							/*
							 * 业务逻辑处理 获取的图片列表
							 */
							Iterator<String> iterator = imgList.iterator();
							while (iterator.hasNext()) {
								String imgUrl = iterator.next();

								LogUtils.d("imgUrl  " + imgUrl);
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

	public void getGoodListByClass(String gcId, String storeId, String curPage) {
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
				String historyResult = responseInfo.result;
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

				ObjectMapper mapper = new ObjectMapper();
				List<GoodEntity> goodEntities = new ArrayList<GoodEntity>();
				try {
					ShopSearchGoodsInterfaceEntity entity = mapper.readValue(result, ShopSearchGoodsInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

//						if (entity.hasDatas()) {
							goodEntities = entity.getDatas();

							/*
							 * 业务逻辑处理
							 */
							Iterator<GoodEntity> iterator = goodEntities.iterator();
							while (iterator.hasNext()) {
								GoodEntity good = iterator.next();

								LogUtils.d("good " + good.getGoods_name() + good.getGoods_price());
							}
//						}
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

	public void getShopSearchGoods(String keyWord, String curPage) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("life_search", keyWord);
		params.addBodyParameter("curpage", curPage);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getShopSearchGoods(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String historyResult = responseInfo.result;
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

				ObjectMapper mapper = new ObjectMapper();
				List<GoodEntity> goodEntities = new ArrayList<GoodEntity>();
				try {
					ShopSearchGoodsInterfaceEntity entity = mapper.readValue(result, ShopSearchGoodsInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

//						if (entity.hasDatas()) {
							goodEntities = entity.getDatas();

							/*
							 * 业务逻辑处理
							 */
							Iterator<GoodEntity> iterator = goodEntities.iterator();
							while (iterator.hasNext()) {
								GoodEntity good = iterator.next();

								LogUtils.d("good " + good.getGoods_name() + good.getGoods_price());
							}
//						}
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

	public void getShopHeadPic() {
		RequestParams params = new RequestParams();
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getLiveShopHeadPic(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("getShopHeadPic = " + result);

				ObjectMapper m = new ObjectMapper();

				Map<String, String> picMap = new HashMap<String, String>();
				try {
					JsonNode rootNode = m.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").getTextValue();
					LogUtils.d("getShopHeadPic jsonResult = " + jsonResult);
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {

						picMap.put(InterfaceUtils.ShopPicType.KEY_TODAY_NEW, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_TODAY_NEW).toString());// 今日新品
						picMap.put(InterfaceUtils.ShopPicType.KEY_DINNER_OUT, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_DINNER_OUT).toString());// 餐饮外卖
						picMap.put(InterfaceUtils.ShopPicType.KEY_EVENING_SPECIAL, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_EVENING_SPECIAL).toString());// 晚市特卖
						picMap.put(InterfaceUtils.ShopPicType.KEY_MORNING_SPECIAL, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_MORNING_SPECIAL).toString());// 早市特卖
						picMap.put(InterfaceUtils.ShopPicType.KEY_GROUP_BUY, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_GROUP_BUY).toString());// 团购

						LogUtils.d("getShopHeadPic = " + picMap.toString());

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
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				toastLong("请求失败");
			}
		});

	}

	public void getLiveShopInfo(String store_id) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("store_id", store_id);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getLiveShopInfo(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);

				ObjectMapper mapper = new ObjectMapper();
				StoreEntity storeEntity = new StoreEntity();// 获取生活馆信息
				try {
					ShopInfoInterfaceEntity entity = mapper.readValue(result, ShopInfoInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

						if (entity.hasDatas()) {
							storeEntity = entity.getDatas().get(0);

							LogUtils.d("shopInfo " + storeEntity.getStore_name());
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

	public void getShopDistribution(String storeId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("store_id", storeId);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getShopDistribution(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("getShopDistribution = " + result);

				ObjectMapper m = new ObjectMapper();
				String shopDis;
				try {
					JsonNode rootNode = m.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").toString();
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {

						shopDis = rootNode.path("datas").path("info").toString();

						LogUtils.d("shopDistribution = " + shopDis);

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
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				toastLong("请求失败");
			}
		});

	}

	private void getSearchGoodList(String keyWord, String key, String order, String curpage, String pagesize) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("keyword", keyWord);
		params.addBodyParameter("key", key);
		params.addBodyParameter("order", order);
		// params.addBodyParameter("curpage",curpage);
		// params.addBodyParameter("pagesize",pagesize);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.GET, InterfaceUtils.getSearchGoodList(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("--===" + result);

				ObjectMapper mapper = new ObjectMapper();
				List<GoodForSearchEntity> goodSearchList = new ArrayList<GoodForSearchEntity>();// 搜索的商品列表
				try {
					GoodSearchInterfaceEntity entity = mapper.readValue(result, GoodSearchInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

						if (entity.hasDatas()) {
							goodSearchList = entity.getDatas();

							/*
							 * 业务逻辑处理
							 */
							Iterator<GoodForSearchEntity> iterator = goodSearchList.iterator();
							while (iterator.hasNext()) {
								GoodForSearchEntity good = iterator.next();

								LogUtils.d("searchGood " + good.getGoods_name());
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

	/**
	 * 获取商品的所有分类，包括农庄和商城
	 * 
	 * @param style为1表示全部分类值
	 *            ，3表示开心农庄分类值
	 */
	private void getGoodClassify(String style) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("style", style);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodClassify(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d(result);

				ObjectMapper mapper = new ObjectMapper();
				List<GoodClassEntity> goodClassList = new ArrayList<GoodClassEntity>();
				try {
					GoodClassListInterfaceEntity entity = mapper.readValue(result, GoodClassListInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

						if (entity.hasDatas()) {
							goodClassList = entity.getDatas().getGood_class();

							/*
							 * 业务逻辑处理
							 */
							Iterator<GoodClassEntity> iterator = goodClassList.iterator();
							while (iterator.hasNext()) {
								GoodClassEntity goodClass = iterator.next();

								LogUtils.d("good " + goodClass.getGc_name());
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

	/**
	 * // 获取用户当前位置 3公里内的生活馆信息
	 * 
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 */
	private void getLbsShops(String lng, String lat) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("lng", lng);
		params.addBodyParameter("lat", lat);
		HttpUtils http = new HttpUtils();
		// http.configResponseTextCharset("GBK");
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getLbsShops(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d(result);

				ObjectMapper m = new ObjectMapper();
				List<StoreEntity> storeList = new ArrayList<StoreEntity>();// 生活馆列表
				try {
					JsonNode rootNode = m.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").getTextValue();
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {

						JavaType javaType = JacksonUtils.getCollectionType(ArrayList.class, StoreEntity.class);
						String jsonList = rootNode.path("datas").toString();
						// StoreEntity[] s = m.readValue(jsonList,
						// StoreEntity[].class);

						storeList = m.readValue(jsonList, javaType);

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
	 * // 获取最近的生活馆
	 * 
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 */
	private void getNearShop(String lng, String lat) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("lng", lng);
		params.addBodyParameter("lat", lat);
		HttpUtils http = new HttpUtils();
		// http.configResponseTextCharset("GBK");
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getNearShop(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d(result);

				ObjectMapper m = new ObjectMapper();
				List<StoreEntity> storeList = new ArrayList<StoreEntity>();// 生活馆列表
				StoreEntity storeEntity = new StoreEntity();
				try {
					JsonNode rootNode = m.readValue(result, JsonNode.class);
					String jsonResult = rootNode.path("result").getTextValue();
					if (InterfaceUtils.RESULT_SUCCESS.equals(jsonResult)) {

						String jsonList = rootNode.path("datas").toString();
						// StoreEntity[] s = m.readValue(jsonList,
						// StoreEntity[].class);

						storeEntity = m.readValue(jsonList, StoreEntity.class);

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
	private void getGoodListByClassify(String gcId, String key, String order, String page, String curpage) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("gc_id", gcId);
		params.addBodyParameter("key", gcId);
		params.addBodyParameter("order", order);
		// params.addBodyParameter("page", page);
		// params.addBodyParameter("curpage", curpage);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodListByClassify(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("请求服务器");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);

				LogUtils.d(result);
				// toastLong("reply: " + result);
				// showResultActivity(result);

				ObjectMapper mapper = new ObjectMapper();
				List<GoodEntity> goodList = new ArrayList<GoodEntity>();
				try {
					GoodListInterfaceEntity entity = mapper.readValue(result, GoodListInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " " + entity.getPage_total());

						if (entity.hasDatas()) {
							goodList = entity.getDatas().getGoods_list();

							/*
							 * 业务逻辑处理
							 */
							Iterator<GoodEntity> iterator = goodList.iterator();
							while (iterator.hasNext()) {
								GoodEntity good = iterator.next();

								LogUtils.d("good " + good.getEvaluation_count() + " " + good.getEvaluation_good_star() + " " + good.getGoods_id() + " " + good.getGoods_image() + " " + good.getGoods_image_url() + " "
										+ good.getGoods_marketprice() + " " + good.getGoods_name() + " " + good.getGoods_price() + " " + good.getGoods_salenum());
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
