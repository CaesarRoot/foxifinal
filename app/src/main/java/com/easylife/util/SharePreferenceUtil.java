package com.easylife.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.easylife.activity.Delay;
import com.easylife.activity.MainControlActivity;
import com.easylife.entity.Task;
import com.easylife.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.content.Context.MODE_PRIVATE;

public class SharePreferenceUtil {
    private SharedPreferences preferences;
    private Context context;
    private String[] taskTypeName = {"todo", "finished", "failed"};

    /**
     * @param context 上下文对象
     * @param tasks
     */
    public SharePreferenceUtil(Context context, String tasks) {
        this.context = context;
        preferences = context.getSharedPreferences(tasks, MODE_PRIVATE);
    }

    /**
     * 用于保存String数组
     *
     * @param ddl
     */
    public void putListData(List<String> list, List<String> ddl, String taskType) {
        SharedPreferences.Editor editor = preferences.edit();
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            task.append(list.get(i)).append("@").append(ddl.get(i)).append("#");
        }
        editor.putString(taskType, task.toString());
        editor.apply();
        tasksUpdate(task.toString(), taskType);
    }

    //获取本地事务集
    private void tasksUpdate(String task, String taskType) {
        Task taskToUpdate = new Task();
        taskToUpdate.setTodo(preferences.getString("todo", "null"));
        taskToUpdate.setFinished(preferences.getString("finished", "null"));
        taskToUpdate.setFailed(preferences.getString("failed", "null"));
        taskToUpdate.setUsername(getCurrentUser().getUsername());
        BmobQuery<Task> taskQuery = new BmobQuery<>();
        taskQuery.addWhereEqualTo("username", getCurrentUser().getUsername());
        taskQuery.findObjects(new FindListener<Task>() {
            @Override
            public void done(List<Task> list, BmobException e) {
                if (e == null) {
                    taskToUpdate.setObjectId(list.get(0).getObjectId());
                    taskToUpdate.setValue(taskType, task.toString());
                    taskToUpdate.update(taskToUpdate.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                }
            }
        });
    }

    /**
     * 获取保存的List
     */
    public List<String> getListData(String taskType) {
        BmobQuery<Task> taskQuery = new BmobQuery<>();
        taskQuery.addWhereEqualTo("username", getCurrentUser().getUsername());
        taskQuery.findObjects(new FindListener<Task>() {
            @Override
            public void done(List<Task> list, BmobException e) {
                if (e == null) {
                    SharedPreferences.Editor editor = preferences.edit();
                    String task;
                    if (taskType.equals(taskTypeName[0])) {
                        task = list.get(0).getTodo();
                    } else if (taskType.equals(taskTypeName[1])) {
                        task = list.get(0).getFinished();
                    } else {
                        task = list.get(0).getFailed();
                    }
                    editor.putString(taskType, task);
                    editor.apply();
                }
            }
        });
        return new ArrayList<>(Arrays.asList(preferences.getString(taskType, "null").split("#")));
    }

    //获取本地用户
    public User getCurrentUser() {
        User currentUser;
        SharedPreferences preferences = context.getSharedPreferences("login_user", MODE_PRIVATE);
        currentUser = new User(
                preferences.getString("username", "null"),
                preferences.getString("nickname", "null"),
                preferences.getString("password", "null"),
                preferences.getString("user_phone", "null"));
        currentUser.setObjectId(preferences.getString("objectID", "null"));
        currentUser.setPassword(preferences.getString("password", "null"));
        currentUser.setAvatarUrl(preferences.getString("avatar", "null"));
        currentUser.setAvatarFileName(preferences.getString("avatar_file_name", "null"));
        return currentUser;
    }

}
