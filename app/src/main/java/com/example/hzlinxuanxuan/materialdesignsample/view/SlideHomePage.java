package com.example.hzlinxuanxuan.materialdesignsample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.hzlinxuanxuan.materialdesignsample.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by hzlinxuanxuan on 2015/10/15.
 */
public class SlideHomePage extends HorizontalScrollView {

    private int menuWidth = 100;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth;
    private boolean once;

    private ViewGroup menu, content;

    public SlideHomePage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        once = false;
        parseAttributeSet(context, attrs);
        setHorizontalScrollBarEnabled(false);
    }

    private void parseAttributeSet(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ScrollHomePage, 0, 0);
        menuWidth = (int) ta.getDimension(R.styleable.ScrollHomePage_menuWidth, menuWidth);
        ta.recycle();
    }

    private float start_x = -1, last_x = -1;
    private boolean isMove = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isMove) {
                isMove = false;
                float end_x = event.getX();
                if (last_x > end_x) {
                    //向左移动，收起menu
                    smoothScrollTo(menuWidth, 0);
                } else {
                    smoothScrollTo(0, 0);
                }
                start_x = -1;
                last_x = -1;
            }
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (last_x == -1) {
                last_x = event.getX();
            } else
                last_x = start_x;
            start_x = event.getX();
            isMove = true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            // 将菜单隐藏
            this.scrollTo(menuWidth, 0);
            once = true;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 显示的设置一个宽度
         */
        if (!once) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            menu = (ViewGroup) wrapper.getChildAt(0);
            content = (ViewGroup) wrapper.getChildAt(1);
            menu.getLayoutParams().width = menuWidth;
            content.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / menuWidth;
        float leftScale = 0.9f - 0.3f * scale;
        float rightScale = 0.85f + scale * 0.15f;

        //对menu进行缩放
        ViewHelper.setScaleX(menu, leftScale);
        ViewHelper.setScaleY(menu, leftScale);
        //对menu设置透明度
        ViewHelper.setAlpha(menu, 0.6f + 0.4f * (1 - scale));
        //设置menu这个view的位置
        ViewHelper.setTranslationX(menu, menuWidth * scale * 0.6f);

        //对menu进行旋转，有种旋转上来显示的感觉
        ViewHelper.setPivotX(menu, 0);
        ViewHelper.setPivotY(menu, content.getHeight() / 2);
        ViewHelper.setRotationY(menu, menuWidth * scale * 0.1f);

        //对content设置缩放的中轴点的x和y坐标
        ViewHelper.setPivotX(content, 0);
        ViewHelper.setPivotY(content, content.getHeight() / 2);
        ViewHelper.setScaleX(content, rightScale);
        ViewHelper.setScaleY(content, rightScale);
    }
}
