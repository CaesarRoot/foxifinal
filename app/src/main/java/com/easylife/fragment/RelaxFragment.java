package com.easylife.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easylife.activity.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class RelaxFragment extends Fragment {
    private BarChart barChart;
    private ArrayList<BarEntry> entries;
    private ArrayList<RelaxData> relaxData;
    private BarDataSet dataSet;
    private BarData barData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化数据
        initData();
    }

    private void initData() {
        entries = new ArrayList<>(7);
        relaxData = new ArrayList<>(7);

        for (int i = 0; i < 3; i++) {
            relaxData.add(new RelaxData(30 + i * i));
        }
        for (int i = 0; i < 4; i++) {
            relaxData.add(new RelaxData(20 + i * i * 2));
        }

        for (RelaxData data : relaxData) {
            entries.add(new BarEntry(relaxData.indexOf(data) + 1, data.getTimeSum()));
        }

        dataSet = new BarDataSet(entries, "[x:day y:min]");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        dataSet.setHighLightAlpha(255);

        barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relax, null);
        barChart = view.findViewById(R.id.relax_barchart);
        barChart.animateY(1000);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getDescription().setEnabled(false);
        barChart.setSelected(false);
        barChart.setData(barData);
        return view;
    }

    class RelaxData {
        private int timeSum;

        public RelaxData(int timeSum) {
            this.timeSum = timeSum;
        }

        public int getTimeSum() {
            return timeSum;
        }

        public void setTimeSum(int timeSum) {
            this.timeSum = timeSum;
        }
    }


}