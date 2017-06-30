package com.ddd.viewlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.GridView;
import android.widget.ListView;
/**
 * 解决  scrollview与listview滑动冲突的问题（已解决）
 * @author liu
 *
 */
public class MyGridView extends GridView{

	public MyGridView(Context context) { 
		super(context);
	} 
	public MyGridView(Context context, AttributeSet attrs) { 
		super(context, attrs); 
	} 
	public MyGridView(Context context, AttributeSet attrs, int defStyle) { 
		super(context, attrs, defStyle); 
	} 
	@Override 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
		int expandSpec = MeasureSpec.makeMeasureSpec(  Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
		super.onMeasure(widthMeasureSpec, expandSpec); 
//		if(getChildCount()!=0){
//			View childAt = getChildAt(0);
//			childAt.measure(MeasureSpec.EXACTLY, MeasureSpec.EXACTLY);
//			int measuredHeight = childAt.getMeasuredWidth();
//			int expandSpec = MeasureSpec.makeMeasureSpec(  
//					measuredHeight*getChildCount(), MeasureSpec.AT_MOST);  
//			super.onMeasure(widthMeasureSpec, expandSpec); 
//		}else{
//			int expandSpec = MeasureSpec.makeMeasureSpec(  
//					Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
//			super.onMeasure(widthMeasureSpec, expandSpec); 
////			super.onMeasure(widthMeasureSpec, heightMeasureSpec); 
//		}
	}
	
}
