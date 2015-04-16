package com.haili.living.utils;

import android.content.Context;

/**
 * @author melody
 * @version 创建时间：2014年10月20日 下午2:25:34 类说明
 */
public class Utils {
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
