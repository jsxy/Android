package com.example.xiyan.testapplication;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.xiyan.testapplication.widget.Prompt;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.log.AndroidLog;
import com.supconit.azpt.log.DateEnum;
import com.supconit.azpt.log.LogSender;
import com.supconit.azpt.log.LogUtil;
import org.apache.log4j.Level;

import java.io.IOException;
import java.util.Date;
import java.util.Random;


@AZActivity(R.layout.activity_log)
public class LogActivity extends Activity {


    @AZView(R.id.radioGroup1)
    private RadioGroup radioGroup1;
    @AZView(R.id.radioGroup2)
    private RadioGroup radioGroup2;
    @AZView(R.id.radioGroup3)
    private RadioGroup radioGroup3;
    @AZView(R.id.radioGroup4)
    private RadioGroup radioGroup4;

    @AZView(R.id.et_msg)
    private EditText editText;

    @AZView
    private Prompt prompt;

    private Level level= Level.ERROR;

    private DateEnum dateEnum= DateEnum.MINUTE;

    private String maxSizeFile="3KB";
    private int maxBackupIndex=0;
    private String title;
    private String content;

    @AZAfter
    public void init(){
        LogUtil.setLogFilePath(this);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_trace:
                        level = Level.TRACE;
                        break;
                    case R.id.rb_debug:
                        level = Level.DEBUG;
                        break;
                    case R.id.rb_info:
                        level = Level.INFO;
                        break;
                    case R.id.rb_warn:
                        level = Level.WARN;
                        break;
                    case R.id.rb_error:
                        level = Level.ERROR;
                        break;
                    case R.id.rb_fatal:
                        level = Level.FATAL;
                        break;
                    default:
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_minute:
                        dateEnum = DateEnum.MINUTE;
                        break;
                    case R.id.rb_hour:
                        dateEnum = DateEnum.HOUR;
                        break;
                    case R.id.rb_halfday:
                        dateEnum = DateEnum.HALFDAY;
                        break;
                    case R.id.rb_day:
                        dateEnum = DateEnum.DAY;
                        break;
                    case R.id.rb_week:
                        dateEnum = DateEnum.WEEK;
                        break;
                    case R.id.rb_month:
                        dateEnum = DateEnum.MONTH;
                        break;
                    default:
                        break;
                }
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_kb:
                        maxSizeFile = "1KB";
                        break;
                    case R.id.rb_mb:
                        maxSizeFile = "1MB";
                        break;
                    case R.id.rb_gb:
                        maxSizeFile = "1GB";
                        break;
                    default:
                        break;
                }
            }
        });
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        maxBackupIndex = -2;
                        break;
                    case R.id.rb_0:
                        maxBackupIndex = 0;
                        break;
                    case R.id.rb_2:
                        maxBackupIndex = 2;
                        break;
                    case R.id.rb_4:
                        maxBackupIndex = 4;
                        break;
                    case R.id.rb_6:
                        maxBackupIndex = 6;
                        break;
                    case R.id.rb_10:
                        maxBackupIndex = 10;
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @AZListener(R.id.bt_console)
    public void console(View view) {
        AndroidLog androidLog1= null;
        try {
            androidLog1 = LogUtil.getAndroidLogger(LogActivity.class, level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        androidLog1.trace(editText.getText().toString(), false, null);
        androidLog1.debug(editText.getText().toString(), false, null);
        androidLog1.info(editText.getText().toString(), false, null);
        androidLog1.warn(editText.getText().toString(), false, null);
        androidLog1.error(editText.getText().toString(), false, null);
        androidLog1.fatal(editText.getText().toString(), false, null);

        title="控制台输出调用方式：";


        if(level == Level.TRACE){
            content= "时间 线程名 [TRACE] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [DEBUG] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [INFO] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [WARN] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [ERROR] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [FATAL] 类全名- "+editText.getText().toString()+"\n";

        }else if(level == Level.DEBUG){
            content= "时间 线程名 [DEBUG] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [INFO] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [WARN] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [ERROR] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [FATAL] 类全名- "+editText.getText().toString()+"\n";
        }else if(level == Level.INFO){
            content= "时间 线程名 [INFO] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [WARN] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [ERROR] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [FATAL] 类全名- "+editText.getText().toString()+"\n";
        }else if(level == Level.WARN){
            content= "时间 线程名 [WARN] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [ERROR] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [FATAL] 类全名- "+editText.getText().toString()+"\n";
        }else if(level == Level.ERROR){
            content= "时间 线程名 [ERROR] 类全名- "+editText.getText().toString()+"\n";
            content+= "时间 线程名 [FATAL] 类全名- "+editText.getText().toString()+"\n";
        }else if(level == Level.FATAL){
            content= "时间 线程名 [FATAL] 类全名- "+editText.getText().toString()+"\n";
        }
        prompt.show(title, content);

    }

    @AZListener(R.id.bt_file)
    public void file(View view) throws IOException {
        AndroidLog androidLog1 = LogUtil.getAndroidLogFile(LogActivity.class);
        androidLog1.trace(editText.getText().toString(), false, null);
        androidLog1.debug(editText.getText().toString(), false, null);
        androidLog1.info(editText.getText().toString(), false, null);
        androidLog1.warn(editText.getText().toString(), false, null);
        androidLog1.error(editText.getText().toString(), false, null);
        androidLog1.fatal(editText.getText().toString(), false, null);
    }


    @AZListener(R.id.bt_file_level)
    public void file_level(View view) throws IOException {
        AndroidLog androidLog1 = LogUtil.getAndroidLogFile(LogActivity.class, level);
        androidLog1.debug(editText.getText().toString(), false, null);
        androidLog1.info(editText.getText().toString(), false, null);
        androidLog1.warn(editText.getText().toString(), false, null);
        androidLog1.error(editText.getText().toString(), false, null);
        androidLog1.fatal(editText.getText().toString(), false, null);
    }

    @AZListener(R.id.bt_file_patternLayout)
    public void file_patternLayout(View view) throws IOException {
        AndroidLog androidLog1 = LogUtil.getAndroidLogFile(LogActivity.class, "%d [%t]  %c  %x %p %r %l  %n  - %m%n",level.TRACE);
        androidLog1.trace(editText.getText().toString(), false, null);
        androidLog1.debug(editText.getText().toString(), false, null);
        androidLog1.info(editText.getText().toString(), false, null);
        androidLog1.warn(editText.getText().toString(), false, null);
        androidLog1.error(editText.getText().toString(), false, null);
        androidLog1.fatal(editText.getText().toString(), false, null);
    }



    @AZListener(R.id.bt_datefile)
    public void dateFile(View view) throws IOException {
        AndroidLog androidLog1 = LogUtil.getAndroidLogDateFile(LogActivity.class, dateEnum);
        androidLog1.trace(editText.getText().toString(), false, null);
        androidLog1.debug(editText.getText().toString(), false, null);
        androidLog1.info(editText.getText().toString(), false, null);
        androidLog1.warn(editText.getText().toString(), false, null);
        androidLog1.error(editText.getText().toString(), false, null);
        androidLog1.fatal(editText.getText().toString(), false, null);
    }

    @AZListener(R.id.bt_datefile_level)
    public void dateFile_level(View view) throws IOException {
        AndroidLog androidLog1 = LogUtil.getAndroidLogDateFile(LogActivity.class, level, dateEnum);
        androidLog1.trace(editText.getText().toString(), false, null);
        androidLog1.debug(editText.getText().toString(), false, null);
        androidLog1.info(editText.getText().toString(), false, null);
        androidLog1.warn(editText.getText().toString(), false, null);
        androidLog1.error(editText.getText().toString(), false, null);
        androidLog1.fatal(editText.getText().toString(), false, null);

    }

    @AZListener(R.id.bt_datefile_patternLayout)
    public void dateFile_patternLayout(View view) throws IOException {
        AndroidLog androidLog1 = LogUtil.getAndroidLogDateFile(LogActivity.class, "%d [%t]  %c  %x %p %r %l  %n  - %m%n",level, dateEnum);
        androidLog1.trace(editText.getText().toString(), false, null);
        androidLog1.debug(editText.getText().toString(), false, null);
        androidLog1.info(editText.getText().toString(), false, null);
        androidLog1.warn(editText.getText().toString(), false, null);
        androidLog1.error(editText.getText().toString(), false, null);
        androidLog1.fatal(editText.getText().toString(), false, null);

    }


    @AZListener(R.id.bt_sizefile)
    public void sizefile(View view) throws IOException {
        AndroidLog androidLog1 = LogUtil.getAndroidLogSizeFile(LogActivity.class, level, maxSizeFile, maxBackupIndex);
            androidLog1.trace(editText.getText().toString(), false, null);
            androidLog1.debug(editText.getText().toString(), false, null);
            androidLog1.info(editText.getText().toString(), false, null);
            androidLog1.warn(editText.getText().toString(), false, null);
            androidLog1.error(editText.getText().toString(), false, null);
            androidLog1.fatal(editText.getText().toString(), false, null);
    }

    @AZListener(R.id.bt_delete)
    public void deleteFile(View view){
        LogUtil.deleteLogFile();
    }

    @AZListener(R.id.bt_deleteall)
    public void deleteAllFile(View view){
        LogUtil.deleteAllLogFile();
    }

    @AZListener(R.id.bt_alert)
    public void alert(View view) throws IOException {
        AndroidLog androidLog1= LogUtil.getAndroidLogger(LogActivity.class, level);
        androidLog1.fatal(editText.getText().toString(), false, this);
    }

    @AZListener(R.id.bt_http)
    public void http(View view){


        LogSender.configURL("http://172.19.10.18:8080/z_web_test/test_upload.action");

        Toast.makeText(LogActivity.this, "上传服务器成功", Toast.LENGTH_LONG).show();


    }

    @AZListener(R.id.bt_mail)
    public void mail(View view){

        LogSender.configEMail(new String[]{"coco_li2016@163.com", "coco_li2016@126.com"}, "coco_li2016@126.com", "abc123");
        int result = LogSender.sendLogMsgMail("hh", "开心");
        if(result == 0){
            Toast.makeText(LogActivity.this,"邮件发送成功，请查收",Toast.LENGTH_LONG).show();
        }
    }

    @AZListener(R.id.bt_mailFile)
    public void mailFile(View view){
        LogSender.configEMail(new String[]{"coco_li2016@163.com", "coco_li2016@126.com"}, "coco_li2016@126.com", "abc123");
//        int result = LogSender.sendLogFileMail("hh","开心");
        int result = LogSender.sendLogFileAllMail("hh", "开心");
        if(result == 0){
            Toast.makeText(LogActivity.this,"邮件发送成功，请查收",Toast.LENGTH_LONG).show();
        }
    }

    //生成GB数据
    private void makeGB(){
        int sum =0;

    }

}
