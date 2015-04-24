package com.haili.living.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haili.living.R;
import com.haili.living.entity.GoodEntity;
import com.haili.living.utils.LoadNetworkPic;

/**
 * @author melody
 * @version 创建时间：2014年11月26日 上午10:51:57 类说明
 */
public class GoodsItemAdapter extends BaseAdapter {
	protected LoadNetworkPic imageLoader;
	private Context mContext;
	private LayoutInflater layoutInflater;
	private List<GoodEntity> list;
	private DecimalFormat df;

	public GoodsItemAdapter(Context mContext, List<GoodEntity> voList) {
		this.mContext = mContext;
		layoutInflater = LayoutInflater.from(mContext);
		this.list = voList;
		imageLoader = new LoadNetworkPic(mContext);
		df = new DecimalFormat("#0.00");
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void RemoveItem(int position) {
		if (list != null) {
			list.remove(position);
		}
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.goods_item_layout, null);
			holder.goods_icon = (ImageView) convertView.findViewById(R.id.goods_icon);
			holder.goods_price = (TextView) convertView.findViewById(R.id.goods_price);
			holder.goods_name = (TextView) convertView.findViewById(R.id.goods_name);
			holder.goods_weight = (TextView) convertView.findViewById(R.id.goods_weight);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
	    imageLoader.DisplayImage(list.get(position).getGoods_image_url(), holder.goods_icon);
		holder.goods_price.setText("￥"+list.get(position).getGoods_price());
		holder.goods_name.setText(list.get(position).getGoods_name());
//		holder.goods_weight.setText(list.get(position).getGoods_salenum());
		return convertView;
	}

	static class ViewHolder {
		TextView goods_price;
		TextView goods_name;
		TextView goods_weight;
		ImageView goods_icon;
	}
}
