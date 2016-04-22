package com.example.xiyan.testapplication.unit;

import android.os.Environment;

/**
 * Created by xiyan on 16-4-19.
 */
public class Unit {
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath(); //主要的外部存储目录
    }
}
