package com.haili.living;

import java.lang.reflect.Field;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class BaseActivity extends Activity {
	protected static final int LOADING_PG = 0x909;

	protected static BaseActivity context;
	protected Dialog progressDialog;
	protected final DisplayMetrics metrics = new DisplayMetrics();

	public BaseActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	protected void exitApp() {//APP程序退出
		finish();

		android.os.Process.killProcess(android.os.Process.myPid());
		System.gc();

		ActivityManager activityMgr = (ActivityManager) this
				.getSystemService(ACTIVITY_SERVICE);
		activityMgr.killBackgroundProcesses(getPackageName());
		activityMgr.restartPackage(getPackageName());
	}

	/**
	 * @Title:
	 * @Description: TODO 获取资源字符串
	 * @param @param string_id
	 * @param @return
	 * @return
	 * @throws
	 * @author chinaliteng@gmail.com
	 * @date 2014年7月18日 下午4:09:17
	 */
	protected String getResourceString(int string_id) {
		return getResources().getString(string_id);
	}

	/**
	 * @Title: 根据变量名反射获取字符串
	 * @Description: TODO
	 * @param @param var
	 * @param @return
	 * @return
	 * @throws
	 * @author chinaliteng@gmail.com
	 * @date 2014年7月23日 下午7:22:24
	 */
	protected String getResourceStringByVar(String var) {
		R.string strings = new R.string();
		try {
			Field field = R.string.class.getField(var);
			if (field == null)
				return null;
			int resId = (Integer) field.get(strings);

			return getString(resId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @Title:
	 * @Description: TODO 获取资源颜色
	 * @param @param string_id
	 * @param @return
	 * @return
	 * @throws
	 * @author chinaliteng@gmail.com
	 * @date 2014年7月18日 下午4:09:17
	 */
	protected int getResourceColor(int color_id) {
		return getResources().getColor(color_id);
	}

	/**
	 * Check whether the network connection
	 * 
	 * */
	protected boolean checkInternet() {
		ConnectivityManager mannager = (ConnectivityManager) this
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo info = mannager.getActiveNetworkInfo();
		if (info == null || !info.isConnected()) {
			return false;
		}
		if (info.isRoaming()) {
			return true;
		}
		if (info.isAvailable()) {
			return true;
		}
		return false;
	}

	protected boolean isWifiConnected() {
		WifiManager mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
		int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();
		if (mWifiManager.isWifiEnabled() && ipAddress != 0) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isTopActivity(String ActivityName) {
		boolean isTop = false;
		ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		if (cn.getClassName().contains(ActivityName)) {
			isTop = true;
		}
		return isTop;
	}

	protected void toastLong(String text) {
		Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
	}

	protected void toastShort(String text) {
		Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
	}

}
