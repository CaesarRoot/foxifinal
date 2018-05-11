package com.easylife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easylife.activity.R;

import java.util.List;

public class DelayPagerAdapter extends RecyclerView.Adapter<DelayPagerAdapter.ViewHolder> {
    public List<String> tasks;
//    public OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public DelayPagerAdapter(List<String> tasks) {
        this.tasks = tasks;
    }

//    //定义回调方法 1.定义接口 2.声明属性 3.设置方法 4.绑定控件
//    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
//        this.mOnItemClickListener = listener;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delayitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tasks.setText(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

//    public interface OnRecyclerViewItemClickListener{
//        void onItemClick(View view, int position);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tasks;

        public ViewHolder(View itemView) {
            super(itemView);
            tasks = itemView.findViewById(R.id.tasks);
//            tasks.setOnClickListener(v -> {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onItemClick(v, ViewHolder.this.getLayoutPosition());
//                }
//            });
        }
    }
}

