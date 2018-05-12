package com.easylife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
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
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.adapter.DelayPagerAdapter;
import com.easylife.util.Pickers;
import com.easylife.view.PickerScrollView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.b.V;

public class Delay extends Fragment {
    private RecyclerView recyclerView;
    private DelayPagerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button button;

    public List<String> tasks = new ArrayList<>();
    public List<String> ddl = new ArrayList<>();
    private String month;
    private String day;

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
            showDialogtoAdd();
        }
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adapter = new DelayPagerAdapter(tasks, ddl);
        adapter.setOnItemClickListener((view, position) -> showDialogtoChange(view, position));
    }

    private void showDialogtoAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.mydialog_layout, null);//获取自定义布局
        builder.setView(layout);

        final EditText mytext = layout.findViewById(R.id.tasks);
        MaterialCalendarView calendar = layout.findViewById(R.id.calendar);
        calendar.state().edit()
                .setMinimumDate(CalendarDay.today())
                .commit();


        builder.setPositiveButton("ok", (arg0, arg1) -> {
            CalendarDay day = calendar.getSelectedDate();
            String date = day.getYear()+"-"+(day.getMonth()+1)+"-"+day.getDay();
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Your add is applied", Toast.LENGTH_SHORT).show();
            Delay.this.tasks.add(mytext.getText().toString());
            Delay.this.ddl.add(date);
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

    public void showDialogtoChange(View view, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.mydialog_layout, null);//获取自定义布局
        builder.setView(layout);

        final EditText mytext = layout.findViewById(R.id.tasks);
        MaterialCalendarView calendar = layout.findViewById(R.id.calendar);
        calendar.state().edit()
                .setMinimumDate(CalendarDay.today())
                .commit();

        //设置默认文本
        TextView textView = (TextView)view;
        mytext.setText(textView.getText().toString());


        builder.setPositiveButton("ok", (arg0, arg1) -> {
            CalendarDay day = calendar.getSelectedDate();
            String date = day.getYear()+"-"+(day.getMonth()+1)+"-"+day.getDay();
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Your change is applied", Toast.LENGTH_SHORT).show();
            Delay.this.tasks.set(position, mytext.getText().toString());
            Delay.this.ddl.set(position, date);
            adapter.notifyItemRangeChanged(0,tasks.size());
        });
        //取消
        builder.setNegativeButton("cancel", (arg0, arg1) -> {
            // TODO Auto-generated method stub

        });
        final AlertDialog dlg = builder.create();
        dlg.show();
    }
}

