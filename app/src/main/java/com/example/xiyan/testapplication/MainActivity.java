package com.example.xiyan.testapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiyan.testapplication.widget.GestureLockActivity;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;
import java.util.ArrayList;
import java.util.List;


@AZActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @AZView
    private ListView lv;

    @AZAfter
    public void init() {
        //列表显示
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));



    }

    @AZListener(value=R.id.lv, type= ListenerType.ItemClick)
    public void go(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent(this, IocActivity.class);
            startActivity(intent);
        }else if (position == 1) {
            Intent intent = new Intent(this, DBActivity.class);
            startActivity(intent);
        }else if (position == 2) {
            Intent intent = new Intent(this, NetworkActivity.class);
            startActivity(intent);
        }else if (position == 3) {
            Intent intent = new Intent(this, ChartActivity.class);
            startActivity(intent);
        }else if (position == 4) {
            Intent intent = new Intent(this, LogActivity.class);
            startActivity(intent);
        }else if (position == 5) {
            Intent intent = new Intent(this, ImageActivity.class);
            startActivity(intent);
        }else if (position == 6) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        data.add("自动注入模块测试");
        data.add("数据库模块测试");
        data.add("网络模块测试");
        data.add("图表模块测试");
        data.add("日志模块测试");
        data.add("图片缓存模块测试");
        data.add("登录页面");
        return data;
    }



}
