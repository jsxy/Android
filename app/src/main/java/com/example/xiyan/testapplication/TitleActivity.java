package com.example.xiyan.testapplication;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ui.activity.AZTitledActivity;

/**
 * Created by yanfei on 2016/4/20.
 */

@AZActivity(R.layout.activity_title)
public class TitleActivity extends AZTitledActivity {

    @AZView
    private TextView tv;

    @AZAfter
    public void init() {
        setTitleCenterText("Titled Activity");
        //setTitleBackgroundResource(R.mipmap.azpt_common_title_left_icon);
        setTitleBackgroundColor(Color.RED);
        tv.setText("I am a injected view");
    }

    @AZListener(R.id.tv)
    public void click(View view) {
        Toast.makeText(this, "Injected Listener", Toast.LENGTH_LONG).show();
    }
}
