package com.example.xiyan.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiyan.testapplication.R;
import com.example.xiyan.testapplication.chart.BarchartActivity;
import com.example.xiyan.testapplication.chart.LinechartActivity;
import com.example.xiyan.testapplication.chart.PiechartActivity;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;

import java.util.ArrayList;
import java.util.List;

@AZActivity(R.layout.activity_chart)
public class ChartActivity extends Activity {

    @AZView
    private ListView lv;

    @AZAfter
    public void init() {
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
    }


    @AZListener(value=R.id.lv, type= ListenerType.ItemClick)
    public void go(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        if (position == 0) {
            intent = new Intent(this, LinechartActivity.class);
            startActivity(intent);
        }else if (position == 1) {
            intent = new Intent(this, BarchartActivity.class);
            startActivity(intent);
        }else if (position == 2) {
            intent = new Intent(this, PiechartActivity.class);
            startActivity(intent);
        }

        if (intent != null)
            startActivity(intent);
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        data.add("折线图");
        data.add("柱状图");
        data.add("饼图");
        return data;
    }


}
