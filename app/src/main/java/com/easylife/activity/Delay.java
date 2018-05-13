package com.easylife.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.adapter.DelayPagerAdapter;
import com.easylife.util.SharePreferenceUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

public class Delay extends Fragment {
    private RecyclerView recyclerView;
    private DelayPagerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button button;

    public List<String> tasks = new ArrayList<String>();
    public List<String> ddl = new ArrayList<String>();

    private String[] taskTypeName = {"todo", "finished", "failed"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragementdelay_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        initData();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyItemRangeChanged(0, tasks.size());

        button = view.findViewById(R.id.add);
        button.setOnClickListener(new Add());

        return view;
    }

    public class Add implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showDialogtoAdd();
        }
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        readData(com.easylife.entity.Task.STATE_TODO);
//        加载 tasks & ddl 数据
//        注意顺序一定要先于adapter 否则adapter就无法正确绑定数组

        adapter = new DelayPagerAdapter(tasks, ddl);
        adapter.setOnItemClickListener((view, position) -> showDialogtoChange(view, position));
    }

    private void showDialogtoAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.mydialogtoadd_layout, null);//获取自定义布局
        builder.setView(layout);

        final EditText mytext = layout.findViewById(R.id.tasks);
        MaterialCalendarView calendar = layout.findViewById(R.id.calendar);
        calendar.setSelectedDate(CalendarDay.today());
        calendar.state().edit()
                .setMinimumDate(CalendarDay.today())
                .commit();


        builder.setPositiveButton("ok", (arg0, arg1) -> {
            CalendarDay day = calendar.getSelectedDate();
            String date = day.getYear() + "-" + (day.getMonth() + 1) + "-" + day.getDay();
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Your add is applied", Toast.LENGTH_SHORT).show();
            Delay.this.tasks.add(mytext.getText().toString());
            Delay.this.ddl.add(date);
            saveData(com.easylife.entity.Task.STATE_TODO);
            adapter.notifyItemInserted(0);
            adapter.notifyItemRangeChanged(0, tasks.size());
        });
        //取消
        builder.setNegativeButton("cancel", (arg0, arg1) -> {
            // TODO Auto-generated method stub

        });
        final AlertDialog dlg = builder.create();
        dlg.show();
    }

    public void showDialogtoChange(View view, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.mydialogtochange_layout, null);//获取自定义布局
        builder.setView(layout);

        final EditText mytext = layout.findViewById(R.id.tasks);
        MaterialCalendarView calendar = layout.findViewById(R.id.calendar);
        calendar.setSelectedDate(CalendarDay.today());
        calendar.state().edit()
                .setMinimumDate(CalendarDay.today())
                .commit();

        //设置默认文本
        TextView textView = (TextView) view;
        mytext.setText(textView.getText().toString());


        builder.setPositiveButton("ok", (arg0, arg1) -> {
            CalendarDay day = calendar.getSelectedDate();
            String date = day.getYear() + "-" + (day.getMonth() + 1) + "-" + day.getDay();
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "Your change is applied", Toast.LENGTH_SHORT).show();
            Delay.this.tasks.set(position, mytext.getText().toString());
            Delay.this.ddl.set(position, date);
            saveData(com.easylife.entity.Task.STATE_TODO);
            adapter.notifyItemRangeChanged(0, tasks.size());
        });
        //取消
        builder.setNegativeButton("cancel", (arg0, arg1) -> {
            // TODO Auto-generated method stub

        });
        final AlertDialog dlg = builder.create();
        dlg.show();
    }

    public void readData(int taskType) {
        SharePreferenceUtil SharePreferenceUtil = new SharePreferenceUtil(getActivity(), "tasks");
        List<String> tasksToLoad = SharePreferenceUtil.getListData(taskTypeName[taskType]);
        tasks = new ArrayList<>();
        ddl = new ArrayList<>();
        if (tasksToLoad.get(0).equals("null"))
            return;
        for (String task : tasksToLoad) {
            if (task == null || task.length() == 1)
                break;
            Log.i("task", task);
            tasks.add(task.split("@")[0]);
            ddl.add(task.split("@")[1]);
        }
    }

    public void saveData(int taskType) {
        //存储数据
        SharePreferenceUtil SharePreferenceUtil = new SharePreferenceUtil(getActivity(), "tasks");
        SharePreferenceUtil.putListData(tasks, ddl, taskTypeName[taskType]);
    }

}

