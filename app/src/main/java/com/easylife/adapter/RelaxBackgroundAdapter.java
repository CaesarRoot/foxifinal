package com.easylife.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RelaxBackgroundAdapter extends StaticPagerAdapter {
    private String[] picNames;
    private List<String> images = new ArrayList<>();
    private AssetManager manager;
    private Context context;
    private InputStream fis;

    public RelaxBackgroundAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 3;
        initAssets();
        try {
            fis = manager.open(images.get((int) (Math.random() * images.size())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.setImageBitmap(BitmapFactory.decodeStream(fis, null, opts));
        return view;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    //初始化资源文件
    private void initAssets() {
        try {
            manager = context.getAssets();
            picNames = manager.list("relax_pic");
            for (String pic : picNames) {
                images.add("relax_pic/" + pic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
