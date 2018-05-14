package com.easylife.activity;


import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.easylife.adapter.RelaxBackgroundAdapter;
import com.easylife.util.BgMusicAssetsManager;
import com.jude.rollviewpager.RollPagerView;

import java.io.IOException;


public class Entertain extends Fragment {
    public static RollPagerView relaxBackground;
    public static Button play;
    private MediaPlayer player = new MediaPlayer();
    private BgMusicAssetsManager musicAssetsManager;

    private boolean isPrepared = false;
    private boolean isPlayable = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        musicAssetsManager = new BgMusicAssetsManager();
        musicAssetsManager.initAssets(getContext());
        try {
            int musicIndex = (int) (Math.random() * musicAssetsManager.getMusics().size());
            player.setDataSource(musicAssetsManager.getMusics().get(musicIndex));
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entertain, container, false);
        initWidget(view);
        setOnClickEvent();
        RelaxBackgroundAdapter relaxAdapter = new RelaxBackgroundAdapter(getContext());
        relaxBackground.setAdapter(relaxAdapter);
        relaxBackground.setPlayDelay(3000);
        relaxBackground.setAnimationDurtion(1000);
        return view;
    }

    private void setOnClickEvent() {
        //播放按钮监听事件
        play.setOnClickListener(v -> {
            if (!isPrepared) {
                Toast.makeText(getContext(), "正在缓冲，请稍候...", Toast.LENGTH_SHORT).show();
                return;
            }
            if (player.isPlaying()) {
                player.pause();
                isPlayable = false;
            } else {
                player.start();
                isPlayable = true;
            }
        });

        //播放结束时回调方法
        player.setOnCompletionListener(mp -> {

        });

        //播放资源缓冲完成时回调方法
        player.setOnPreparedListener(mp -> {
            isPrepared = true;
        });

    }

    //初始化控件
    private void initWidget(View view) {
        play = view.findViewById(R.id.play_button);
        relaxBackground = view.findViewById(R.id.relax_background_imageview);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isPlayable) {
            return;
        }
        if (!isVisibleToUser) {
            player.pause();
        } else {
            player.start();
        }
    }
}
