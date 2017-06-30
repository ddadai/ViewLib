package com.wheel.widget.popview;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ddd.viewlib.R;
import com.ddd.viewlib.view.BasePopLayout;
import com.wheel.widget.view.WheelView;

public abstract class BaseWheelPopLayout extends BasePopLayout{


	protected int width;
	protected int height;
	protected boolean isShowCenter=true;//显示中间的那条横线

	protected WheelView wv1,wv2,wv3;
	protected WheelView wv4,wv5,wv6;
	protected View centerView,okView;
	protected TextView popTitleTv;

	protected WheelView wvs[];

	public BaseWheelPopLayout(Context context) {
		super(context);
	}


//	protected void noViewClick() {
//		dismiss();
//	}
	
	/**
	 * 设置是否显示中间的横条
	 * @param color 输入0xffffffff 格式的8位16进制的色值
	 */
	public void setShowCengter(int color){
		try {
			centerView.setVisibility(View.VISIBLE);
			centerView.setBackgroundColor(color);
		} catch (Exception e) {
			e.printStackTrace();
			centerView.setVisibility(View.GONE);
		}
	}
	
	
	/**
	 * 设置是否显示中间的横条
	 * @param show
	 */
	public void setShowCengter(boolean show){
		if(show){
			centerView.setVisibility(View.VISIBLE);
			centerView.setBackgroundColor(0x11111111);
		}else{
			centerView.setVisibility(View.GONE);
		}
	}
	
	
	
	protected void okClick(){
		dismiss();
	}
	protected void rootViewClick(){
		dismiss();
	}

	public void setTitle(String title){
		if(popTitleTv!=null){
			popTitleTv.setText(title);
		}
	}
	
	/**
	 * 设置是否循环
	 * @param isCyclic
	 */
	public void setCyclic(boolean isCyclic){
		for (int i = 0; i < wvs.length; i++) {
			wvs[i].setCyclic(isCyclic);
		}
	}
	/**
	 * 设置是否循环
	 * @param isCyclics
	 */
	public void setCyclic(boolean[] isCyclics){
		for (int i = 0; i < wvs.length; i++) {
			wvs[i].setCyclic(isCyclics[i]);
		}
	}
	
	
	
	


	@Override
	protected int contentViewId() {
		return R.layout.wheel_pop_layout;
	}

	@Override
	protected void afterContentView() {
		centerView= findViewById(R.id.centerView);
		okView= findViewById(R.id.popOkTv);
//		noView= findViewById(R.id.noView);
		popTitleTv= (TextView) findViewById(R.id.popTitleTv);
		wv1=(WheelView) findViewById(R.id.wv1);
		wv2=(WheelView) findViewById(R.id.wv2);
		wv3=(WheelView) findViewById(R.id.wv3);
		wv4=(WheelView) findViewById(R.id.wv4);
		wv5=(WheelView) findViewById(R.id.wv5);
		wv6=(WheelView) findViewById(R.id.wv6);

		wvs=new WheelView[6];
		wvs[0]=wv1;
		wvs[1]=wv2;
		wvs[2]=wv3;
		wvs[3]=wv4;
		wvs[4]=wv5;
		wvs[5]=wv6;

		okView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				okClick();
			}
		});
		findViewById(R.id.wheelRootView).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				rootViewClick();
			}
		});
//		noView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				noViewClick();
//			}
//		});
		initWheelView();
	}


	protected abstract void initWheelView();
}
