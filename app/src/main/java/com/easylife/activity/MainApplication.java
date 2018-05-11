package com.easylife.activity;

import android.app.Activity;
import android.app.Application;

import com.easylife.entity.Delay;
import com.easylife.entity.Reading;
import com.easylife.entity.Relationship;
import com.easylife.entity.Relax;
import com.easylife.entity.Study;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class MainApplication extends Application {
    public static int picIndex;
    public static int sentenceIndex;

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "7be9b42f62bf09ca88164f551f7af615");

        Delay delay = new Delay();
        Reading reading = new Reading();
        Relationship relationship = new Relationship();
        Relax relax = new Relax();
        Study study = new Study();

        delay.save();
        reading.save();
        relationship.save();
        relax.save();
        study.save();
    }

}
