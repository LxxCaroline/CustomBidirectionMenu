package com.example.hzlinxuanxuan.materialdesignsample.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Created by hzlinxuanxuan on 2015/10/27.
 */
public class CustomDrawerLayout extends DrawerLayout {

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        //当i=0的时候是内容
        if (i == 0)
            return 2;
        //左菜单
        if (i == 1)
            return 1;
        //右
        if (i == 2)
            return 0;
        return super.getChildDrawingOrder(childCount, i);
    }
}
