package com.example.xiyan.testapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        }
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        data.add("自动注入模块测试");
        data.add("数据库模块测试");
        data.add("网络模块测试");
        data.add("图表模块测试");
        data.add("日志模块测试");
        return data;
    }


}
