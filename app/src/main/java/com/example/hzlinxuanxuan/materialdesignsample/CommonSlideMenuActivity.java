package com.example.hzlinxuanxuan.materialdesignsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

public class CommonSlideMenuActivity extends Activity implements DrawerLayout.DrawerListener, View.OnClickListener {

    private DrawerLayout mdrawerLayout;
    private Button btnRightMenu, btnNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_two_way_slide);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        btnRightMenu = (Button) findViewById(R.id.btn_right_menu);
        btnNextPage = (Button) findViewById(R.id.btn_next_page);
        btnRightMenu.setVisibility(View.GONE);
        btnNextPage.setOnClickListener(this);
        mdrawerLayout.setDrawerListener(this);
        TextView description = (TextView) findViewById(R.id.tv_description);
        description.setText("此页面是左右DrawerLayout移动时，中间内容页也会移动");
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        View mContent = mdrawerLayout.getChildAt(0);
        View mMenu = drawerView;
        if (mMenu == mdrawerLayout.getChildAt(1)) {
            Log.d("tag", "1");
        } else if (mMenu == mdrawerLayout.getChildAt(2)) {
            Log.d("tag", "2");
        }
        float scale = 1 - slideOffset;
        if (drawerView.getTag().equals("LEFT")) {
            ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));
        } else if (drawerView.getTag().equals("RIGHT")) {
            ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * (1 - scale));
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

    @Override
    public void onClick(View v) {
        startActivity(new Intent(CommonSlideMenuActivity.this, DrawerSlideMenuActivity.class));
    }
}