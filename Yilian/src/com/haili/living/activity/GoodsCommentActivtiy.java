package com.haili.living.activity;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haili.living.R;
import com.haili.living.entity.Eval_infoEntity;
import com.haili.living.entity.interfaces.GoodCommentsInterfaceEntity;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.view.PagerSlidingTabStrip;
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
 * @ClassName: GoodsCommentActivtiy
 * @Description: 商品评价页面
 * @author melody
 * @date 2015-6-12 上午10:42:32
 * 
 */
public class GoodsCommentActivtiy extends FragmentActivity {
	private Eval_infoEntity eval_infoEntity;
	private ProgressDialog progressDialog;
	private String goodsId;
	private final String[] TITLES = { "全部", "好评", "中评", "差评" };
	private final String[] SUBTITLES = new String[4];
	boolean[] fragmentsUpdateFlag = { false, false, false, false };
	private Fragment[] fragments = new Fragment[4];
	@ViewInject(R.id.tabs)
	private PagerSlidingTabStrip tabs;
	@ViewInject(R.id.pager)
	private ViewPager pager;
	@ViewInject(R.id.top_left)
	private ImageView top_left;
	@ViewInject(R.id.top_title)
	private TextView top_title;
	@ViewInject(R.id.top_right)
	private ImageView top_right;

	@OnClick(R.id.top_right)
	public void goToGwc(View v) {
		Toast.makeText(GoodsCommentActivtiy.this, "购物车", Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.top_left)
	public void finsh(View v) {
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_comment);
		ViewUtils.inject(this); // 注入view和事件、
		initView();
	}

	private void initView() {
		// goodsId=getIntent().getStringExtra("goodsId");// TODO test
		goodsId = "125";
		tabs.setIndicatorColor(Color.parseColor("#fd4239"));
		progressDialog = new ProgressDialog(GoodsCommentActivtiy.this);
		progressDialog.setIndeterminate(false);
		progressDialog.setMessage("请稍候...");
		getGoodEvaluation(goodsId, "1");
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {
		FragmentManager fm;
		private String[] TITLES;
		private String[] SUBTITLES;

		public MyPagerAdapter(FragmentManager fm, String[] TITLES, String[] SUBTITLES) {
			super(fm);
			this.fm = fm;
			this.TITLES = TITLES;
			this.SUBTITLES = SUBTITLES;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		public CharSequence getSubPageTitle(int position) {
			return SUBTITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public Fragment getItem(int position) {
			return fragments[position % fragments.length];
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = (Fragment) super.instantiateItem(container, position);
			String fragmentTag = fragment.getTag();
			if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
				FragmentTransaction ft = fm.beginTransaction();
				ft.remove(fragment);
				fragment = fragments[position % fragments.length];
				ft.add(container.getId(), fragment, fragmentTag);
				ft.attach(fragment);
				ft.commit();
				// 复位更新标志
				fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
			}
			return fragment;
		}
	}

	public void getGoodEvaluation(String goodsId, String curPage) {
		RequestParams params = new RequestParams();
		System.out.println(goodsId);
		params.addBodyParameter("goods_id", goodsId);
		params.addBodyParameter("curpage", curPage);

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
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				String result = InterfaceUtils.getResponseResult(responseInfo.result);
				LogUtils.d("**" + result);
				ObjectMapper mapper = new ObjectMapper();
				try {
					GoodCommentsInterfaceEntity entity = mapper.readValue(result, GoodCommentsInterfaceEntity.class);// 接口实体类

					if (InterfaceUtils.RESULT_SUCCESS.equals(entity.getResult())) {// 如果result返回1
						LogUtils.d("entity " + entity.getCode() + " " + entity.getResult() + " ");

						if (entity.hasDatas()) {// 如果有评价内容
							eval_infoEntity = entity.getDatas().getEval_info();
							if (eval_infoEntity != null)
								initData();
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
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
			}
		});
	}

	protected void initData() {
		SUBTITLES[0] = "(" + eval_infoEntity.getAll() + ")";
		SUBTITLES[1] = "(" + eval_infoEntity.getGood() + ")";
		SUBTITLES[2] = "(" + eval_infoEntity.getNormal() + ")";
		SUBTITLES[3] = "(" + eval_infoEntity.getBad() + ")";

		fragments[0] = new CommentFragment(goodsId);
		fragments[1] = new CommentFragment(goodsId);
		fragments[2] = new CommentFragment(goodsId);
		fragments[3] = new CommentFragment(goodsId);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), TITLES, SUBTITLES));

		WindowManager wm = this.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		tabs.setTabWidth(width/4);
		tabs.setViewPager(pager);
	}

}
