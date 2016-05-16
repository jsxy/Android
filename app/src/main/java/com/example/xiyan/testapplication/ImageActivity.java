package com.example.xiyan.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xiyan.testapplication.Image.ListImageActivity;
import com.example.xiyan.testapplication.Image.GridImageActivity;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;
import com.supconit.azpt.ui.pullrefresh.widget.PullToRefreshBase;
import com.supconit.azpt.ui.pullrefresh.widget.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

@AZActivity(R.layout.activity_image)
public class ImageActivity extends Activity {
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
            Intent intent = new Intent(this, ListImageActivity.class);
            startActivity(intent);
        }else if (position == 1) {
            Intent intent = new Intent(this, GridImageActivity.class);
            startActivity(intent);
        }
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        data.add("Listimage");
        data.add("Gradeimage");
        return data;
    }


}
