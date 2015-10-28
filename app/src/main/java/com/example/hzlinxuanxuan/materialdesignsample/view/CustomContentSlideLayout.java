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
public class CustomContentSlideLayout extends HorizontalScrollView {

    private int leftMenuWidth = 100, rightMenuWidth = 100;
    //如果当前有抽屉(左菜单右菜单)打开，则为true
    private boolean isOpen = false;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth;
    private boolean once;

    private ViewGroup leftMenu, content, rightMenu;

    public CustomContentSlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        once = false;
        parseAttributeSet(context, attrs);
        setHorizontalScrollBarEnabled(false);
    }

    private void parseAttributeSet(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomContentSlideLayout, 0, 0);
        leftMenuWidth = (int) ta.getDimension(R.styleable.CustomContentSlideLayout_leftMenuWidth, leftMenuWidth);
        rightMenuWidth = (int) ta.getDimension(R.styleable.CustomContentSlideLayout_rightMenuWidth, rightMenuWidth);
        ta.recycle();
    }

    private float start_x = -1, last_x = -1;
    private boolean isMove = false;
    private boolean isLeft;

    //这里要记录一开始触摸的位置，在屏幕的右半边，处理右菜单，在屏幕的左半边，处理左菜单
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isMove) {
                isMove = false;
                float end_x = event.getX();
                //往左滑
                if (last_x > end_x) {
                    //如果当前没有菜单打开,则打开右菜单
                    if (!isOpen) {
                        smoothScrollTo(leftMenuWidth + rightMenuWidth, 0);
                        isLeft = false;
                        isOpen = true;
                    } else {
                        //如果菜单打开了，若为左菜单，则关闭左菜单，若为右菜单，不变
                        if (isLeft) {
                            smoothScrollTo(leftMenuWidth, 0);
                            isLeft = false;
                            isOpen = false;
                        } else {
                            smoothScrollTo(leftMenuWidth + rightMenuWidth, 0);
                        }
                    }
                }
                //往右滑
                else if (last_x <= end_x) {
                    //如果当前没有菜单打开,则打开右菜单
                    if (!isOpen) {
                        smoothScrollTo(0, 0);
                        isOpen = true;
                        isLeft = true;
                    } else {
                        //如果菜单打开了，若为右菜单，则关闭右菜单，若为左菜单，不变
                        if (!isLeft) {
                            smoothScrollTo(leftMenuWidth, 0);
                            isOpen = false;
                            isLeft = true;
                        } else {
                            smoothScrollTo(0, 0);
                        }
                    }
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
            //记录第一次触摸的位置，并且当前没有菜单打开，否则还是操作原来的菜单
//            if (!isMove && !isOpen) {
//                isLeft = last_x < mScreenWidth / 2 ? true : false;
//            }
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
            this.scrollTo(leftMenuWidth, 0);
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
            leftMenu = (ViewGroup) wrapper.getChildAt(0);
            content = (ViewGroup) wrapper.getChildAt(1);
            rightMenu = (ViewGroup) wrapper.getChildAt(2);
            leftMenu.getLayoutParams().width = leftMenuWidth;
            rightMenu.getLayoutParams().width = rightMenuWidth;
            content.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (l <= leftMenuWidth) {
            float scale = l * 1.0f / leftMenuWidth;
            float leftScale = 0.9f - 0.3f * scale;
            float rightScale = 0.85f + scale * 0.15f;

            //对menu进行缩放
            ViewHelper.setScaleX(leftMenu, leftScale);
            ViewHelper.setScaleY(leftMenu, leftScale);
            //对menu设置透明度
            ViewHelper.setAlpha(leftMenu, 0.6f + 0.4f * (1 - scale));
            //设置menu这个view的位置
            ViewHelper.setTranslationX(leftMenu, leftScale * scale * 0.6f);

            //对menu进行旋转，有种旋转上来显示的感觉
            ViewHelper.setPivotX(leftMenu, 0);
            ViewHelper.setPivotY(leftMenu, content.getHeight() / 2);
            ViewHelper.setRotationY(leftMenu, leftScale * scale * 0.5f);

            //对content设置缩放的中轴点的x和y坐标
            ViewHelper.setPivotX(content, 0);
            ViewHelper.setPivotY(content, content.getHeight() / 2);
            ViewHelper.setScaleX(content, rightScale);
            ViewHelper.setScaleY(content, rightScale);
        }
    }
}
