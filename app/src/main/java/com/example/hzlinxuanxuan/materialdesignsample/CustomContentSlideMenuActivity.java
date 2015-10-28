package com.example.hzlinxuanxuan.materialdesignsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by hzlinxuanxuan on 2015/10/27.
 */
public class CustomContentSlideMenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_content_slide);
        Button btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomContentSlideMenuActivity.this, CommonSlideMenuActivity.class));
            }
        });
    }
}
