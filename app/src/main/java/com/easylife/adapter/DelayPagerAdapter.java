package com.easylife.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easylife.activity.R;

import java.util.List;

public class DelayPagerAdapter extends RecyclerView.Adapter<DelayPagerAdapter.ViewHolder> {
    public List<String> tasks;
    public List<String> ddl;
    public OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public TranslateAnimation mShowAction;
    public TranslateAnimation mHiddenAction;
    public TranslateAnimation buttonHidAction;

    public DelayPagerAdapter(List<String> tasks, List<String> ddl) {
        this.tasks = tasks;
        this.ddl = ddl;
        //初始化三种动画
        setShowAction();
        setHiddenAction();
        setButtonHid();
    }

    //定义回调方法 1.定义接口 2.声明属性 3.设置方法 4.绑定控件
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delayitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tasks.setText(tasks.get(position));
        holder.ddl.setText(ddl.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tasks;
        private TextView ddl;
        private View details;
        private ConstraintLayout ddlandbutton;
        private View changeitem;

        public ViewHolder(View itemView) {
            super(itemView);
            tasks = itemView.findViewById(R.id.tasks);
            ddl = itemView.findViewById(R.id.ddl);
            ddlandbutton = itemView.findViewById(R.id.ddlandbutton);
            details = itemView.findViewById(R.id.changeVisible);
            changeitem = itemView.findViewById(R.id.changeitem);

            changeitem.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(ViewHolder.this.tasks, ViewHolder.this.getLayoutPosition());
                }
            });
            details.setOnClickListener(v -> {
                    details.setVisibility(View.GONE);
                    ddlandbutton.startAnimation(mShowAction);
                    ddlandbutton.setVisibility(View.VISIBLE);
            });
            ddl.setOnClickListener(v -> {
                    ddlandbutton.startAnimation(mHiddenAction);
                    ddlandbutton.setVisibility(View.GONE);
                    details.startAnimation(buttonHidAction);
                    details.setVisibility(View.VISIBLE);
            });
        }
    }

    public void setShowAction()
    {
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
    }

    public void setHiddenAction()
    {
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
        mHiddenAction.setDuration(500);
    }

    public void setButtonHid(){
        buttonHidAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -4.8f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        buttonHidAction.setDuration(500);
    }
}

