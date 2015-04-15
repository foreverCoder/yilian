package com.haili.living;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * Demo首页
 * */
public class MapActivity extends Activity implements LocationSource,
		AMapLocationListener, OnCheckedChangeListener {

	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private RadioGroup mGPSModeGroup;

	private double longitude;//经度
	private double latitude;//纬度
	private LatLng nowPoint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 不显示程序的标题栏
		setContentView(R.layout.activity_map);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
		mGPSModeGroup = (RadioGroup) findViewById(R.id.gps_radio_group);
		mGPSModeGroup.setOnCheckedChangeListener(this);
	}

	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
		aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
		
	}
	
	private void setMarkers(){

		longitude = aMap.getMyLocation().getLongitude();
		latitude = aMap.getMyLocation().getLatitude();
		nowPoint = new LatLng(latitude, longitude);
		
		double long1 = 117.17579842;
		double lati1 = 31.84081599;
		LatLng point1 = new LatLng(lati1, long1);
		CameraUpdate update = CameraUpdateFactory.changeLatLng(point1);
		aMap.moveCamera(update);
		MarkerOptions mark1 = new MarkerOptions()
        .anchor(0.5f,0.5f)
        .position(point1)
        .title("亿联生活馆1")
        .snippet("望江西路 距离: "+AMapUtils.calculateLineDistance(nowPoint, point1)+"米").icon(BitmapDescriptorFactory
                .fromResource(R.drawable.marker))
        .perspective(true).draggable(true).period(50);
		
		
		LatLng point2 = new LatLng(31.81201021, 117.22772598);
		CameraUpdate update2 = CameraUpdateFactory.changeLatLng(point2);
		aMap.moveCamera(update2);
		MarkerOptions mark2 = new MarkerOptions()
        .anchor(0.5f,0.5f)
        .position(point2)
        .title("亿联生活馆2")
        .snippet("政务区 距离: "+AMapUtils.calculateLineDistance(nowPoint, point2)+"米").icon(BitmapDescriptorFactory
                .fromResource(R.drawable.marker))
        .perspective(true).draggable(true).period(50);
		
		LatLng point3 = new LatLng(31.85459582, 117.26351738);
		CameraUpdate update3 = CameraUpdateFactory.changeLatLng(point3);
		aMap.moveCamera(update3);
		MarkerOptions mark3 = new MarkerOptions()
        .anchor(0.5f,0.5f)
        .position(point3)
        .title("亿联生活馆3")
        .snippet("梅山路 距离: "+AMapUtils.calculateLineDistance(nowPoint, point3)+"米").icon(BitmapDescriptorFactory
                .fromResource(R.drawable.marker))
        .perspective(true).draggable(true).period(50);
		
		aMap.addMarker(mark1);
		aMap.addMarker(mark2);
		aMap.addMarker(mark3);
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.gps_locate_button:
			// 设置定位的类型为定位模式
			aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
			break;
		case R.id.gps_follow_button:
			// 设置定位的类型为 跟随模式
			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
			break;
		case R.id.gps_rotate_button:
			// 设置定位的类型为根据地图面向方向旋转
			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
			break;
		}

	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * 此方法已经废弃
	 */
	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation != null
					&& amapLocation.getAMapException().getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
				
				setMarkers();//打上标记
			} else {
				Log.e("AmapErr", "Location ERR:"
						+ amapLocation.getAMapException().getErrorCode());
			}
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用destroy()方法
			// 其中如果间隔时间为-1，则定位只定一次
			// 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
			mAMapLocationManager.requestLocationData(
					LocationProviderProxy.AMapNetwork, 60 * 1000, 10, this);
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
	}
	
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
}
