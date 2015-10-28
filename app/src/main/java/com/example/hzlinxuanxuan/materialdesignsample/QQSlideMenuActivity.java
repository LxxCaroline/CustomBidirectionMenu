package com.example.hzlinxuanxuan.materialdesignsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by hzlinxuanxuan on 2015/10/16.
 */
public class QQSlideMenuActivity extends Activity implements DrawerLayout.DrawerListener {

    private DrawerLayout mdrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_two_way_slide);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //只有编程才能将其弹出。
        mdrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        mdrawerLayout.setDrawerListener(this);
        TextView description= (TextView) findViewById(R.id.tv_description);
        description.setText("此页面是模仿QQ菜单栏的效果，左边的DrawerLayout移动时样式为从下至上翻转，中间内容页缩小，右边菜单栏" +
                "只能通过点击按钮打开，但可以通过手势关闭");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_right_menu:
                mdrawerLayout.openDrawer(Gravity.RIGHT);
                mdrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
                break;
            case R.id.btn_next_page:
                startActivity(new Intent(QQSlideMenuActivity.this, CommonSlideMenuActivity.class));
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        View mContent = mdrawerLayout.getChildAt(0);
        View mMenu = drawerView;
        float scale = 1 - slideOffset;
        float rightScale = 0.7f + scale * 0.3f;

        if (drawerView.getTag().equals("LEFT")) {
            //这里的处理和SlideHomePage一样
            float leftScale = 1 - 0.3f * scale;

            //对menu进行缩放
            ViewHelper.setScaleX(mMenu, leftScale);
            ViewHelper.setScaleY(mMenu, leftScale);
            //对menu设置透明度
            ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
            //设置menu这个view的位置
            ViewHelper.setTranslationX(mMenu, mMenu.getMeasuredWidth() * scale * 0.7f);

            //对menu进行旋转，有种旋转上来显示的感觉
            ViewHelper.setPivotX(mMenu, 0);
            ViewHelper.setPivotY(mMenu, mContent.getHeight() / 2);
            ViewHelper.setRotationY(mMenu, mMenu.getMeasuredWidth() * scale * 0.1f);

            //对content设置缩放的中轴点的x和y坐标
            ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));
            ViewHelper.setPivotX(mContent, 0);
            ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
            ViewHelper.setScaleX(mContent, rightScale);
            ViewHelper.setScaleY(mContent, rightScale);
        } else if (drawerView.getTag().equals("RIGHT")) {
            //对menu设置透明度
            ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
            ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * (1 - scale));
            //对content设置缩放的中轴点的x和y坐标
            ViewHelper.setPivotX(mContent, mContent.getWidth());
            ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
            ViewHelper.setScaleX(mContent, rightScale);
            ViewHelper.setScaleY(mContent, rightScale);
        }

    }

    @Override
    public void onDrawerOpened(View drawerView) {
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mdrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }
}
