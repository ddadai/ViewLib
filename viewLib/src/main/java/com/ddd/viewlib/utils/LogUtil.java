package com.ddd.viewlib.utils;


import android.util.Log;

public class LogUtil {
	public static  boolean isShow=false;
	private static final String TAG="test";
	
	public static void d(String tag,Object msg){
		if(isShow){
			Log.d(tag, msg==null?"":msg.toString());
		}
	}
	public static void d(Object msg){
		if(isShow){
			d(TAG, msg);
		}
	}
	
	
	
}
