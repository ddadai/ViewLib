package com.ddd.viewlib.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MyRecyclerView extends RecyclerView{

	public MyRecyclerView(Context context) { 
		super(context);
	} 
	public MyRecyclerView(Context context, AttributeSet attrs) { 
		super(context, attrs); 
	} 
	public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) { 
		super(context, attrs, defStyle); 
	} 
	@Override 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
		int expandSpec = MeasureSpec.makeMeasureSpec(  
				Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
		super.onMeasure(widthMeasureSpec, expandSpec); 
	}

}
