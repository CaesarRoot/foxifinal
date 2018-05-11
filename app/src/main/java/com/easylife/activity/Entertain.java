package com.easylife.activity;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Entertain extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmenthandler = new Handler();
        //        handler.postDelayed(() -> {
        //
        //        }, SPLASH_DISPLAY_LENGHT);
        return inflater.inflate(R.layout.fragment_entertain, container, false);
    }
}
