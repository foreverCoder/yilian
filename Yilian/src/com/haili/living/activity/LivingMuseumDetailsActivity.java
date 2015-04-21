package com.haili.living.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.R;
import com.haili.living.adapter.GoodsItemAdapter;
import com.haili.living.entity.LivingGoodsVo;
import com.haili.living.utils.Utils;
import com.haili.living.view.ScrollViewExtend;
import com.haili.living.view.ScrollViewExtend.OnScrollListener1;
import com.haili.living.view.XListView;
import com.haili.living.view.XListView.IXListViewListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class LivingMuseumDetailsActivity extends Activity implements OnScrollListener1 {
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;
	private List<LivingGoodsVo> lVoList = new ArrayList<LivingGoodsVo>();
	private GoodsItemAdapter gAdapter;
	private boolean changeedGroup = true;
	public static int totalHeight;
	public static int topHeight;
	public  int screenHeight;
	@ViewInject(R.id.sv)
	private ScrollViewExtend sv;
//	@ViewInject(R.id.dong_layout)
	private LinearLayout dong_layout;
//	@ViewInject(R.id.jing_layout)
	private LinearLayout jing_layout;
	@ViewInject(R.id.top_bar)
	private View top_bar;
	@ViewInject(R.id.mlistview)
	private XListView mListView;
	@ViewInject(R.id.top_left)
	ImageView top_left;
	@ViewInject(R.id.back_top)
	ImageView back_top;
	
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

	@ViewInject(R.id.top_right)
	ImageView top_right;

	@OnClick(R.id.top_right)
	// 切换生活馆
	public void changeOtherLivingMusenum(View v) {
		Toast.makeText(LivingMuseumDetailsActivity.this, "切换生活馆", Toast.LENGTH_SHORT).show();
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
	// 特卖筛选 今日特卖 餐饮 早市特卖 晚市特卖 团购
	public void changeTm(View v) {
		switch (v.getId()) {
		// 今日特卖
		case R.id.img_jrtm:
			Toast.makeText(LivingMuseumDetailsActivity.this, "今日特卖", Toast.LENGTH_SHORT).show();
			break;
		// 餐饮
		case R.id.img_cy:
			Toast.makeText(LivingMuseumDetailsActivity.this, "餐饮", Toast.LENGTH_SHORT).show();
			break;
		// 早市特卖
		case R.id.img_zstm:
			Toast.makeText(LivingMuseumDetailsActivity.this, "早市特卖", Toast.LENGTH_SHORT).show();
			break;
		// 晚市特卖
		case R.id.img_mstm:
			Toast.makeText(LivingMuseumDetailsActivity.this, "晚市特卖", Toast.LENGTH_SHORT).show();
			break;
		// 团购
		case R.id.img_tg:
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

//	@OnRadioGroupCheckedChange(R.id.radio_group1)
//	public void screeningGoods1(RadioGroup group, int checkedId) {
//		if (changeedGroup) {
//			changeedGroup = false;
//			radio_group2.clearCheck();
//			changeedGroup = true;
//			switch (checkedId) {
//			// 粮油副食
//			case R.id.radio_lyfs:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "粮油副食", Toast.LENGTH_SHORT).show();
//				break;
//			// 休闲食品
//			case R.id.radio_xxsp:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "休闲食品", Toast.LENGTH_SHORT).show();
//				break;
//			// 酒水茶饮
//			case R.id.radio_jscy:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "酒水茶饮", Toast.LENGTH_SHORT).show();
//				break;
//			// 方便速食
//			case R.id.radio_fbss:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "方便速食", Toast.LENGTH_SHORT).show();
//				break;
//			default:
//				break;
//			}
//		}
//	}
//
//	@OnRadioGroupCheckedChange(R.id.radio_group2)
//	public void screeningGoods2(RadioGroup group, int checkedId) {
//		if (changeedGroup) {
//			changeedGroup = false;
//			radio_group1.clearCheck();
//			changeedGroup = true;
//			switch (checkedId) {
//			// 保健品
//			case R.id.radio_bjsp:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "保健品", Toast.LENGTH_SHORT).show();
//				break;
//			// 牛奶乳制品
//			case R.id.radio_nnrzp:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "牛奶乳制品", Toast.LENGTH_SHORT).show();
//				break;
//			// 生鲜
//			case R.id.radio_sx:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "生鲜", Toast.LENGTH_SHORT).show();
//				break;
//			// 餐饮
//			case R.id.radio_cy:
//				Toast.makeText(LivingMuseumDetailsActivity.this, "餐饮", Toast.LENGTH_SHORT).show();
//				break;
//			default:
//				break;
//			}
//		}
//	}

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
		dong_layout=(LinearLayout)findViewById(R.id.dong_layout);
		jing_layout=(LinearLayout)findViewById(R.id.jing_layout);
		radio_group1 = (RadioGroup) dong_layout.findViewById(R.id.radio_group1);
		radio_group2 = (RadioGroup) dong_layout.findViewById(R.id.radio_group2);
		radio_group1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (changeedGroup) {
				changeedGroup = false;
				radio_group2.clearCheck();
				changeedGroup = true;
				switch (checkedId) {
				// 粮油副食
				case R.id.radio_lyfs:
					Toast.makeText(LivingMuseumDetailsActivity.this, "粮油副食", Toast.LENGTH_SHORT).show();
					break;
				// 休闲食品
				case R.id.radio_xxsp:
					Toast.makeText(LivingMuseumDetailsActivity.this, "休闲食品", Toast.LENGTH_SHORT).show();
					break;
				// 酒水茶饮
				case R.id.radio_jscy:
					Toast.makeText(LivingMuseumDetailsActivity.this, "酒水茶饮", Toast.LENGTH_SHORT).show();
					break;
				// 方便速食
				case R.id.radio_fbss:
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
						switch (checkedId) {
						// 保健品
						case R.id.radio_bjsp:
							Toast.makeText(LivingMuseumDetailsActivity.this, "保健品", Toast.LENGTH_SHORT).show();
							break;
						// 牛奶乳制品
						case R.id.radio_nnrzp:
							Toast.makeText(LivingMuseumDetailsActivity.this, "牛奶乳制品", Toast.LENGTH_SHORT).show();
							break;
						// 生鲜
						case R.id.radio_sx:
							Toast.makeText(LivingMuseumDetailsActivity.this, "生鲜", Toast.LENGTH_SHORT).show();
							break;
						// 餐饮
						case R.id.radio_cy:
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
			}
		});
		mListView.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
			}

			@Override
			public void onLoadMore() {
			}
		});
	}

	private void initData() {
		screenHeight=findViewById(R.id.parent_layout).getHeight();
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(false);
		top_search.setPadding(Utils.dip2px(LivingMuseumDetailsActivity.this, 35), 0, 0, 0);
		details_info.setText("大溪地店地:合肥市望江西路800号创新产业园A4栋1001 电话：688888888   营业时间：8:00-22:00");
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
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		lVoList.add(new LivingGoodsVo("", "美味七七碧根果", "19.7", "250g"));
		gAdapter = new GoodsItemAdapter(LivingMuseumDetailsActivity.this, lVoList);

		mListView.setAdapter(gAdapter);
		topHeight = top_bar.getHeight();
		Utils.setListViewHeight(mListView, topHeight);
	}

	@Override
	public void onScroll(int scrollY) {
		if (scrollY>screenHeight) {
			back_top.setVisibility(View.VISIBLE);
		}else {
			back_top.setVisibility(View.INVISIBLE);
		}
		totalHeight = findViewById(R.id.mLayout).getHeight();
//		System.out.println("scrollY :" + scrollY);
//		System.out.println("totalHeight :" + totalHeight);
		int mBuyLayout2ParentTop = Math.max(scrollY, jing_layout.getTop());
		dong_layout.layout(0, mBuyLayout2ParentTop, dong_layout.getWidth(), dong_layout.getHeight() + mBuyLayout2ParentTop);
	}
}
