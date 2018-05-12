package com.easylife.activity;

import android.app.ActionBar;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.adapter.NotePagerAdapter;
import com.easylife.entity.User;
import com.easylife.fragment.DelayFragment;
import com.easylife.fragment.FocusFragment;
import com.easylife.fragment.RelaxFragment;
import com.github.mikephil.charting.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

public class UserActivity extends FragmentActivity {
    private TextView week;
    private TextView settings;
    private TextView about;
    private TextView nickname;
    private RoundedImageView avatar;
    private ViewPager charts;
    private RadioButton focusChart;
    private RadioButton delayChart;
    private RadioButton relaxChart;
    private RadioGroup chartGroup;
    private ImageView back;

    private String avatarSrc = Environment.getExternalStorageDirectory().getPath() + "/EasyLife/avatar/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化统计表工具包
        Utils.init(this);

        setContentView(R.layout.activity_user);

        //初始化标题栏
        initActionBar();

        //初始化控件
        initWidget();

        //初始化用户数据
        initUserInfo();

        //设置点击事件
        setOnClick();

    }

    //初始化用户数据
    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        View actionBarView = LayoutInflater.from(this).inflate(R.layout.user_actionbar, null);
        if (actionBar != null) {
            actionBar.setCustomView(actionBarView);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        }
    }

    //初始化用户数据
    private void initUserInfo() {
        User user = getCurrentUser();
        nickname.setText(user.getNickname());
        //初始化头像
        if (new File(avatarSrc + user.getAvatarFileName()).exists()) {
            avatar.setImageBitmap(BitmapFactory.decodeFile(avatarSrc + user.getAvatarFileName()));
        } else if (!user.getAvatarUrl().equals("null")) {
            BmobFile avatar = new BmobFile(user.getAvatarFileName(), "", user.getAvatarUrl());
            avatar.download(new File(avatarSrc + user.getAvatarFileName()), new DownloadFileListener() {
                @Override
                public void done(String s, BmobException e) {
                    if (e != null) {
                        UserActivity.this.avatar.setImageResource(R.mipmap.avatar);
                    } else {
                        UserActivity.this.avatar.setImageBitmap(BitmapFactory.decodeFile(s + "/" + user.getAvatarFileName()));
                    }
                }

                @Override
                public void onProgress(Integer integer, long l) {

                }
            });
        } else {
            UserActivity.this.avatar.setImageResource(R.mipmap.avatar);
        }
    }

    private void setOnClick() {
        avatar.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, UserInfoActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });

        week.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, WeeklyReportActivity.class);
            startActivity(intent);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP && event.getRepeatCount() == 0) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private void initWidget() {
        week = findViewById(R.id.week);
        settings = findViewById(R.id.settings);
        about = findViewById(R.id.about);
        avatar = findViewById(R.id.avatar_imageview);
        charts = findViewById(R.id.charts_viewpager);
        focusChart = findViewById(R.id.focus_radiobutton);
        delayChart = findViewById(R.id.delay_radiobutton);
        relaxChart = findViewById(R.id.relax_radiobutton);
        chartGroup = findViewById(R.id.chart_radiogroup);
        nickname = findViewById(R.id.nickname_textview);
        back = findViewById(R.id.back_imageview);

        //初始化统计表
        initChart();
    }

    private void initChart() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FocusFragment());
        fragments.add(new DelayFragment());
        fragments.add(new RelaxFragment());
        NotePagerAdapter adapter = new NotePagerAdapter(getSupportFragmentManager(), fragments);
        charts.setAdapter(adapter);
        /**
         * 为 Viewpager 设置页面切换监听，当页面切换完成被选中时，我们同步 RadioButton 的状态
         **/
        charts.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        focusChart.setChecked(true);
                        break;
                    case 1:
                        delayChart.setChecked(true);
                        break;
                    case 2:
                        relaxChart.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /**
         * 为 RadioGroup 设置选中变化事件监听，当 RadioButton 状态变化，我们同步 Viewpager 的选中页面
         **/
        chartGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.focus_radiobutton:
                    charts.setCurrentItem(0);
                    break;
                case R.id.delay_radiobutton:
                    charts.setCurrentItem(1);
                    break;
                case R.id.relax_radiobutton:
                    charts.setCurrentItem(2);
                    break;
            }
        });
    }

    //获取本地用户
    public User getCurrentUser() {
        User currentUser;
        SharedPreferences preferences = getSharedPreferences("login_user", MODE_PRIVATE);
        currentUser = new User(
                preferences.getString("username", "null"),
                preferences.getString("nickname", "null"),
                preferences.getString("password", "null"),
                preferences.getString("user_phone", "null"));
        currentUser.setObjectId(preferences.getString("objectID", "null"));
        currentUser.setPassword(preferences.getString("password", "null"));
        currentUser.setAvatarUrl(preferences.getString("avatar", "null"));
        currentUser.setAvatarFileName(preferences.getString("avatar_file_name", "null"));
        return currentUser;
    }
}
