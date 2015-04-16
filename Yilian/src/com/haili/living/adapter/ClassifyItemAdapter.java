package com.haili.living.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haili.living.R;
import com.haili.living.entity.ClassifyVo;

/**
 * @author melody
 * @version 创建时间：2014年11月26日 上午10:51:57 类说明
 */
public class ClassifyItemAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater layoutInflater;
	private List<ClassifyVo> list;
	private DecimalFormat df;
	private ClassifyVo selected;

	public ClassifyItemAdapter(Context mContext, List<ClassifyVo> advertlist) {
		this.mContext = mContext;
		layoutInflater = LayoutInflater.from(mContext);
		this.list = advertlist;
		df = new DecimalFormat("#0.00");
	}
	public void setSelected(ClassifyVo vo){
		selected = vo;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.classify_list_item, null);
			holder.tx_classify_name = (TextView) convertView.findViewById(R.id.tx_classify_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//选中高亮
		if (list.get(position).equals(selected)) {
			convertView.setBackgroundResource(R.drawable.classify_p);
			holder.tx_classify_name.setTextColor(Color.parseColor("#39892f"));
		}else {
			convertView.setBackgroundResource(R.drawable.classify_n);
			holder.tx_classify_name.setTextColor(Color.parseColor("#6e6d6d"));
		}
		holder.tx_classify_name.setText(list.get(position).getClassifyName());
		return convertView;
	}

	static class ViewHolder {
		TextView tx_classify_name;
	}
}
