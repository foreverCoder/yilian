package com.haili.living.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.BaseActivity;
import com.haili.living.R;
import com.haili.living.adapter.ClassifyItemAdapter;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.entity.GoodClassEntity;
import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.interfaces.GoodClassListInterfaceEntity;
import com.haili.living.entity.interfaces.GoodListInterfaceEntity;
import com.haili.living.utils.ConstantValue;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.Utils;
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

public class LivingMuseumActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, OnClickListener {
	private TextView top_title, top_right;
	private ImageView top_left;
	private EditText top_search;
	private ListView classify_list_view;
	private XListView mListView;
	private List<GoodClassEntity> cVoList = new ArrayList<GoodClassEntity>();
	private List<GoodEntity> lVoList = new ArrayList<GoodEntity>();
	private ClassifyItemAdapter cAdapter;
	private GoodsItemAdapter gAdapter;
	private int priceTag = -1;// -1 未选中 0正序 1倒叙
	private int pageNum = 1;// 当前页数
	private String sortStyle = InterfaceUtils.SortStyle.MULTIPLE;// 排序方式
	private String sortDirect = InterfaceUtils.SortDirect.POSITIVE;// 排序大小
	private ProgressDialog progressDialog;
	@ViewInject(R.id.img_btn)
	ImageButton img_btn;
	@OnClick(R.id.img_btn)
	public void search(View v) {
		((InputMethodManager) top_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(LivingMuseumActivity.this.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
		if ("".equals(top_search.getText().toString().trim()) || top_search.getText() == null) {
			Toast.makeText(LivingMuseumActivity.this, "关键字不能为空", Toast.LENGTH_SHORT).show();
		} else {
			// TODO 跳转
			Intent intent = new Intent(LivingMuseumActivity.this, LivingSearchActivity.class);
			intent.putExtra("searchValue", top_search.getText().toString().trim());
			startActivity(intent);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_living_museum);
		ViewUtils.inject(this); // 注入view和事件、
		initViews();
		setListeners();
		initData();
		getClassifyDatas();// 获取分类
	}

	private void initViews() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setIndeterminate(false);
		progressDialog.setMessage("请稍候...");
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
		top_search.setHint("请输入类别或关键字");
	}

	private void setListeners() {
		// 搜索跳转
		top_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					((InputMethodManager) top_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(LivingMuseumActivity.this.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
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
				pageNum = 1;
				getGoodListByClassify(cAdapter.getSelected().getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
			}

			@Override
			public void onLoadMore() {
				pageNum += 1;
				getGoodListByClassify(cAdapter.getSelected().getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", false);
			}
		});
		classify_list_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				cAdapter.setSelected((GoodClassEntity) cAdapter.getItem(arg2));
				cAdapter.notifyDataSetChanged();
				pageNum = 1;
				getGoodListByClassify(cVoList.get(arg2).getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
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
		mListView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
			    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
                imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);
                top_search.clearFocus();
				return false;
			}
		});
		classify_list_view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
			    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
                imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);
                top_search.clearFocus();
				return false;
			}
		});
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	private void initData() {
		if (cVoList.size() > 0) {
			cAdapter = new ClassifyItemAdapter(LivingMuseumActivity.this, cVoList);
			classify_list_view.setAdapter(cAdapter);
			cAdapter.setSelected((GoodClassEntity) cAdapter.getItem(0));
			// 根据分类查询商品 默认
			getGoodListByClassify(cVoList.get(0).getGc_id(), InterfaceUtils.SortStyle.MULTIPLE, InterfaceUtils.SortDirect.POSITIVE, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
		}
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
				initJgButton();
				sortStyle = InterfaceUtils.SortStyle.MULTIPLE;
				getGoodListByClassify(cAdapter.getSelected().getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				break;
			// 销量
			case R.id.radio_xl:
				initJgButton();
				sortStyle = InterfaceUtils.SortStyle.SALES;
				getGoodListByClassify(cAdapter.getSelected().getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				break;
			// 价格
			case R.id.radio_jg:
				sortStyle = InterfaceUtils.SortStyle.PRICE;
				getGoodListByClassify(cAdapter.getSelected().getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				break;
			// 距离
			case R.id.radio_jl:
				initJgButton();
				// sortDirect = InterfaceUtils.SortDirect.POSITIVE;// 排序大小
				// sortStyle = InterfaceUtils.SortStyle.PRICE;// TODO 距离排序
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
			if (priceTag == -1) {// 价格未选中
				drawable = getResources().getDrawable(R.drawable.ic_jg1);
				priceTag = 0;
			} else if (priceTag == 0) {// 价格降序
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				sortStyle = InterfaceUtils.SortStyle.PRICE;
				sortDirect = InterfaceUtils.SortDirect.REVERSE;
				drawable = getResources().getDrawable(R.drawable.ic_jg2);
				priceTag = 1;
				getGoodListByClassify(cAdapter.getSelected().getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
			} else if (priceTag == 1) {// 价格升序
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
				sortStyle = InterfaceUtils.SortStyle.PRICE;
				sortDirect = InterfaceUtils.SortDirect.POSITIVE;
				drawable = getResources().getDrawable(R.drawable.ic_jg1);
				priceTag = 0;
				getGoodListByClassify(cAdapter.getSelected().getGc_id(), sortStyle, sortDirect, ConstantValue.PAGE_COUNT + "", pageNum + "", true);
			}
			drawable.setBounds(0, 0, a, a);
			btn.setCompoundDrawables(null, null, drawable, null);
			break;

		default:
			break;
		}
	}

	/**
	 * 获取分类列表
	 * **/
	private void getClassifyDatas() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("style", InterfaceUtils.GoodType.ALL);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodClassify(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d(result);

				ObjectMapper mapper = new ObjectMapper();
				try {
					GoodClassListInterfaceEntity entity = mapper.readValue(result, GoodClassListInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						if (entity.hasDatas()) {
							cVoList = entity.getDatas().getGood_class();
							if (cVoList.size() > 0) {
								initData();// 初始化列表
							}
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
	 * @param flag
	 *            ture 为刷新 false为加载更多
	 */
	private void getGoodListByClassify(String gcId, String key, String order, String page, final String curpage, final Boolean flag) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("gc_id", gcId);
		params.addBodyParameter("key", key);
		params.addBodyParameter("order", order);
		params.addBodyParameter("page", page);
		params.addBodyParameter("pageToal", page);
		params.addBodyParameter("curpage", curpage);
		System.out.println("   当前页面curpage：" + curpage + "    每页数据page：" + page + "  分类ID gc_id：" + gcId + "  排序类型key：" + key + " 排序方向order：" + order);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getGoodListByClassify(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				onLoad();
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d(result);
				ObjectMapper mapper = new ObjectMapper();
				List<GoodEntity> goodList = new ArrayList<GoodEntity>();
				try {
					GoodListInterfaceEntity entity = mapper.readValue(result, GoodListInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " " + entity.getPage_total());
						goodList = entity.getDatas().getGoods_list();
						if (flag) {// 刷新
							lVoList.clear();
							lVoList = goodList;
							if (goodList.size() < 1) {
								toastShort("暂无数据");
							}
							gAdapter = new GoodsItemAdapter(LivingMuseumActivity.this, lVoList);
							mListView.setAdapter(gAdapter);
						} else { // 加载更多
							if (goodList.size() > 0) {
								lVoList.addAll(0, entity.getDatas().getGoods_list());
								lVoList.addAll(entity.getDatas().getGoods_list());
								gAdapter.notifyDataSetChanged();
							} else {
								toastShort("没有更多数据了");
							}
						}
					} else {
						if (flag) {// 刷新
							lVoList.clear();
							gAdapter = new GoodsItemAdapter(LivingMuseumActivity.this, lVoList);
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
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				onLoad();
				toastLong("请求失败");
			}
		});
	}

}
