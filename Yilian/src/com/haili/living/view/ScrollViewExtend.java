package com.haili.living.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 能够兼容ViewPager的ScrollView
 * @Description: 解决了ViewPager在ScrollView中的滑动反弹问题

 * @File: ScrollViewExtend.java

 * @Package com.image.indicator.control

 * @Author Hanyonglu

 * @Date 2012-6-18 下午01:34:50

 * @Version V1.0
 */
public class ScrollViewExtend extends ScrollView {
    // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;
    private OnScrollListener1 onScrollListener;

	public ScrollViewExtend(Context context) {
		this(context, null);
	}
	
	public ScrollViewExtend(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScrollViewExtend(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScroll1Listener(OnScrollListener1 onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
    /**
    * 滚动的回调接口
    */
   public interface OnScrollListener1{
       /**
        * 回调方法， 返回MyScrollView滑动的Y方向距离
        * @param scrollY
        *              、
        */
       public void onScroll(int scrollY);
   }
    @Override
	protected int computeVerticalScrollRange() {
		return super.computeVerticalScrollRange();
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollListener != null){
			onScrollListener.onScroll(t);
		}
	}
    
	@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if(xDistance > yDistance){
                    return false;
                }  
        }

        return super.onInterceptTouchEvent(ev);
    }
}