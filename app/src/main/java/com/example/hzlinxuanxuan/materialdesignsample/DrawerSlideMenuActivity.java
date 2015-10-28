package com.example.hzlinxuanxuan.materialdesignsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DrawerSlideMenuActivity extends Activity implements View.OnClickListener {

    private Button btnRightMenu, btnNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_two_way_slide);
        btnRightMenu = (Button) findViewById(R.id.btn_right_menu);
        btnNextPage = (Button) findViewById(R.id.btn_next_page);
        btnRightMenu.setVisibility(View.GONE);
        btnNextPage.setOnClickListener(this);
        TextView description= (TextView) findViewById(R.id.tv_description);
        description.setText("此页面是左右DrawerLayout移动，中间内容页不移动");
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(DrawerSlideMenuActivity.this, ContentSlideMenuActivity.class));
    }
}