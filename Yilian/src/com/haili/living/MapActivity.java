package com.haili.living;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import android.R.color;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.haili.living.activity.LivingMuseumDetailsActivity;
import com.haili.living.activity.LivingMuseumDetailsSearchActivity;
import com.haili.living.entity.StoreEntity;
import com.haili.living.utils.InterfaceUtils;
import com.haili.living.utils.JacksonUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

/**
 * Demo首页
 * */
public class MapActivity extends BaseActivity implements LocationSource, AMapLocationListener, OnCheckedChangeListener,
		OnMarkerClickListener {

	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private RadioGroup mGPSModeGroup;
	private Button btnNearShop;

	private double longitude;// 经度
	private double latitude;// 纬度
	private LatLng nowPoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 不显示程序的标题栏
		setContentView(R.layout.activity_map);
		btnNearShop = (Button) findViewById(R.id.btnNearShop);
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

		btnNearShop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getNearShop(String.valueOf(longitude), String.valueOf(latitude));
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
				toastLong("正在定位最近的生活馆...");
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
						startActivity(new Intent(MapActivity.this, LivingMuseumDetailsActivity.class).putExtra("storeId",
								storeEntity.getStore_id()));
						// toastLong("要跳转到最近的"+storeEntity.getStore_id()+"生活馆");

					} else {
						LogUtils.d("数据解析失败！");
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
				toastLong("服务器好像挂了，等一会儿再试试");
			}
		});
	}

	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
		aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);

		aMap.setOnMarkerClickListener(this);

		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.strokeColor(color.transparent);
		myLocationStyle.radiusFillColor(color.transparent);
		// myLocationStyle.strokeWidth(1000);
		aMap.setMyLocationStyle(myLocationStyle);

		// MyLocationStyle myLocationStyle = new MyLocationStyle();
		// myLocationStyle.strokeWidth(100);
		// aMap.setMyLocationStyle(myLocationStyle);
	}

	private void setMarkers(List<StoreEntity> storeList) {

		// aMap.clear();
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		if (storeList == null || storeList.size() == 0) {
			toastLong("抱歉！附近还没有生活馆！");
			return;
		}

		Iterator<StoreEntity> storeIterator = storeList.iterator();
		while (storeIterator.hasNext()) {
			StoreEntity storeEntity = storeIterator.next();
			String[] lnglatArray = storeEntity.getMaplnglat().split(",");

			Double lng = Double.valueOf(lnglatArray[0]);
			Double lat = Double.valueOf(lnglatArray[1]);
			LatLng point1 = new LatLng(lat, lng);
			CameraUpdate update = CameraUpdateFactory.changeLatLng(point1);
			aMap.moveCamera(update);
			MarkerOptions mark1 = new MarkerOptions()
					.anchor(0.5f, 0.5f)
					.position(point1)
					// .title(storeEntity.getStore_name())
					// .snippet(storeEntity.getStore_id()).icon(BitmapDescriptorFactory
					// .fromResource(R.drawable.marker))
					// .snippet(storeEntity.getStore_id())
					// .icon(BitmapDescriptorFactory
					// .defaultMarker(BitmapDescriptorFactory.HUE_RED))
					.icon(BitmapDescriptorFactory.fromView(getMarkView(storeEntity.getStore_name(), storeEntity.getStore_address())))
					.perspective(true).draggable(false)// 标记不可拖拽
					.period(50);
			mark1.visible(true);

			Marker marker = aMap.addMarker(mark1);
			marker.setObject(storeEntity.getStore_id());
		}
	}

	/**
	 * 把一个xml布局文件转化成view
	 */
	public View getMarkView(String storeName, String storeAddress) {
		View view = getLayoutInflater().inflate(R.layout.map_marker, null);
		// TextView text_title = (TextView)
		// view.findViewById(R.id.marker_title);
		TextView text_text = (TextView) view.findViewById(R.id.marker_text);
		// text_title.setText(title);
		// text_text.setText(Html.fromHtml(storeName+"<br/>"+storeAddress));
		text_text.setText(Html.fromHtml(storeName));
		return view;
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
		LogUtils.d("获取lng=" + lng + " lat=" + lat + "的附近生活馆");
		RequestParams params = new RequestParams();
		params.addBodyParameter("lng", lng);
		params.addBodyParameter("lat", lat);
		HttpUtils http = new HttpUtils();
		// http.configResponseTextCharset("GBK");
		http.send(HttpRequest.HttpMethod.POST, InterfaceUtils.getLbsShops(), params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
				toastLong("正在获取附近的生活馆....");
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

						setMarkers(storeList);

					} else {
						LogUtils.d("附近没有生活馆哦");
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
				toastLong("服务器好像挂了，等一会儿再试试");
			}
		});
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
			if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
				btnNearShop.setVisibility(View.VISIBLE);
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点

				longitude = aMap.getMyLocation().getLongitude();
				latitude = aMap.getMyLocation().getLatitude();
				nowPoint = new LatLng(latitude, longitude);

				aMap.clear();
				aMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).icon(
						BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

				aMap.addCircle(new CircleOptions().center(new LatLng(latitude, longitude)).radius(3000)
						.strokeColor(Color.argb(50, 1, 1, 1)).fillColor(Color.argb(50, 1, 1, 1)).strokeWidth(5));

				aMap.moveCamera(CameraUpdateFactory.zoomTo(20));

				getLbsShops(String.valueOf(longitude), String.valueOf(latitude));
			} else {
				Log.e("AmapErr", "Location ERR:" + amapLocation.getAMapException().getErrorCode());
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
			btnNearShop.setVisibility(View.INVISIBLE);
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用destroy()方法
			// 其中如果间隔时间为-1，则定位只定一次
			// 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
			mAMapLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 10, this);
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

	protected boolean checkInternet() {// 判断网络
		ConnectivityManager mannager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
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

	@Override
	public boolean onMarkerClick(Marker arg0) {
		if (arg0.getObject() != null && !"".equals(arg0.getObject())) {// 排除当前定位位置的mark
			String storeId = arg0.getObject().toString();
//			toastLong("要跳转到" + storeId + "生活馆");
			startActivity(new Intent(MapActivity.this, LivingMuseumDetailsActivity.class).putExtra("storeId", storeId));
		}

		return true;
	}
}
