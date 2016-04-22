package com.example.xiyan.testapplication;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xiyan.testapplication.unit.Unit;
import com.example.xiyan.testapplication.widget.NumberProgressBar;
import com.example.xiyan.testapplication.widget.Prompt;
import com.example.xiyan.testapplication.widget.Textcontent;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;
import com.supconit.azpt.log.AndroidLog;
import com.supconit.azpt.log.LogUtil;
import com.supconit.azpt.network.AZNetworkUtils;
import com.supconit.azpt.network.callback.DownloadCallback;
import com.supconit.azpt.network.callback.SimpleCallback;
import com.supconit.azpt.network.callback.StringCallback;
import com.tencent.mm.sdk.platformtools.Util;

import org.apache.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@AZActivity(R.layout.activity_network)
public class NetworkActivity extends AppCompatActivity {

    private String server="http://172.19.10.18:8080";

    private String name;
    private String pwd;

    @AZView
    private Button getbt,postbt,uploadbt,downloadbt,reset_download_btn,downpausebt,rest_getbt,rest_postbt,rest_putbt,rest_deletebt,rest_patchbt;

    @AZView
    private TextView textcon,username,passwd,uploadtext,downloadtext,downloadfile1,downloadfile2;

    @AZView
    private EditText uservalue,passwdvalue;

    @AZView
    private Prompt prompt;

    private boolean downloading;

    private Level level= Level.TRACE;

    private String maxSizeFile="50kB";
    private int maxBackupIndex=3;

    @AZView // field name must be same as view's id in layout
    private NumberProgressBar download_npb,download_npb2,upload_npb;

    //get方法
    @AZListener(R.id.getbt)
    public void get(View view) {
        new  Thread(){
            public void run() {
                name=uservalue.getText().toString().trim();
                pwd=passwdvalue.getText().toString().trim();
                String url = server + "/z_web_test/test_test.action";
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", name);
                params.put("password", pwd);
                url = url + "?username=" +name+"&password="+pwd;
                prompt.show("Get", url);

                AZNetworkUtils.http().get()
                        .url(url)
                        .addParams(params)
                        .build()
                        .execute(new StringCallback() {
                            public void onError(Exception e) {
                                String error = "未提交成功";
                            }
                        });

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        String msg = "get方法成功\n";
                        prompt.show("Http Get 请求", msg);
                    }
                });


            }
        }.run();
    }

    //post方法
    @AZListener(R.id.postbt)
    public void post(View view)  throws IOException{
        new Thread(){
            public void run(){
                name=uservalue.getText().toString().trim();
                pwd=passwdvalue.getText().toString().trim();
                String url = server + "/z_web_test/test_test.action";
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", name);
                params.put("password", pwd);
//                url = url + "?username=" +name+"&password="+pwd;

                String filepath1 = Unit.getSDCardPath() + "/Download/flower.jpg";
                String filepath2 = Unit.getSDCardPath() + "/Download/031506_java.wmv";
                File file1 = new File(filepath1);
                File file2 = new File(filepath2);

                Map<String, File> fileMap = new HashMap<String, File>();
                fileMap.put("031506_java.wmv", file2);

                String temp = "----测试日志输出";
               for(int i=0;i<1000;i++){
                    try {
                        temp = temp + String.valueOf(i)+"-----";
                        AndroidLog androidLog1 = LogUtil.getAndroidLogSizeFile(NetworkActivity.class, level, maxSizeFile, maxBackupIndex);
                        androidLog1.debug(temp, false, null);
                        temp = "----测试日志输出";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                AZNetworkUtils.http()
                        .post()
                        .url(url)
                        .addParams(params)
                        .addFile("files", "flower.jpg", file1)
                        .addFiles("files", fileMap)
                        .build()
                        .connTimeOut(1500)
                        .execute(new StringCallback() {

                            @Override
                            public void onFinish() {
                                Log.i("Test", "-------onFinish()--------");
                            }

                            @Override
                            public void onSuccess(String jsonStr) {
                                Log.i("Test", "-------onSuccess()--------");
                                String msg = "post方法成功,返回结果：" + jsonStr;
                                prompt.show("Http Post 请求", msg);

                            }

                            @Override
                            public void onError(Exception e) {

                                e.printStackTrace();
                                Log.i("Test", "-------onError()--------");
                                Toast.makeText(NetworkActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onProgress(float progress) {
                                Log.i("Test", "-------  " + progress + "  --------");
                            }
                        });
            }
        }.start();

    }

    //上传功能
    @AZListener(R.id.uploadbt)
    public void upload(View view){
        upload();
    }

    public void upload(){
        new Thread(){
            public void run(){
                String url = server + "/z_web_test/test_upload.action";
                String filepath1 = Unit.getSDCardPath() + "/AndroidLog/azptLog.log.1";
                File file1 = new File(filepath1);

                Log.i("azpt", " -------------length : " + file1.length());
                AZNetworkUtils.transfer()
                        .upload()
                        .url(url)
                        .file(file1)
                        .build()
                        .execute(new SimpleCallback() {
                            @Override
                            public void onSuccess(Object result) {
                                Toast.makeText(NetworkActivity.this, "upload success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.i("azpt", "-------  error  --------");
                                Toast.makeText(NetworkActivity.this, "download error\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onProgress(float progress) {
                                Log.i("azpt", "-------  " + progress + "  --------");
                                upload_npb.setProgress((int) (progress * 100));
                            }
                        });
            }
        }.start();

    }

    //下载功能
    @AZListener(R.id.downloadbt)
    public void download(View view){
        download();
    }

    private void download() {
        downloading = true;
//        new Thread() {
//            public void run() {
//                String url = server + "/z_web_test/files/download1.zip";
//                try {
//                    final long length = AZNetworkUtils.http().get()
//                            .url(url)
//                            .build()
//                            .executeForContentLength();
//
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        public void run() {
//                            Toast.makeText(NetworkActivity.this, "File length : " + length, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        new Thread() {
            public void run() {
                String url = server + "/z_web_test/files/031506_java.rar";
                String savepath = Unit.getSDCardPath() + "/Download";
                AZNetworkUtils.transfer().download()
                        .url(url)
                        .tag(NetworkActivity.class)
                        .build()
                        .execute(new DownloadCallback(savepath, "031506_java.rar", true) {
                            @Override
                            public void inProgress(float progress, long total) {
                                Log.i("test", "-------  " + progress + "  --------");
                                download_npb.setProgress((int) (progress * 100));
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.i("test", "-------  error  --------");
                            }

                            @Override
                            public void onSuccess(File result) {
                                Toast.makeText(NetworkActivity.this, "download success", Toast.LENGTH_SHORT).show();
                                  }

                            @Override
                            public void onCancel() {
                                Toast.makeText(NetworkActivity.this, "download cancel", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }.start();


        new Thread() {
            public void run() {
                String url = server + "/z_web_test/files/flower.jpg";
                String savepath = Unit.getSDCardPath() + "/Download";
                AZNetworkUtils.transfer().download()
                        .url(url)
                        .tag(NetworkActivity.class)
                        .build()
                        .execute(new DownloadCallback(savepath, "flower.jpg", true) {
                            @Override
                            public void inProgress(float progress, long total) {
                                Log.i("test", "-------  " + progress + "  --------");
                                download_npb2.setProgress((int) (progress * 100));
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.i("test", "-------  error  --------");
                            }

                            @Override
                            public void onSuccess(File result) {
                                Toast.makeText(NetworkActivity.this, "download success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(NetworkActivity.this, "download cancel", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }.start();


    }

    @AZListener(R.id.downpausebt)
    public void cancelDownload(View view) {
        Button button = (Button) view;
        //下载停止
        if (downloading) {
            AZNetworkUtils.getInstance().cancelTag(NetworkActivity.class);
            button.setText("继续");
            downloading = false;
        } else {
            download();
            button.setText("暂停");
        }

    }


    @AZListener(value = {R.id.rest_getbt,R.id.rest_postbt,R.id.rest_putbt,R.id.rest_deletebt,R.id.rest_patchbt}, type= ListenerType.Click)
    public void buttonClick(View view) {
        if(view.getId() == R.id.rest_getbt){
            new Thread() {
                public void run() {
//                   String url = server + "/z_spring_mvc/person/1";
                    String url = server + "/z_spring_mvc/person/";
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("name", "admin");
                    params.put("sex","男");
                    params.put("age","22");
                    AZNetworkUtils.rest()
                            .get()
                            .url(url)
                            .addParams(params)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    String info = "使用方法AZNetworkUtils.rest().get().url(url).addParam().build().execute返回结果\n" +
                                            result;
                                    prompt.show("Rest GET 请求", info);
                                }
                                public void onError(Exception e) {
                                    String error = "未提交成功";
                                    e.printStackTrace();
                                }
                            });
                }
            }.start();

        }else if(view.getId() == R.id.rest_postbt){
            new Thread() {
                public void run() {
                    String url = server + "/z_spring_mvc/person/";
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("name", "admin2");
                    params.put("sex","男");
                    params.put("age","22");
                    AZNetworkUtils.rest()
                            .post()
                            .url(url)
                            .addParams(params)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    String info = "使用方法AZNetworkUtils.rest().post().url(url).addParam().build().execute返回结果\n" +
                                            result;
                                    prompt.show("Rest POST 请求", info);
                                }
                                public void onError(Exception e) {
                                    String error = "未提交成功";
                                    e.printStackTrace();
                                }
                            });
                }
            }.start();


        }else if(view.getId() == R.id.rest_putbt){
            new Thread() {
                public void run() {
                    String url = server + "/z_spring_mvc/person";
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("name", "test");
                    params.put("sex","男");
                    params.put("age","22");
                    AZNetworkUtils.rest()
                            .put()
                            .url(url)
                            .addParams(params)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    String info = "使用方法AZNetworkUtils.rest().put().url(url).build().execute返回结果:\n" +
                                            result;
                                    prompt.show("Rest Put 请求", info);
                                }
                                public void onError(Exception e) {
                                    String error = "未提交成功";
                                    e.printStackTrace();
                                }

                            });
                }
            }.start();
        }else if(view.getId()==R.id.rest_deletebt){
            new Thread(){
                public void run(){
                    String url = server + "/z_spring_mvc/person/1";
//                    Map<String,String> params = new HashMap<String, String>();
//                    params.put("name", "张三");
//                    params.put("sex", "男");
//                    params.put("age","30");
                    AZNetworkUtils.rest()
                            .delete()
                            .url(url)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    String info = "使用方法AZNetworkUtils.rest().delete().url(url).build().execute返回结果:\n" +
                                            result;
                                    prompt.show("Rest Delete 请求", info);
                                }

                                public void onError(Exception e) {
                                    String error = "未提交成功";
                                    e.printStackTrace();
                                }
                            });
                }


            }.start();
        }else if(view.getId()==R.id.rest_patchbt){
                    String url = server + "/z_spring_mvc/person";
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("name", "张三");
                    params.put("sex","男");
                    params.put("age","30");
                    AZNetworkUtils.rest()
                            .patch()
                            .url(url)
                            .addParams(params)
                            .build()
                            .executeInUIThread(new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    String info = "使用方法AZNetworkUtils.rest().patch().url(url).build().execute返回结果:\n" +
                                            result;
                                    prompt.show("Rest patch 请求", info);
                                }
                                public void onError(Exception e) {
                                    String error = "未提交成功";
                                }
                            });
        }
    }



}
