package com.easylife.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easylife.activity.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DelayFragment extends Fragment {
    private PieChart pieChart;
    private ArrayList<PieEntry> entries;
    private ArrayList<DelayData> delayData;
    private PieDataSet dataSet;
    private PieData pieData;
    private Legend legend;
    private static final String[] tasks = new String[]{"待办事务", "已完成事务", "已逾期事务"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化数据
        initData();
    }

    private void initData() {
        entries = new ArrayList<>(3);
        delayData = new ArrayList<>(3);
        delayData.add(new DelayData(5));
        delayData.add(new DelayData(4));
        delayData.add(new DelayData(3));
        for (int i = 0, delayDataSize = delayData.size(); i < delayDataSize; i++) {
            DelayData data = delayData.get(i);
            entries.add(new PieEntry(data.getTimes(), tasks[i] + "(" + data.getTimes() + ")"));
        }

        dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(2f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        pieData = new PieData(dataSet);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delay, null);
        pieChart = view.findViewById(R.id.delay_piechart);
        pieChartStyling(pieChart);
        pieChart.setData(pieData);
        return view;
    }

    private void pieChartStyling(PieChart pieChart) {
        pieChart.setUsePercentValues(true);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        pieChart.getDescription().setEnabled(false);
        legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);
    }

    class DelayData {
        private int times;

        public DelayData(int times) {
            this.times = times;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }
}