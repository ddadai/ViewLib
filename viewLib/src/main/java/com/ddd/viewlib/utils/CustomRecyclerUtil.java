package com.ddd.viewlib.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ddd.viewlib.R;
import com.ddd.viewlib.view.CustomScrollView;
import com.ddd.viewlib.view.OnRefreshFooterListener;
import com.ddd.viewlib.view.OnRefreshHeadListener;

public class CustomRecyclerUtil implements OnRefreshHeadListener, OnRefreshFooterListener {

	private CustomScrollView cs;
	private RecyclerView recyclerView;
	
	private Context mContextc; 
	
	public void init(Context context,View rootView){
		mContextc=context;
		if(rootView==null){
			return;
		}
		cs= (CustomScrollView) rootView.findViewById(R.id.cs);
		recyclerView= (RecyclerView) rootView.findViewById(R.id.recyclerView);
		
		LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(linearLayoutManager);
		
		
		
		cs.setModelName(context.getClass().getName());
		cs.setHeadView(false);
		cs.setFootView(true);
		
		cs.setOnRefreshHeadListener(this);
		cs.setOnRefreshFooterListener(this);
	}

	@Override
	public void headRefresh() {

	}

	@Override
	public void refreshFooter() {

	}
}
