package com.ddd.viewlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * 解决  scrollview与listview滑动冲突的问题（已解决）
 * @author liu
 *
 */
public class MyListView extends ListView{

	public MyListView(Context context) { 
		super(context);
	} 
	public MyListView(Context context, AttributeSet attrs) { 
		super(context, attrs); 
	} 
	public MyListView(Context context, AttributeSet attrs, int defStyle) { 
		super(context, attrs, defStyle); 
	} 
	@Override 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
		int expandSpec = MeasureSpec.makeMeasureSpec(  
				Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
		super.onMeasure(widthMeasureSpec, expandSpec); 
	}
	
}
