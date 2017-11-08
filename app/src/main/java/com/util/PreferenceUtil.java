package com.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import static android.content.Context.MODE_PRIVATE;

/**
 * 作者 ： Created by user on 2017/11/8 09:28.
 */

public class PreferenceUtil {

    public static void setData(Context context,String preferencesName,String key,String value){
        SharedPreferences preferences =  context.getSharedPreferences(preferencesName,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static void setInt(Context context,String preferencesName,String key,int value){
        SharedPreferences preferences =  context.getSharedPreferences(preferencesName,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    public static String getData(Context context,String preferencesName,String key){
        SharedPreferences preferences =  context.getSharedPreferences(preferencesName,MODE_PRIVATE);
        return preferences.getString(key,null);
    }

    public static int getInt(Context context,String preferencesName,String key){
        SharedPreferences preferences =  context.getSharedPreferences(preferencesName,MODE_PRIVATE);
        return preferences.getInt(key,0);
    }
}
