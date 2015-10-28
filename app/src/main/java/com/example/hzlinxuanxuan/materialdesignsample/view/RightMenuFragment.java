package com.example.hzlinxuanxuan.materialdesignsample.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hzlinxuanxuan.materialdesignsample.R;

/**
 * Created by hzlinxuanxuan on 2015/10/23.
 */
public class RightMenuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_menu, null);
        return view;
    }
}
