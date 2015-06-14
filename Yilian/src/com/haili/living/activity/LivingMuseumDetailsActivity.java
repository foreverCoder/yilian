package com.haili.living.activity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.BaseActivity;
import com.haili.living.MapActivity;
import com.haili.living.R;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.StoreEntity;
import com.haili.living.entity.interfaces.ShopInfoInterfaceEntity;
import com.haili.living.entity.interfaces.ShopSearchGoodsInterfaceEntity;
import com.haili.living.utils.ConstantValue;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.LoadNetworkPic;
import com.haili.living.utils.ObjectMappingCustomer;
import com.haili.living.utils.Utils;
import com.haili.living.view.ScrollViewExtend;
import com.haili.living.view.ScrollViewExtend.OnScrollListener1;
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

public class LivingMuseumDetailsActivity extends BaseActivity implements OnScrollListener1 {
	protected LoadNetworkPic imageLoader;
	private final int pageToal = 10;
	private String storeId;// 生活馆ID
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;
	private GoodsItemAdapter gAdapter;
	private List<GoodEntity> lVoList = new ArrayList<GoodEntity>();
	private int pageNum = 1;// 当前页数
	private String searchStr;
	private boolean changeedGroup = true;
	public static int totalHeight;
	public static int topHeight;
	public int screenHeight;
	private StoreEntity storeEntity;// 生活馆信息
	@ViewInject(R.id.sv)
	private ScrollViewExtend sv;
	// @ViewInject(R.id.dong_layout)
	private LinearLayout dong_layout;
	// @ViewInject(R.id.jing_layout)
	private LinearLayout jing_layout;
	@ViewInject(R.id.top_bar)
	private View top_bar;
	@ViewInject(R.id.mlistview)
	private XListView mListView;
	@ViewInject(R.id.top_left)
	ImageView top_left;
	@ViewInject(R.id.back_top)
	ImageView back_top;
	@ViewInject(R.id.img_btn)
	ImageButton img_btn;
	@ViewInject(R.id.parent_layout)
	RelativeLayout parent_layout;
	

	@OnClick(R.id.back_top)
	public void goToTop(View v) {
		Toast.makeText(LivingMuseumDetailsActivity.this, "返回顶部", Toast.LENGTH_SHORT).show();
		sv.post(new Runnable() {
			public void run() {
				sv.fullScroll(ScrollView.FOCUS_UP);
			}
		});
	}

	@OnClick(R.id.top_left)
	public void finish(View v) {
		finish();
	}
	@OnClick(R.id.img_btn)
	public void search(View v) {
		if ("".equals(top_search.getText().toString().trim()) || top_search.getText() == null) {
			Toast.makeText(LivingMuseumDetailsActivity.this, "关键字不能为空", Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent(LivingMuseumDetailsActivity.this, LivingMuseumDetailsSearchActivity.class);
			intent.putExtra("searchValue", top_search.getText().toString().trim());
			startActivity(intent);
		}
	}
	@ViewInject(R.id.top_right)
	ImageView top_right;

	@OnClick(R.id.top_right)
	// 切换生活馆
	public void changeOtherLivingMusenum(View v) {
		startActivity(new Intent(LivingMuseumDetailsActivity.this, MapActivity.class));
//		finish(); TODO
	}

	@ViewInject(R.id.img_psfw)
	ImageButton img_psfw;

	@OnClick(R.id.img_psfw)
	// 查看配送范围
	public void goToDeliveryScopeActivity(View v) {
		Intent intent = new Intent(LivingMuseumDetailsActivity.this, DeliveryScopeActivity.class);
		intent.putExtra("store_id", storeId);// TODO
		startActivity(intent);

	}

	@ViewInject(R.id.top_search)
	EditText top_search;

	@ViewInject(R.id.details_info)
	TextView details_info;

	@ViewInject(R.id.img_jrtm)
	ImageView img_jrtm;
	@ViewInject(R.id.img_cy)
	ImageView img_cy;
	@ViewInject(R.id.img_zstm)
	ImageView img_zstm;
	@ViewInject(R.id.img_mstm)
	ImageView img_mstm;
	@ViewInject(R.id.img_tg)
	ImageView img_tg;

	@OnClick({ R.id.img_jrtm, R.id.img_cy, R.id.img_zstm, R.id.img_mstm, R.id.img_tg })
	/**
	 *  特卖筛选 今日特卖 餐饮 早市特卖 晚市特卖 团购
	 * 早市特卖（life_type=1），晚市特卖(life_type=2），为餐饮外卖-早餐(life_type=3），4为餐饮外卖-午餐(life_type=4），为餐饮外卖-晚餐(life_type=5）,为今日新品（life_type=""）
	 */
	public void changeTm(View v) {
		Intent intent = new Intent(LivingMuseumDetailsActivity.this, LivingMuseumDetailsSearchActivity.class);
		intent.putExtra("storeId", "19");// TODO 生活馆ID
		switch (v.getId()) {
		// 今日特卖
		case R.id.img_jrtm:
			intent.putExtra("searchValue", ConstantValue.S_JRXP);// TODO 类型ID
			intent.putExtra("searchType", "今日特卖");
			startActivity(intent);
			Toast.makeText(LivingMuseumDetailsActivity.this, "今日特卖", Toast.LENGTH_SHORT).show();
			break;
		// 餐饮
		case R.id.img_cy:
			// TODO 跳转
			intent.putExtra("storeId", storeId);
			intent.setClass(context, TakeOutActivity.class);
			startActivity(intent);
			Toast.makeText(LivingMuseumDetailsActivity.this, "餐饮", Toast.LENGTH_SHORT).show();
			break;
		// 早市特卖
		case R.id.img_zstm:
			intent.putExtra("searchValue", ConstantValue.S_ZSTM);
			intent.putExtra("searchType", "早市特卖");
			startActivity(intent);
			Toast.makeText(LivingMuseumDetailsActivity.this, "早市特卖", Toast.LENGTH_SHORT).show();
			break;
		// 晚市特卖
		case R.id.img_mstm:
			intent.putExtra("searchValue", ConstantValue.S_WSTM);
			intent.putExtra("searchType", "晚市特卖");
			startActivity(intent);
			Toast.makeText(LivingMuseumDetailsActivity.this, "晚市特卖", Toast.LENGTH_SHORT).show();
			break;
		// 团购
		case R.id.img_tg:
			intent.putExtra("searchType", "团购");
			intent.putExtra("storeId", storeId);
			startActivity(intent);
			Toast.makeText(LivingMuseumDetailsActivity.this, "团购", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

	// @ViewInject(R.id.radio_group1)
	RadioGroup radio_group1;
	// @ViewInject(R.id.radio_group2)
	RadioGroup radio_group2;

	// @OnRadioGroupCheckedChange(R.id.radio_group1)
	// public void screeningGoods1(RadioGroup group, int checkedId) {
	// if (changeedGroup) {
	// changeedGroup = false;
	// radio_group2.clearCheck();
	// changeedGroup = true;
	// switch (checkedId) {
	// // 粮油副食
	// case R.id.radio_lyfs:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "粮油副食",
	// Toast.LENGTH_SHORT).show();
	// break;
	// // 休闲食品
	// case R.id.radio_xxsp:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "休闲食品",
	// Toast.LENGTH_SHORT).show();
	// break;
	// // 酒水茶饮
	// case R.id.radio_jscy:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "酒水茶饮",
	// Toast.LENGTH_SHORT).show();
	// break;
	// // 方便速食
	// case R.id.radio_fbss:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "方便速食",
	// Toast.LENGTH_SHORT).show();
	// break;
	// default:
	// break;
	// }
	// }
	// }
	//
	// @OnRadioGroupCheckedChange(R.id.radio_group2)
	// public void screeningGoods2(RadioGroup group, int checkedId) {
	// if (changeedGroup) {
	// changeedGroup = false;
	// radio_group1.clearCheck();
	// changeedGroup = true;
	// switch (checkedId) {
	// // 保健品
	// case R.id.radio_bjsp:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "保健品",
	// Toast.LENGTH_SHORT).show();
	// break;
	// // 牛奶乳制品
	// case R.id.radio_nnrzp:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "牛奶乳制品",
	// Toast.LENGTH_SHORT).show();
	// break;
	// // 生鲜
	// case R.id.radio_sx:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "生鲜",
	// Toast.LENGTH_SHORT).show();
	// break;
	// // 餐饮
	// case R.id.radio_cy:
	// Toast.makeText(LivingMuseumDetailsActivity.this, "餐饮",
	// Toast.LENGTH_SHORT).show();
	// break;
	// default:
	// break;
	// }
	// }
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_living_details);
		preInitViews();
		ViewUtils.inject(this); // 注入view和事件、
		setListeners();
		initData();
	}

	private void preInitViews() {
		dong_layout = (LinearLayout) findViewById(R.id.dong_layout);
		jing_layout = (LinearLayout) findViewById(R.id.jing_layout);
		radio_group1 = (RadioGroup) dong_layout.findViewById(R.id.radio_group1);
		radio_group2 = (RadioGroup) dong_layout.findViewById(R.id.radio_group2);
		radio_group1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (changeedGroup) {
					changeedGroup = false;
					radio_group2.clearCheck();
					changeedGroup = true;
					pageNum = 1;
					switch (checkedId) {
					// 粮油副食
					case R.id.radio_lyfs:
						searchStr = ConstantValue.S_LYFS + "";
						getGoodListByClass(ConstantValue.S_LYFS + "", pageNum + "", true);
						Toast.makeText(LivingMuseumDetailsActivity.this, "粮油副食", Toast.LENGTH_SHORT).show();
						break;
					// 休闲食品
					case R.id.radio_xxsp:
						searchStr = ConstantValue.S_XXSP + "";
						getGoodListByClass(ConstantValue.S_XXSP + "", pageNum + "", true);
						Toast.makeText(LivingMuseumDetailsActivity.this, "休闲食品", Toast.LENGTH_SHORT).show();
						break;
					// 酒水茶饮
					case R.id.radio_jscy:
						searchStr = ConstantValue.S_JSCY + "";
						getGoodListByClass(ConstantValue.S_JSCY + "", pageNum + "", true);
						Toast.makeText(LivingMuseumDetailsActivity.this, "酒水茶饮", Toast.LENGTH_SHORT).show();
						break;
					// 方便速食
					case R.id.radio_fbss:
						searchStr = ConstantValue.S_FBSS + "";
						getGoodListByClass(ConstantValue.S_FBSS + "", pageNum + "", true);
						Toast.makeText(LivingMuseumDetailsActivity.this, "方便速食", Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
					}
				}
			}
		});
		radio_group2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (changeedGroup) {
					if (changeedGroup) {
						changeedGroup = false;
						radio_group1.clearCheck();
						changeedGroup = true;
						pageNum = 1;
						switch (checkedId) {
						// 保健品
						case R.id.radio_bjsp:
							searchStr = ConstantValue.S_BJP + "";
							getGoodListByClass(ConstantValue.S_BJP + "", pageNum + "", true);
							Toast.makeText(LivingMuseumDetailsActivity.this, "保健品", Toast.LENGTH_SHORT).show();
							break;
						// 牛奶乳制品
						case R.id.radio_nnrzp:
							searchStr = ConstantValue.S_NNRZP + "";
							getGoodListByClass(ConstantValue.S_NNRZP + "", pageNum + "", true);
							Toast.makeText(LivingMuseumDetailsActivity.this, "牛奶乳制品", Toast.LENGTH_SHORT).show();
							break;
						// 生鲜
						case R.id.radio_sx:
							searchStr = ConstantValue.S_SX + "";
							getGoodListByClass(ConstantValue.S_SX + "", pageNum + "", true);
							Toast.makeText(LivingMuseumDetailsActivity.this, "生鲜", Toast.LENGTH_SHORT).show();
							break;
						// 餐饮
						case R.id.radio_cy:
							searchStr = ConstantValue.S_CY + "";
							getGoodListByClass(ConstantValue.S_CY + "", pageNum + "", true);
							Toast.makeText(LivingMuseumDetailsActivity.this, "餐饮", Toast.LENGTH_SHORT).show();
							break;
						default:
							break;
						}
					}
				}
			}
		});

	}

	private void setListeners() {
		sv.setOnScroll1Listener(this);
		findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				onScroll(sv.getScrollY());
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				GoodEntity vo = (GoodEntity) gAdapter.getItem(position - 1);
				startActivity(new Intent(LivingMuseumDetailsActivity.this, GoodsDetailsActivity.class).putExtra("vo", (Serializable) vo).putExtra("sPhoneNum", storeEntity.getStore_tel()));
			}
		});
		mListView.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				pageNum = 1;
				getGoodListByClass(searchStr, pageNum + "", true);
			}

			@Override
			public void onLoadMore() {
				pageNum += 1;
				getGoodListByClass(searchStr, pageNum + "", false);
			}
		});
		top_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					((InputMethodManager) top_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(LivingMuseumDetailsActivity.this.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
					if ("".equals(top_search.getText().toString().trim()) || top_search.getText() == null) {
						Toast.makeText(LivingMuseumDetailsActivity.this, "关键字不能为空", Toast.LENGTH_SHORT).show();
					} else {
						Intent intent = new Intent(LivingMuseumDetailsActivity.this, LivingMuseumDetailsSearchActivity.class);
						intent.putExtra("searchValue", top_search.getText().toString().trim());
						startActivity(intent);
					}
					return true;
				}
				return false;
			}
		});
		top_search.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				if (arg1) {// 获得焦点
					top_left.setVisibility(View.GONE);
					top_right.setVisibility(View.GONE);
					img_btn.setVisibility(View.VISIBLE);
				} else {// 失去焦点
					top_left.setVisibility(View.VISIBLE);
					top_right.setVisibility(View.VISIBLE);
					img_btn.setVisibility(View.GONE);
				}
			}
		});
		sv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
//			    top_search.setCursorVisible(false);//失去光标
			    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
                imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);
                top_search.clearFocus();
				return false;
			}
		});
	}

	private void initData() {
		storeId = getIntent().getStringExtra("storeId");
		imageLoader = new LoadNetworkPic(LivingMuseumDetailsActivity.this);
		screenHeight = findViewById(R.id.parent_layout).getHeight();
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(false);
		top_search.setPadding(Utils.dip2px(LivingMuseumDetailsActivity.this, 35), 0, 0, 0);

		getLiveShopInfo(storeId); // 传生活馆参数 获取生活馆信息
		// getLiveShopInfo("19"); // 传生活馆参数 获取生活馆信息

		getShopHeadPic(); // 传生活馆参数 获取生活馆图片
		searchStr = ConstantValue.S_LYFS + "";
		getGoodListByClass(searchStr, pageNum + "", true);// 获取生活馆商品列表
		topHeight = top_bar.getHeight();

	}

	@Override
	public void onScroll(int scrollY) {
		if (scrollY > screenHeight) {
			back_top.setVisibility(View.VISIBLE);
		} else {
			back_top.setVisibility(View.INVISIBLE);
		}
		totalHeight = findViewById(R.id.mLayout).getHeight();
		// System.out.println("scrollY :" + scrollY);
		// System.out.println("totalHeight :" + totalHeight);
		int mBuyLayout2ParentTop = Math.max(scrollY, jing_layout.getTop());
		dong_layout.layout(0, mBuyLayout2ParentTop, dong_layout.getWidth(), dong_layout.getHeight() + mBuyLayout2ParentTop);
	}

	/**
	 * 根据生活馆id 获取生活馆信息
	 * **/
	public void getLiveShopInfo(String store_id) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("store_id", store_id);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getLiveShopInfo(), params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);
				ObjectMapper mapper = new ObjectMapper();
				try {
					ShopInfoInterfaceEntity entity = mapper.readValue(result, ShopInfoInterfaceEntity.class);// 接口实体类
					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						if (entity.hasDatas()) {
							storeEntity = entity.getDatas().get(0);
							String detailString = storeEntity.getStore_name() + ":";
							detailString += storeEntity.getStore_address() + "  电话：";
							detailString += storeEntity.getStore_tel() + "  营业时间：";
							detailString += storeEntity.getStore_workingtime();
							// details_info.setText("大溪地店地:合肥市望江西路800号创新产业园A4栋1001 电话：688888888   营业时间：8:00-22:00");
							details_info.setText(detailString);
						}
					} else {
						LogUtils.d("--------数据异常");
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
			}
		});
	}

	/**
	 * 获取首页图片并展示
	 * **/
	void getShopHeadPic() {
		RequestParams params = new RequestParams();
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getLiveShopHeadPic(), params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
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
						picMap.put(InterfaceUtils.ShopPicType.KEY_TODAY_NEW, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_TODAY_NEW).toString());
						picMap.put(InterfaceUtils.ShopPicType.KEY_DINNER_OUT, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_DINNER_OUT).toString());
						picMap.put(InterfaceUtils.ShopPicType.KEY_EVENING_SPECIAL, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_EVENING_SPECIAL).toString());
						picMap.put(InterfaceUtils.ShopPicType.KEY_MORNING_SPECIAL, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_MORNING_SPECIAL).toString());
						picMap.put(InterfaceUtils.ShopPicType.KEY_GROUP_BUY, rootNode.path("datas").path(InterfaceUtils.ShopPicType.KEY_GROUP_BUY).toString());
						imageLoader.DisplayImage(picMap.get(InterfaceUtils.ShopPicType.KEY_TODAY_NEW).replace("\"", ""), img_jrtm);
						imageLoader.DisplayImage(picMap.get(InterfaceUtils.ShopPicType.KEY_DINNER_OUT).replace("\"", ""), img_cy);
						imageLoader.DisplayImage(picMap.get(InterfaceUtils.ShopPicType.KEY_EVENING_SPECIAL).replace("\"", ""), img_mstm);
						imageLoader.DisplayImage(picMap.get(InterfaceUtils.ShopPicType.KEY_MORNING_SPECIAL).replace("\"", ""), img_zstm);
						imageLoader.DisplayImage(picMap.get(InterfaceUtils.ShopPicType.KEY_GROUP_BUY).replace("\"", ""), img_tg);
					} else {
						LogUtils.d("--------数据异常");
					}
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
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
	 * 生活馆商品列表
	 * **/
	public void getGoodListByClass(String gcId, String curPage, final Boolean flag) {
		// String storeId="19";//生活馆ID
		RequestParams params = new RequestParams();
		params.addBodyParameter("gc_id", gcId);
		params.addBodyParameter("store_id", storeId);
		params.addBodyParameter("curpage", curPage);
		params.addBodyParameter("pageToal", pageToal + "");

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodListByShopClass(), params, new RequestCallBack<String>() {

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
				// ObjectMapper mapper = new ObjectMapper();
				ObjectMappingCustomer mapper = new ObjectMappingCustomer();
				List<GoodEntity> goodEntities = new ArrayList<GoodEntity>();
				try {
					// ShopSearchGoodsInterfaceEntity<List<GoodEntity>> entity =
					// mapper.readValue(result, new
					// TypeReference<ShopSearchGoodsInterfaceEntity<List<GoodEntity>>>
					// (){});// 接口实体类
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
							gAdapter = new GoodsItemAdapter(LivingMuseumDetailsActivity.this, lVoList);
							mListView.setAdapter(gAdapter);
							Utils.setListViewHeight(mListView, topHeight);
						} else { // 加载更多
							if (goodEntities.size() > 0) {
								lVoList.addAll(entity.getDatas());
								gAdapter.notifyDataSetChanged();
								Utils.setListViewHeight(mListView, topHeight);
							} else {
								toastShort("没有更多数据了");
							}
						}
					} else {
						if (flag) {
							lVoList.clear();
							gAdapter = new GoodsItemAdapter(LivingMuseumDetailsActivity.this, lVoList);
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
				onLoad();
			}
		});
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
}
