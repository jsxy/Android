package com.example.xiyan.testapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import android.widget.Toast;

import com.example.xiyan.testapplication.widget.GestureLockActivity;
import com.supconit.azpt.common.cache.Cache;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZBefore;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;
import com.supconit.azpt.ui.Alert.AZAlertDialog;
import com.supconit.azpt.ui.GestureLock.GestureActivity;

import org.apache.log4j.Level;

import java.io.FileNotFoundException;

import okhttp3.internal.Internal;

@AZActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

        @AZView
        private Button buttonsave1,buttonsave2,buttonsave3;
        @AZView
        private RadioButton radiotrue,radiofalse;

        @AZView
        private RadioGroup gender;

        @AZView
        private TextView nameView,passwdView,dataView,empty;

        @AZView
        private EditText editpasswd,editname,editdata;

        @AZView
        private ImageButton bt_eye;
        private boolean eyeEdit=false;
        private int alpha=255;

        public static final String BUTTON1_KEY = "BUTTON1_KEY";

        public static final String temp_name="temp_name";
        public static final String temp_passwd="temp_passwd";


        @AZBefore
        public void before(){
            //手势密码，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
            SharedPreferences sharedPreferences= getSharedPreferences("password",MainActivity.MODE_PRIVATE);
            String password =sharedPreferences.getString("password", "");
            String islocked =sharedPreferences.getString("isLock", "");
            if(islocked.equals("true")){
                Intent intent = new Intent(LoginActivity.this, GestureActivity.class);
                intent.putExtra("isLock",false);
                intent.putExtra("maxErrorInputTimes",5);
                intent.putExtra("textSize", 16);
                startActivityForResult(intent, 0);

            }

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == 2){
                Intent intent = new Intent(this, TabActivity.class);//1为设置成功、2为解密成功、3为超过上限
                startActivity(intent);
            }else if(resultCode == 3){
                Toast.makeText(this, "输入密码次数超过上限", Toast.LENGTH_SHORT).show();
            }

        }

        @AZAfter
        public  void init(){
            bt_eye.getBackground().setAlpha(0);
            empty.getBackground().setAlpha(0);
            if(Cache.getInstance(LoginActivity.this).getAsString(temp_name)!="" && Cache.getInstance(LoginActivity.this).getAsString(temp_name).length()!=0){
                editname.setText(Cache.getInstance(LoginActivity.this).getAsString(temp_name));
            }
            if(Cache.getInstance(LoginActivity.this).getAsString(temp_passwd)!="" && Cache.getInstance(LoginActivity.this).getAsString(temp_passwd).length()!=0){
                editpasswd.setText(Cache.getInstance(LoginActivity.this).getAsString(temp_passwd));
            }
            gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radiotrue:
                            Cache.getInstance(LoginActivity.this).put(temp_name,editname.getText().toString());
                            Cache.getInstance(LoginActivity.this).put(temp_passwd, editpasswd.getText().toString());
                            break;
                        case R.id.radiofalse:
                            Cache.getInstance(LoginActivity.this).put(temp_name, "");
                            Cache.getInstance(LoginActivity.this).put(temp_passwd,"");
                            break;
                        default:
                            break;
                    }
                }
            });


        }


    @AZListener(R.id.bt_eye)
    public void ifcloss(View view){
        if(eyeEdit == false){
            ((ImageButton) view).setImageResource(R.mipmap.az_eye_open);
            editpasswd.setInputType(0x90);
            eyeEdit =true;
        }else{
            ((ImageButton) view).setImageResource(R.mipmap.az_eye_close);
            editpasswd.setInputType(0x81);
            eyeEdit =false;
        }
    }

        //按钮button事件onclick
        @AZListener(value = {R.id.buttonsave1, R.id.buttonsave2}, type= ListenerType.Click)
        public void buttonClick(View view) {
            if(view.getId() == R.id.buttonsave1) {
//                buttonsave1.getBackground().setAlpha(40);
//                AZAlertDialog.createAlertOne(this, null, "首次登录系统", "确认");
                AZAlertDialog.show(this, "系统提示", "首次登录系统", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editname.getText().toString().equals("admin") && editpasswd.getText().toString().equals("admin888")) {
                            Intent intent = new Intent(LoginActivity.this, TabActivity.class);
                            startActivityForResult(intent, 0);
                            dialog.dismiss();
                        } else {
                            AZAlertDialog.show(LoginActivity.this, null, "用户名和密码错误", "返回");
                            dialog.dismiss();
                        }

                    }
                });
            }else if (view.getId() == R.id.buttonsave2){
//                buttonsave2.setEnabled(false);
//                buttonsave2.getBackground().setAlpha(40);
                AZAlertDialog.show(this, "提示", "确认取消哈哈哈哈哈三大三大诉确认取消", "确认", "返回登录返回登录");
                // Cache.getInstance(this).put(BUTTON1_KEY, "在长文本中输入文字");
                // Cache.getInstance(this).getAsString(BUTTON1_KEY);
            }
        }


        //按钮button事件onLongclick
        @AZListener(value =R.id.buttonsave3, type= ListenerType.Touch)
        public void buttonTouch(View v, MotionEvent event){
//            buttonsave3.setText("按钮touch事件");
            buttonsave3.getBackground().setAlpha(30);
//            buttonsave3.setBackgroundColor(0XFF00FF00);
            AZAlertDialog.show(this, "系统提示", "欢迎登录安卓平台1.0.1版本", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(LoginActivity.this, "捕捉到点击事件", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });
        }

    @AZListener(R.id.radiotrue)
    public void remember(View view) throws FileNotFoundException {
        Cache.getInstance(LoginActivity.this).put(temp_name,editname.getText().toString());
        Cache.getInstance(LoginActivity.this).put(temp_passwd, editpasswd.getText().toString());
        String temp_n = Cache.getInstance(LoginActivity.this).getAsString(temp_name);
        String temp_p = Cache.getInstance(LoginActivity.this).getAsString(temp_passwd);
        Toast.makeText(LoginActivity.this,  temp_n +" "+temp_p, Toast.LENGTH_SHORT).show();
    }
    @AZListener(R.id.radiofalse)
    public void deleteremember(View view){
        Cache.getInstance(LoginActivity.this).put(temp_name,"");
        Cache.getInstance(LoginActivity.this).put(temp_passwd,"");
//        String temp_n = Cache.getInstance(LoginActivity.this).getAsString(temp_name);
//        String temp_p = Cache.getInstance(LoginActivity.this).getAsString(temp_passwd);
//        Toast.makeText(LoginActivity.this,temp_n +" "+temp_p, Toast.LENGTH_SHORT).show();
    }

}

