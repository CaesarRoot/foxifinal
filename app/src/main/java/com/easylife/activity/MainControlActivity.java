package com.easylife.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.easylife.adapter.FragAdapter;
import com.easylife.adapter.RelaxBackgroundAdapter;
import com.jude.rollviewpager.RollPagerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainControlActivity extends FragmentActivity {
    public CountDownTimer timer;

    private View yege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Slide slide1 = new Slide();
        slide1.setSlideEdge(Gravity.BOTTOM);
        slide1.setDuration(1000);

        Slide slide2 = new Slide();
        slide2.setSlideEdge(Gravity.BOTTOM);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(slide2);
        getWindow().setExitTransition(slide1);

        setContentView(R.layout.maincontrol_layout);

        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //构造适配器
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Entertain());
        fragments.add(new FocusMain());
        fragments.add(new Delay());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        ViewPager vp = findViewById(R.id.viewpager);
        vp.setAdapter(adapter);
        vp.setCurrentItem(1);

        //跳转到叶哥的账户界面
        yege = findViewById(R.id.yege);
        yege.setOnClickListener(v -> {
            Intent intent = new Intent(MainControlActivity.this, UserActivity.class);
            startActivity(intent);
        });

    }
}
