package com.easylife.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easylife.util.Pickers;
import com.easylife.view.CountdownCircle;
import com.easylife.view.PickerScrollView;
import com.easylife.view.RadialGradientView;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 *
 * @author zengtao 2015年5月20日 下午7:36:03
 *
 */
public class FocusTimerActivity extends Activity {

    private Button bt_scrollchoose; // 滚动选择器按钮
    private PickerScrollView pickerscrolllview1; // 滚动选择器
    private PickerScrollView pickerscrolllview2;
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private LinearLayout picker_rel; // 选择器布局
    private RadialGradientView dot; //倒计时小圆点
    private CountdownCircle countdownCircle;

    private Button bt_yes; // 确定按钮
    private Button bt_tocontrol; //返回 MainControlActivity 按钮

    private TextView mTv_show;
    private long millisInFuture1 = 0;
    private long millisInFuture2 = 0;//60s
    private long countDownInterval = 1000;//每隔一秒
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Slide slide1 = new Slide();
        slide1.setSlideEdge(Gravity.TOP);
        slide1.setDuration(1000);

        Slide slide2 = new Slide();
        slide2.setSlideEdge(Gravity.TOP);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(slide2);
        getWindow().setExitTransition(slide1);

        setContentView(R.layout.focustimer_layout);

        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        initView();
        initLinstener();
        initData1();
        initData2();
    }

    /**
     * 初始化
     */
    private void initView() {
        bt_scrollchoose = findViewById(R.id.bt_scrollchoose);
        picker_rel = findViewById(R.id.picker_rel);
        pickerscrolllview1 = findViewById(R.id.pickerscrolllview1);
        pickerscrolllview2 = findViewById(R.id.pickerscrolllview2);
        bt_yes = findViewById(R.id.picker_yes);
        mTv_show = findViewById(R.id.time);
        bt_tocontrol = findViewById(R.id.button1);
        dot = findViewById(R.id.dot);
        countdownCircle = findViewById(R.id.countdown);
    }

    /**
     * 设置监听事件
     */
    private void initLinstener() {
        bt_scrollchoose.setOnClickListener(onClickListener);
        pickerscrolllview1.setOnSelectListener(pickerListener1);
        pickerscrolllview2.setOnSelectListener(pickerListener2);
        bt_yes.setOnClickListener(onClickListener);
        bt_tocontrol.setOnClickListener(onClickListener);
    }

    /**
     * 初始化数据
     */
    private void initData1() {
        list = new ArrayList<Pickers>();
        id = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
        name = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrolllview1.setData(list);
        pickerscrolllview1.setSelected(0);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();   //获取屏幕宽度
        int widthPixels = displayMetrics.widthPixels;

        pickerscrolllview1.setMyWidth(widthPixels/5*2 *2 - 80);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener1 = new PickerScrollView.onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            millisInFuture1 = Long.parseLong(pickers.getShowId()) * 1000 * 3600;
        }
    };

    private void initData2() {
        list = new ArrayList<Pickers>();
        id = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };
        name = new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrolllview2.setData(list);
        pickerscrolllview2.setSelected(0);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();   //获取屏幕宽度
        int widthPixels = displayMetrics.widthPixels;

        pickerscrolllview2.setMyWidth(80);
    }

    // 滚动选择器选中事件
   PickerScrollView.onSelectListener pickerListener2 = new PickerScrollView.onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            millisInFuture2 = Long.parseLong(pickers.getShowId()) * 1000 * 60;
        }
    };

    // 点击监听事件
    OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v == bt_scrollchoose) {
                picker_rel.setVisibility(View.VISIBLE);
            } else if (v == bt_yes) {
                picker_rel.setVisibility(View.GONE);
                if (myCountDownTimer == null) {
                    myCountDownTimer = new MyCountDownTimer(millisInFuture1 + millisInFuture2, countDownInterval);
                }
                myCountDownTimer.start();
                CountDownCircle();    //倒计时小圆点移动
                countdownCircle.setTotaltime(millisInFuture1 + millisInFuture2);   //开始画倒计时圈
            }else if (v == bt_tocontrol){
                Intent intent = new Intent(FocusTimerActivity.this, MainControlActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(FocusTimerActivity.this, bt_tocontrol, "shared").toBundle());
            }
        }
    };

    //倒计时小圆点的移动
    public void CountDownCircle(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(dot, "rotation", 360f, 0f);  //旋转view来使圆圈倒转
        animator.setDuration(millisInFuture1 + millisInFuture2);
        animator.start();
    }

    public class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    总共持续的时间
         * @param countDownInterval 倒计时的时间间隔
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * @param millisUntilFinished 还剩下的时间
         */
        @Override
        public void onTick(long millisUntilFinished) {
            mTv_show.setText(toTimeStr(millisUntilFinished));
        }

        /**
         * 倒计时结束时候回调
         */
        @Override
        public void onFinish() {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
        }
    }

    private String to2Str(long i){

        if(i > 9){
            return i+"";
        }else{
            return "0"+i;
        }

    }

    private String toTimeStr(long secTotal){

        String result = null;
        secTotal = secTotal / 1000;
        long hour = secTotal / 3600;
        long min = ( secTotal%3600 ) / 60;
        long sec = ( secTotal%3600 ) % 60;
        result = to2Str(hour)+":"+to2Str(min)+":"+to2Str(sec);

        return result;
    }
}

