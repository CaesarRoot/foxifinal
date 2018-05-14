package com.easylife.activity;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easylife.adapter.RelaxBackgroundAdapter;
import com.jude.rollviewpager.RollPagerView;


public class Entertain extends Fragment {
    private RollPagerView relaxBackground;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entertain, container, false);
        initWidget(view);
        RelaxBackgroundAdapter adapter = new RelaxBackgroundAdapter(getContext());
        relaxBackground.setAdapter(adapter);
        relaxBackground.setPlayDelay(5000);
        return view;
    }

    //初始化控件
    private void initWidget(View view) {
        relaxBackground = view.findViewById(R.id.relax_background_imageview);
    }
}
