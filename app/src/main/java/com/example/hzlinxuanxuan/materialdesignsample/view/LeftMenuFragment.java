package com.example.hzlinxuanxuan.materialdesignsample.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hzlinxuanxuan.materialdesignsample.R;

/**
 * Created by hzlinxuanxuan on 2015/10/23.
 */
public class LeftMenuFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "ddddd", Toast.LENGTH_LONG).show();
    }
}
