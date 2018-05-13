package com.easylife.activity;


import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Entertain extends Fragment {
    public static ImageView relaxBackgroundTop;
    public static ImageView relaxBackgroundBottom;
    public static String[] picNames;
    public static List<String> images = new ArrayList<>();
    public static AssetManager manager;
    private InputStream fis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entertain, container, false);
        initWidget(view);
        initAssets();
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 3;
            fis = manager.open(images.get((int) (Math.random()*images.size())));
            relaxBackgroundTop.setImageBitmap(BitmapFactory.decodeStream(fis,null,opts));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    //初始化资源文件
    private void initAssets() {
        try {
            manager = getActivity().getAssets();
            picNames = manager.list("relax_pic");
            for (String pic : picNames) {
                images.add("relax_pic/" + pic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //初始化控件
    private void initWidget(View view) {
        relaxBackgroundTop = view.findViewById(R.id.relax_background_top_imageview);
        relaxBackgroundBottom = view.findViewById(R.id.relax_background_bottom_imageview);
    }
}
