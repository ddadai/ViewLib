package com.ddd.viewlib.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

/**
 * Created by shi on 2017/2/24.
 */

public abstract class BasePopLayout extends FrameLayout implements View.OnClickListener {


    protected ViewGroup vg;
    protected boolean isShow = false;
    protected boolean isAnim = false;
    protected LayoutParams layoutParams;
    protected  InputMethodManager imm;


    public BasePopLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasePopLayout(Context context) {
        this(context, null);
    }

    public BasePopLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    protected void initView() {
        if(getContext() instanceof Activity){
            vg= (ViewGroup) ((Activity)getContext()).getWindow().getDecorView().getRootView().findViewById(android.R.id.content);
        }
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        if(contentViewId()>0){
            View.inflate(getContext(),contentViewId(),this);
        }
        afterContentView();
    }


    public void show() {
        View view = ((Activity)getContext()).getWindow().peekDecorView();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if (isAnim) {
            return;
        }
        if (isShow) {
            return;
        }
        isShow = true;
        if(getParent()==null&&vg!=null){
            vg.addView(this, layoutParams);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            showAnim();
        }
    }

    public void dismiss(){
        if(isAnim){
            return;
        }
        if(!isShow){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            dismissAnim();
        }else {
            if(getParent()!=null&&vg!=null){
                vg.removeView(this);
            }
            isShow = false;
        }

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void showAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(getResources().getDisplayMetrics().heightPixels, 0);
        valueAnimator.setDuration(500);
        valueAnimator.setTarget(this);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isAnim = true;

                setTranslationY((Float) animation.getAnimatedValue());
//                setTranslationY((float) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnim = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnim = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    protected void dismissAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, getResources().getDisplayMetrics().heightPixels);
        valueAnimator.setDuration(300);
        valueAnimator.setTarget(this);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isAnim = true;
                setTranslationY((Float) animation.getAnimatedValue());
//                setTranslationY((float) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(vg!=null&&getParent()!=null){
                    vg.removeView(BasePopLayout.this);
                }
                isShow = false;
                isAnim = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if(vg!=null&&getParent()!=null){
                    vg.removeView(BasePopLayout.this);
                }
                isShow = false;
                isAnim = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }


    @Override
    public void onClick(View v) {
        v.setEnabled(false);
        viewClick(v);
        v.setEnabled(true);
    }

    protected void viewClick(View v){

    }


    protected abstract int contentViewId();
    protected abstract void afterContentView();
}
