package com.example.xiyan.testapplication.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.xiyan.testapplication.LogActivity;
import com.example.xiyan.testapplication.MainActivity;
import com.example.xiyan.testapplication.R;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZBefore;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;
import com.supconit.azpt.ui.Alert.AZAlertDialog;
import com.supconit.azpt.ui.GestureLock.GestureActivity;

@AZActivity(R.layout.activity_gesturelayout)
public class GestureLockActivity extends Activity {
    @AZView
    private RadioGroup GestrueRadio;
    @AZView
    private ImageButton GestrueButton;

    private boolean GestrueEdit=false;
    @AZView
    private Button GestrueB;

    @AZAfter
    public void init(){
        SharedPreferences sharedPreferences= getSharedPreferences("password", MainActivity.MODE_PRIVATE);
        String islocked =sharedPreferences.getString("isLock", "");
        if(islocked.equals("true")){
            GestrueButton.setImageResource(R.mipmap.on);
            GestrueEdit=true;
            Toast.makeText(GestureLockActivity.this, "true", Toast.LENGTH_SHORT).show();
        }else{
            GestrueButton.setImageResource(R.mipmap.off);
            GestrueEdit=false;
            Toast.makeText(GestureLockActivity.this, "false", Toast.LENGTH_SHORT).show();
        }

    }

    @AZListener(value = {R.id.GestrueButton}, type = ListenerType.Click)
    public void changeGestrue(View view) {
        if (GestrueEdit == false) {
            ((ImageButton) view).setImageResource(R.mipmap.on);
            AZAlertDialog.show(this, "系统提示", "确认密码更加安全", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(GestureLockActivity.this, GestureActivity.class);
                    intent.putExtra("isLock", true);
                    intent.putExtra("maxErrorInputTimes", 5);
                    intent.putExtra("textSize", 16);
                    startActivityForResult(intent, 0);
                    dialog.dismiss();

                }
            });
            GestrueEdit=true;
        }else{
            ((ImageButton) view).setImageResource(R.mipmap.off);
            GestrueEdit=false;
            SharedPreferences preferences = getSharedPreferences("password", GestureActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("isLock", "false");
            editor.commit();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1) {
            String passwd = data.getStringExtra("password");
            Toast.makeText(GestureLockActivity.this, "手势密码：" + passwd, Toast.LENGTH_SHORT).show();

            SharedPreferences preferences = getSharedPreferences("password", GestureActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("password", passwd);
            editor.putString("isLock", "true");
            editor.commit();

        }else if(resultCode == 2){
            Toast.makeText(GestureLockActivity.this, "输入密码正确", Toast.LENGTH_SHORT).show();
        }else if(resultCode == 3){
            Toast.makeText(GestureLockActivity.this, "输入密码次数超过上限", Toast.LENGTH_SHORT).show();
        }


    }

/*    @AZListener(value = {R.id.GestrueB}, type = ListenerType.Click)
    public void change(View view){
        Intent intent = new Intent(GestureLockActivity.this, GestureActivity.class);
        intent.putExtra("isLock",false);
        intent.putExtra("maxErrorInputTimes",5);
        intent.putExtra("textSize",16);
        startActivityForResult(intent, 0);
    }*/


}


