package com.example.hzlinxuanxuan.materialdesignsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

public class ContentSlideMenuActivity extends Activity implements DrawerLayout.DrawerListener {

    private DrawerLayout mdrawerLayout;
    private Button btnNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content_slide_menu);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        btnNextPage = (Button) findViewById(R.id.btn_next_page);
        btnNextPage.setVisibility(View.INVISIBLE);
        mdrawerLayout.setDrawerListener(this);
        TextView description = (TextView) findViewById(R.id.tv_description);
        description.setText("此页面是左右DrawerLayout不移动，中间内容页移动");
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        View mContent = mdrawerLayout.getChildAt(0);
        View mMenu = drawerView;
        float scale = 1 - slideOffset;
        if (drawerView.getTag().equals("LEFT")) {
            ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));
            ViewHelper.setTranslationX(mMenu, mMenu.getMeasuredWidth() * scale);
        } else if (drawerView.getTag().equals("RIGHT")) {
            ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * (1 - scale));
            ViewHelper.setTranslationX(mMenu, -mMenu.getMeasuredWidth() * scale);
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {
    }

    @Override
    public void onDrawerClosed(View drawerView) {
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }

}