package com.example.xiyan.testapplication;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.supconit.azpt.common.cache.Cache;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;


@AZActivity(R.layout.activity_ioc)
public class IocActivity extends AppCompatActivity {

    @AZView
    private Button buttonsave1,buttonsave2,buttonsave3;
    @AZView
    private RadioButton radiotrue,radiofalse;

    @AZView
    private RadioGroup gender;

    @AZView
    private TextView tv,textView1,textView2,nameView,passwdView,dataView;

    @AZView
    private EditText idEdit1,idEdit2,editpasswd,editname,editdata;

    @AZView
    private ListView lv1;

    @AZView
    private ImageView imageView1;

    @AZView
    private ImageButton ImageButton1;

    private int alpha=255;

    public static final String BUTTON1_KEY = "BUTTON1_KEY";

    //按钮button事件onclick
    @AZListener(value = {R.id.buttonsave1, R.id.buttonsave2}, type= ListenerType.Click)
    public void buttonClick(View view) {
        if(view.getId() == R.id.buttonsave1) {
            buttonsave1.setText("onclick保存");
            buttonsave1.setEnabled(false);
            buttonsave1.getBackground().setAlpha(40);
        }else if (view.getId() == R.id.buttonsave2){
            buttonsave2.setText("onclick注册");
            buttonsave2.setEnabled(false);
            buttonsave2.getBackground().setAlpha(40);
           // Cache.getInstance(this).put(BUTTON1_KEY, "在长文本中输入文字");
           // Cache.getInstance(this).getAsString(BUTTON1_KEY);
        }
    }

    //按钮button事件onLongclick
    @AZListener(value =R.id.buttonsave2, type= ListenerType.LongClick)
    public void buttonLongClick(View view){
        buttonsave2.setText("按钮Longonclick事件");
        buttonsave2.setTextSize(8f);
        buttonsave2.setTextColor(Color.RED);
        buttonsave2.getBackground().setAlpha(40);
    }

    //按钮button事件onLongclick
    @AZListener(value =R.id.buttonsave3, type= ListenerType.Touch)
    public void buttonTouch(View v, MotionEvent event){
        buttonsave3.setText("按钮touch事件");
        buttonsave3.getBackground().setAlpha(30);
        buttonsave3.setBackgroundColor(0XFF00FF00);
    }

    //ImageButton图片按钮修改背景色
    @AZListener(value = R.id.ImageButton1, type= ListenerType.Click)
    public void imagebuttonClick(View view) {
       if (view.getId() == R.id.ImageButton1){
           if(alpha>255){
               alpha=255;
           }else{
               alpha=alpha-50;
           }
            ImageButton1.getBackground().setAlpha(alpha);
        }
    }

}

