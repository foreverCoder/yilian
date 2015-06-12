package com.haili.living.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haili.living.R;
import com.haili.living.entity.Goods_evaluate_infoEntity;
import com.haili.living.utils.LoadNetworkPic;

public class CommentAdapter extends BaseAdapter {
	protected LoadNetworkPic imageLoader;
	private Context mContext;
	private LayoutInflater layoutInflater;
	private List<Goods_evaluate_infoEntity> goods_evaluate_infoEntities = new ArrayList<Goods_evaluate_infoEntity>();// 商品评价集合实体

	public CommentAdapter(Context mContext, List<Goods_evaluate_infoEntity> goods_evaluate_infoEntities) {
		super();
		this.mContext = mContext;
		layoutInflater = LayoutInflater.from(mContext);
		imageLoader = new LoadNetworkPic(mContext);
		this.goods_evaluate_infoEntities = goods_evaluate_infoEntities;
	}

	@Override
	public int getCount() {
		return goods_evaluate_infoEntities.size();
	}

	@Override
	public Object getItem(int arg0) {
		return goods_evaluate_infoEntities.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_comment, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.avator = (ImageView) convertView.findViewById(R.id.avator);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		imageLoader.DisplayImage(goods_evaluate_infoEntities.get(position).getAvator(), holder.avator);
		holder.tv_name.setText(goods_evaluate_infoEntities.get(position).getGeval_frommembername());
		holder.tv_content.setText(goods_evaluate_infoEntities.get(position).getGeval_content());

		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		holder.tv_time.setText(format.format(goods_evaluate_infoEntities.get(position).getGeval_addtime()));
		return convertView;
	}

	static class ViewHolder {
		TextView tv_name;
		TextView tv_content;
		TextView tv_time;
		ImageView avator;
	}
}
