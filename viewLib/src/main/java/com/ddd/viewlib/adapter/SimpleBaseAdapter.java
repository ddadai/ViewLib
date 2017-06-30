package com.ddd.viewlib.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 对于一些比较简单的可以继承这个类
 * 
 * @author ddadai
 * 
 */
public abstract class SimpleBaseAdapter extends BaseAdapter {
	protected Context mContext;
	protected List<?> models;
	protected View mConvertView;

	
	public static final int ITEM_TYPE_DEFAULT=0;
	
	public SimpleBaseAdapter(Context mContext) {
		this.mContext = mContext;
	}

	public void setModels(List<?> models) {
		this.models = models;
		notifyDataSetChanged();
	}
	
	public List getModels(){
		return models;
	}
	
	
	public void addMoldes(List models){
		if(this.models!=null){
			this.models.addAll(models);
		}else{
			this.models = models;
		}
		notifyDataSetChanged();
	}
	

	@Override
	public int getCount() {
		return models == null ? 0 : models.size();
	}

	@Override
	public Object getItem(int position) {
		return models.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		for (int i = 0; i < getViewTypeCount(); i++) {
			if(getItemViewType(position)==i){
				ViewHolder holder = null;
				if (convertView == null) {
					convertView = LayoutInflater.from(mContext).inflate(convertViewId(getItemViewType(position)),parent, false);
					mConvertView=convertView;
					holder = newViewHolder(getItemViewType(position),convertView);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				if (holder != null){
					initViewHolder(getItemViewType(position),holder, getItem(position), position);
				}
			}
		}
		return convertView;
	}

	protected static class ViewHolder {
	}
	
	
	public View getOtherView(int position, View convertView, ViewGroup parent){
		return convertView;
	};

	protected abstract int convertViewId(int itemType);
	
	protected abstract ViewHolder newViewHolder(int itemType,View convertView);

	protected abstract void initViewHolder(int itemType,ViewHolder viewHolder, Object itemModel,int position);

	protected  View findViewById(int id){
		return mConvertView.findViewById(id);
	}
}
