package com.haili.living.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.BaseActivity;
import com.haili.living.MapActivity;
import com.haili.living.R;
import com.haili.living.adapter.OthersGoodItemAdapter;
import com.haili.living.entity.GoodEntity;
import com.haili.living.entity.Store_credit;
import com.haili.living.entity.Store_desccredit;
import com.haili.living.entity.interfaces.GoodInfoAndRecommendInterfaceEntity;
import com.haili.living.entity.interfaces.ImgsTheGoodInterfaceEntity;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.LoadNetworkPic;
import com.haili.living.utils.Utils;
import com.haili.living.view.HorizontalListView;
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

/**
 * @author melody
 * @version 创建时间：2015年4月25日 下午7:26:52 类说明
 */
public class GoodsDetailsActivity extends BaseActivity {
	private GoodEntity vo;
	private GoodEntity goodEntity;// 商品详情实体
	List<GoodEntity> goodEntityList = new ArrayList<GoodEntity>();// 推荐的商品实体列表
	private Store_credit pjVo;
	protected LoadNetworkPic imageLoader;
	private PopupWindow popWindow;
	@ViewInject(R.id.img_good)
	private ImageView img_good;
	@ViewInject(R.id.top_left)
	private ImageView top_left;
	@ViewInject(R.id.btn_fx)
	private ImageView btn_fx;
	@ViewInject(R.id.top_right)
	private ImageView top_right;

	@ViewInject(R.id.tx_goodName)
	private TextView tx_goodName;

	@ViewInject(R.id.tx_price)
	private TextView tx_price;

	@ViewInject(R.id.tx_market_price)
	private TextView tx_market_price;

	@ViewInject(R.id.tx_salesNum)
	private TextView tx_salesNum;
	@ViewInject(R.id.tx_ms)
	private TextView tx_ms;
	@ViewInject(R.id.tx_fw)
	private TextView tx_fw;
	@ViewInject(R.id.tx_fh)
	private TextView tx_fh;

	@ViewInject(R.id.horizon_listview)
	private HorizontalListView horizon_listview;

	@ViewInject(R.id.ratingBar)
	private RatingBar ratingBar;

	@OnClick(R.id.top_left)
	public void finsh(View v) {
		finish();
	}

	@OnClick(R.id.img_help)
	public void showPopWindow(View v) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View vPopWindow = inflater.inflate(R.layout.pop_window, null, false);
		popWindow = new PopupWindow(vPopWindow, LinearLayout.LayoutParams.MATCH_PARENT,Utils.dip2px(context, 50), true);
		popWindow.setOutsideTouchable(true);
		popWindow.setBackgroundDrawable(new BitmapDrawable());
		popWindow.showAsDropDown(v, 0, -(v.getHeight()+popWindow.getHeight()));
		popWindow.setFocusable(true);
		
		ImageView callImageView=(ImageView)vPopWindow.findViewById(R.id.btn_call);
		callImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		ImageView btn_help=(ImageView)vPopWindow.findViewById(R.id.btn_help);
		btn_help.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		ImageView btn_ps=(ImageView)vPopWindow.findViewById(R.id.btn_ps);
		btn_ps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}

	@OnClick(R.id.img_add)
	public void addGwc(View v) {
	}

	@OnClick(R.id.img_buy)
	public void buy(View v) {
	}

	@OnClick(R.id.btn_fx)
	public void share(View v) {
		startActivity(new Intent(GoodsDetailsActivity.this,LivingMuseumActivity.class));
		Toast.makeText(GoodsDetailsActivity.this, "分享", Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.top_right)
	public void goToGwc(View v) {
		Toast.makeText(GoodsDetailsActivity.this, "购物车", Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.img_details)
	public void goTodetails(View v) {
		Toast.makeText(GoodsDetailsActivity.this, "图文详情", Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.pj_layout)
	public void goToPjActivity(View v) {
		Toast.makeText(GoodsDetailsActivity.this, "查看评价详情", Toast.LENGTH_SHORT).show();
	}

	@ViewInject(R.id.add_m)
	private TextView add_m;

	@OnClick(R.id.add_l)
	public void reduce(View v) {
		int num = Integer.parseInt(add_m.getText().toString().trim());
		if (num > 1) {
			num--;
			add_m.setText(num + "");
		}
	}

	@OnClick(R.id.add_r)
	public void add(View v) {
		int num = Integer.parseInt(add_m.getText().toString().trim());
		num++;
		add_m.setText(num + "");
	}

	private OthersGoodItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_details);
		ViewUtils.inject(this); // 注入view和事件、
		initView();
	}

	private void initView() {
		imageLoader = new LoadNetworkPic(GoodsDetailsActivity.this);
		vo = (GoodEntity) getIntent().getSerializableExtra("vo");
		tx_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

		getImgListByGood(vo.getGoods_id());// 获取商品图片
		getShopInfoAndRecommendByGood(vo.getGoods_id());// 获取商品信息
	}

	public void getImgListByGood(String goodsId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goods_id", goodsId);

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getTheGoodImgs(), params, new RequestCallBack<String>() {

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
				List<String> imgList = new ArrayList<String>();
				try {
					ImgsTheGoodInterfaceEntity entity = mapper.readValue(result, ImgsTheGoodInterfaceEntity.class);// 接口实体类
					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");
						if (entity.hasDatas()) {
							imgList = entity.getDatas();
							Iterator<String> iterator = imgList.iterator();
							while (iterator.hasNext()) {
								String imgUrl = iterator.next();
								LogUtils.d("imgUrl  " + imgUrl);
							}
							imageLoader.DisplayImage(imgList.get(0), img_good);
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

	public void getShopInfoAndRecommendByGood(String goodsId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goods_id", goodsId);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getTheGoodInfoAndRecommends(), params, new RequestCallBack<String>() {
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
					GoodInfoAndRecommendInterfaceEntity entity = mapper.readValue(result, GoodInfoAndRecommendInterfaceEntity.class);// 接口实体类
					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						goodEntity = entity.getDatas().getGoods_info();// 获得商品详情实体
						pjVo=entity.getDatas().getStore_credit();//获取评价
						initViewData();
						if (entity.hasDatas()) {
							goodEntityList = entity.getDatas().getGoods_commend_list();// 获得推荐的商品列表
							adapter = new OthersGoodItemAdapter(context, goodEntityList);
							horizon_listview.setAdapter(adapter);
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

	private void initViewData() {
		if (goodEntity != null) {
			tx_goodName.setText(goodEntity.getGoods_name());
			tx_price.setText("￥" + goodEntity.getGoods_price());
			tx_market_price.setText("市场价:￥" + goodEntity.getGoods_marketprice());
			tx_salesNum.setText(goodEntity.getGoods_salenum() + "件");
			float starNum = Float.parseFloat(goodEntity.getEvaluation_good_star());// 星级
			ratingBar.setRating(starNum);
		}
		if(pjVo!=null){
			Store_desccredit vo1=pjVo.getStore_deliverycredit();//发货速度
			Store_desccredit vo2=pjVo.getStore_desccredit();//描述相符
			Store_desccredit vo3=pjVo.getStore_servicecredit();//服务
			tx_ms.setText(vo2.getCredit()+"");
			tx_fw.setText(vo3.getCredit()+"");
			tx_fh.setText(vo1.getCredit()+"");
			
			
			 int b=com.haili.living.utils.Utils.dip2px(getBaseContext(), 35);
			 int a=com.haili.living.utils.Utils.dip2px(getBaseContext(), 15);
			//设置整数的宽高
			Rect rect = new Rect(0, 0, a, b);
			Drawable up=getResources().getDrawable(R.drawable.img_up);
			Drawable down=getResources().getDrawable(R.drawable.img_down);
			up.setBounds(rect);
			down.setBounds(rect);
			if("低于".equals(vo1.getPercent_text())){
				tx_fh.setCompoundDrawables(null, null, down, null);
			}else{
				tx_fh.setCompoundDrawables(null, null, up, null);
			}
			if("低于".equals(vo2.getPercent_text())){
				tx_ms.setCompoundDrawables(null, null, down, null);
			}else{
				tx_ms.setCompoundDrawables(null, null, up, null);
			}
			if("低于".equals(vo3.getPercent_text())){
				tx_fw.setCompoundDrawables(null, null, down, null);
			}else{
				tx_fw.setCompoundDrawables(null, null, up, null);
			}
		}
	}

	@Override
	protected void onPause() {
		if (popWindow!=null&&popWindow.isShowing()) {
			popWindow.dismiss();
		}
		super.onPause();
	}
	
}
