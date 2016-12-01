package com.myprojiect.myapplication.Wight;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ScallView extends ViewGroup {

    private int mtouchSlop;
    private float mLastXIntercept=0;
    private float mLastYIntercept=0;

    private float mLastX=0;
    private float mLastY=0;
    private int leftBorder;
    private int rightBorder;


    public ScallView(Context context){
        super(context);
        init(context);
    }

    public ScallView(Context context,AttributeSet attrs){
        super(context,attrs);
        init(context);
    }


    public ScallView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(Context context){
        ViewConfiguration configuration=ViewConfiguration.get(context);
        // 获取TouchSlop值
        mtouchSlop= ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        float  xIntercept=ev.getX();
        float  yIntercept = ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercept=false;
                break;

            case MotionEvent.ACTION_MOVE:
                float deltaX=xIntercept-mLastXIntercept;
                float deltaY=yIntercept-mLastYIntercept;
                if(Math.abs(deltaX)>Math.abs(deltaY) && Math.abs(deltaX)>mtouchSlop){
                    intercept=true;
                }else{
                    intercept=false;
                }
                break;

            case MotionEvent.ACTION_UP:
                intercept=false;
                break;


        }

        mLastX=xIntercept;
        mLastY=yIntercept;
        mLastXIntercept=xIntercept;
        mLastYIntercept=yIntercept;
        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xTouch = event.getX();
        float yTouch = event.getY();
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                float deltaX=xTouch-mLastX;
                float deltaY=yTouch-mLastY;
                float scrollByStart = deltaX;
                if(getScaleX()-deltaX<leftBorder){
                    scrollByStart=getScrollX()-leftBorder;

                }else if(getScaleX()+ getWidth()-deltaX>rightBorder){
                    scrollByStart=rightBorder-getWidth()-getScaleX();
                }
                scrollBy((int) -scrollByStart, 0);
                break;

            case MotionEvent.ACTION_UP:

                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                scrollTo(getScrollX()+dx,0);
                break;
        }

        mLastX = xTouch;
        mLastY = yTouch;
        return super.onTouchEvent(event);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount=getChildCount();
        for(int i = 0; i < childCount; i++){
            View childView = getChildAt(i);

            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount=getChildCount();
            for(int i=0;i<childCount;i++){
                View childView=getChildAt(i);
                childView.layout(i*getMeasuredWidth(),0,i*getMeasuredWidth()+childView.getMeasuredWidth()+getPaddingLeft(),childView.getMeasuredWidth());


            }

            leftBorder=0;
            rightBorder=getChildCount()*getMeasuredWidth();



        }



    }
}
