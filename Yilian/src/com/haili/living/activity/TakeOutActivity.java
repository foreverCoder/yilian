package com.haili.living.activity;

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
import com.haili.living.utils.ConstantValue;
import com.haili.living.view.PagerSlidingTabStrip;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * @author melody 早餐 午餐 晚餐
 * @version 创建时间：2015年6月13日 上午10:52:25 类说明
 */
public class TakeOutActivity extends FragmentActivity {
	private String storeId;
	private final String[] TITLES = { "早餐", "午餐", "晚餐" };
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
		Toast.makeText(TakeOutActivity.this, "购物车", Toast.LENGTH_SHORT).show();
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
		storeId = getIntent().getStringExtra("storeId");// TODO test
		top_title.setText("餐饮");
		tabs.setIndicatorColor(Color.parseColor("#fd4239"));
		initData();
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

	protected void initData() {

		fragments[0] = new CateringFragment(ConstantValue.S_CYWM_A, storeId);
		fragments[1] = new CateringFragment(ConstantValue.S_CYWM_P, storeId);
		fragments[2] = new CateringFragment(ConstantValue.S_CYWM_D, storeId);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), TITLES, null));

		WindowManager wm = this.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		tabs.setTabWidth(width / 3);
		tabs.setViewPager(pager);
	}

}
