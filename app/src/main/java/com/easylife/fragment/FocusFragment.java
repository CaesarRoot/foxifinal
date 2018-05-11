package com.easylife.fragment;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easylife.activity.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class FocusFragment extends Fragment {
    private List<FocusData> readingDataList = new ArrayList<>();
    private List<FocusData> workingDataList = new ArrayList<>();
    private LineChart lineChart;
    private List<Entry> readingEntries = new ArrayList<>();
    private List<Entry> workingEntries = new ArrayList<>();
    private LineDataSet readingDataSet;
    private LineDataSet workingDataSet;
    private LineData focusData;
    private List<ILineDataSet> dataSets = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据
        initData();
    }

    private void initData() {
        if (readingDataList.size() != 0 || workingDataList.size() != 0) {
            return;
        }

        for (int i = 1; i <= 7; i++) {
            readingDataList.add(new FocusData(i, 20 - i * i));
            workingDataList.add(new FocusData(i, i * i + 5));
        }

        for (FocusData data : readingDataList) {
            readingEntries.add(new Entry(data.getDay(), data.getFocusTime()));
        }

        for (FocusData data : workingDataList) {
            workingEntries.add(new Entry(data.getDay(), data.getFocusTime()));
        }

        readingDataSet = new LineDataSet(readingEntries, "阅读时长");
        readingDataSet.setLineWidth(3.0f);
        readingDataSet.setCircleRadius(5.0f);
        readingDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        readingDataSet.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        readingDataSet.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[1]);

        workingDataSet = new LineDataSet(workingEntries, "学习时长");
        workingDataSet.setLineWidth(3.0f);
        workingDataSet.setCircleRadius(5.0f);
        workingDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        workingDataSet.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        workingDataSet.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);

        dataSets.add(readingDataSet);
        dataSets.add(workingDataSet);

        focusData = new LineData(dataSets);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus, null);
        lineChart = view.findViewById(R.id.focus_linechart);
        lineChartStyling(lineChart);
        lineChart.setData(focusData);
        lineChart.invalidate();
        return view;
    }

    //lineChart的风格设置
    private void lineChartStyling(LineChart lineChart) {
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setText("[x:day y:hour]");
        lineChart.setDrawBorders(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.animateXY(1000, 1000);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    private class FocusData {
        private int day;
        private int focusTime;

        public FocusData(int day, int focusTime) {
            this.day = day;
            this.focusTime = focusTime;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getFocusTime() {
            return focusTime;
        }

        public void setFocusTime(int focusTime) {
            this.focusTime = focusTime;
        }
    }
}