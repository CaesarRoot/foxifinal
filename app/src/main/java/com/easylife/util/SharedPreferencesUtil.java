package com.easylife.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesUtil {
    private  SharedPreferencesUtil util;
    private  SharedPreferences sp;

    /**
     *
     * @param context 上下文对象
     * @param name    SharedPreferences Name
     */
    public SharedPreferencesUtil(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 用于保存String数组
     *
     * @param key  key
     * @param taskType
     */
    public void putListData(String key, List<String> list, int taskType) {
        SharedPreferences.Editor editor = sp.edit();
        JsonArray array = new JsonArray();
        try {
            for (int i = 0; i < list.size(); i++) {
                array.add(list.get(i));
            }
            editor.putString(key, array.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    /**
     * 获取保存的List
     *
     * @param key key
     * @param taskType
     */
    public List<String> getListData(String key, int taskType) {
        List<String> list = new ArrayList<>();
        String json = sp.getString(key, "");
        if (!json.equals("") && json.length() > 0) {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                list.add(elem.getAsString());
            }
        }
        return list;
    }
}
