package com.easylife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easylife.adapter.DelayPagerAdapter;
import com.easylife.util.Pickers;
import com.easylife.view.PickerScrollView;

import java.util.ArrayList;
import java.util.List;

public class Delay extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button button;

    public List<String> tasks = new ArrayList<>();
    private long millisInFuture1;
    private long millisInFuture2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragementdelay_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        initData();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        button = view.findViewById(R.id.add);
        button.setOnClickListener(new Add());

        return view;
    }

    public class Add implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            showDialog();
        }
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adapter = new DelayPagerAdapter(tasks);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.mydialog_layout, null);//获取自定义布局
        builder.setView(layout);

        final EditText mytext = layout.findViewById(R.id.tasks);

        //设置选择器
        PickerScrollView pickerscrolllview1 = layout.findViewById(R.id.pickerscrolllview1); // 滚动选择器
        pickerscrolllview1.setOnSelectListener(pickerListener1);

        initpicker1(pickerscrolllview1);

        PickerScrollView pickerScrollView2 = layout.findViewById(R.id.pickerscrolllview2);
        pickerScrollView2.setOnSelectListener(pickerListener2);
        initpicker2(pickerScrollView2);




        builder.setPositiveButton("ok", (arg0, arg1) -> {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Your add is applied", Toast.LENGTH_SHORT).show();
            Delay.this.tasks.add(mytext.getText().toString());
            Toast.makeText(getActivity(),mytext.getText().toString(),Toast.LENGTH_LONG).show();
            adapter.notifyItemInserted(0);
            adapter.notifyItemRangeChanged(0,tasks.size());
        });
        //取消
        builder.setNegativeButton("cancel", (arg0, arg1) -> {
            // TODO Auto-generated method stub

        });
        final AlertDialog dlg = builder.create();
        dlg.show();
    }

    private void initpicker1(PickerScrollView pickerScrollView) {
        ArrayList<Pickers> list = new ArrayList<Pickers>();
        String[] id = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
        String[] name = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerScrollView.setData(list);
        pickerScrollView.setSelected(0);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();   //获取屏幕宽度
        int widthPixels = displayMetrics.widthPixels;

        pickerScrollView.setMyWidth(widthPixels/5*2 *2 - 200);
    }

    private void initpicker2(PickerScrollView pickerScrollView) {
        ArrayList<Pickers> list = new ArrayList<Pickers>();
        String[] id = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
        String[] name = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerScrollView.setData(list);
        pickerScrollView.setSelected(0);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();   //获取屏幕宽度
        int widthPixels = displayMetrics.widthPixels;

        pickerScrollView.setMyWidth(80);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener1 = new PickerScrollView.onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            millisInFuture1 = Long.parseLong(pickers.getShowId()) * 1000 * 3600;
        }
    };

    PickerScrollView.onSelectListener pickerListener2 = new PickerScrollView.onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            millisInFuture2 = Long.parseLong(pickers.getShowId()) * 1000 * 3600;
        }
    };
}

