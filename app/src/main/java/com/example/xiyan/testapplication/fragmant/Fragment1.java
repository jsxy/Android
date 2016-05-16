package com.example.xiyan.testapplication.fragmant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyan.testapplication.R;
import com.example.xiyan.testapplication.TitleActivity;
import com.example.xiyan.testapplication.widget.GestureLockActivity;
import com.supconit.azpt.common.scan.Scanner;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.core.InjectCore;
import com.supconit.azpt.ui.fragment.AZTabFragment;
import com.supconit.azpt.ui.loadingDialog.LoadingDialog;
import com.supconit.azpt.ui.progressBar.AZProgressBar;
import com.supconit.azpt.ui.progressBar.ProgressBarView;
import com.supconit.azpt.ui.progressBar.ProgressWheelView;
import com.supconit.azpt.ui.search.activity.SearchViewActivity;


/**
 * Created by xiyan on 2016/4/19.
 */
public class Fragment1 extends AZTabFragment {
    private Context context;

    @AZView(R.id.home_tv)
    private TextView tv;

    @AZView(R.id.process1)
    private TextView process1;
    @AZView(R.id.process2)
    private TextView process2;
    @AZView(R.id.process3)
    private TextView process3;
    @AZView(R.id.process4)
    private TextView process4;

    @AZView(R.id.progressbar_new)
    private ProgressBarView progressBarView;
    @AZView(R.id.progress_wheel_new)
    private ProgressWheelView progress_wheel_new;

    @AZView(R.id.bt_progressBar)
    private Button bt_progressBar;
    @AZView(R.id.bt_progresswheel)
    private Button bt_progresswheel;

    @AZView(R.id.scan_bt)
    private ImageButton scan_bt;

    @AZView(R.id.bt_dot)
    private ImageButton bt_dot;

    @AZView(R.id.bt_wheel)
    private ImageButton bt_wheel;

    @AZView(R.id.bt_self)
    private ImageButton bt_self;



    @AZView(R.id.searchTv)
    private com.supconit.azpt.ui.search.widget.SearchSrcTextView  searchTv;


    public void init(){
        searchTv.setHint("搜索想要的内容...");
//        searchTv.setImageResource(R.mipmap.az_eye_close);
//        searchTv.setBackgroundResource(R.mipmap.az_scan);
        searchTv.setTextSize(10);
        searchTv.setHintTextColor(Color.rgb(128, 138, 135));
        searchTv.setPadding(10,10,10,10);

        new Thread() {
            public void run() {
                for (int i = 0; i <= 360; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBarView.setProgress(i);
                    progress_wheel_new.setProgress(i);
                }
            }
        }.start();
    }



    @AZListener(R.id.bt_progressBar)
    public void progress(View view){
        final ProgressBarView p = AZProgressBar.showProgressBar(getActivity());
        p.setMax(360);
        new Thread() {
            public void run() {
                for (int i = 0; i <= 360; i++) {
                    try {
                        Thread.sleep(10);
                        p.setProgress(i);
                        if(i == 360){
                            AZProgressBar.closeBar();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }.start();
    }


    @AZListener(R.id.bt_progresswheel)
    public void progresswheel(View view){
        final ProgressWheelView p = AZProgressBar.showProgressWheel(getActivity());
        new Thread() {
            public void run() {
                for (int i = 0; i <= 360; i++) {
                    try {
                        Thread.sleep(50);
                        p.setProgress(i);
                        p.setInnerRadius(40);
                        p.setOuterRadius(50);
                        if(i == 360){
                            AZProgressBar.closeWheel();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }.start();
    }


    @AZListener(R.id.scan_bt)
    public void scan(View view){
        Scanner.setScanListener(getActivity(), new Scanner.ScanListener() {
            @Override
            public void scanSuccess(String sucesscan) {
                Toast.makeText(getActivity(), sucesscan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void scanFailing(String failscan) {
                Toast.makeText(getActivity(), failscan, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @AZListener(R.id.bt_dot)
    public void colors(View view){
        LoadingDialog.show(context);
        ThreadClose thread = new ThreadClose();
        thread.start();
    }

    @AZListener(R.id.bt_self)
    public void self_colors(View view){
        LoadingDialog.show(getActivity(), 10, true);
        ThreadClose thread = new ThreadClose();
        thread.start();
    }
    public class ThreadClose extends  Thread{
        public void run(){
            try {
                Thread.sleep(3000);
                LoadingDialog.closeDot();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @AZListener(R.id.bt_wheel)
    public void blue(View view){
        LoadingDialog.show(getActivity(),100,true);
        ThreadWheelClose thread = new ThreadWheelClose();
        thread.start();
    }
    public class ThreadWheelClose extends  Thread{
        public void run(){
            try {
                Thread.sleep(3000);
                LoadingDialog.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @AZListener(R.id.searchTv)
    public void searchTV(View view){
        startActivity(new Intent(getActivity(), SearchViewActivity.class));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = createRootView(inflater, R.layout.ui_layout);
        InjectCore.inject(this, root);

        setTitle("主页");
        init();
        //TextView tv = (TextView) root.findViewById(R.id.home_tv);
        tv.setText("UI组件");
//        process1.setText("进度条(type1)：");
//        process2.setText("进度条(type2)：");
        /*setOnTitleLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "left", Toast.LENGTH_LONG).show();
            }
        });*/
        setOnTitleRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "sucess", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), GestureLockActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @AZListener(R.id.home_tv)
    public void click(View view) {
        Intent intent = new Intent(getActivity(), TitleActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public int initCheckedResId() {
        return R.mipmap.home_checked;
    }

    @Override
    public int initUncheckedResId() {
        return R.mipmap.home_unchecked;
    }

    @Override
    public boolean initChecked() {
        return true;
    }
    @Override
    public String initTabLable() {
        return "首页";
    }

    @Override
    public int initCheckedTextColor() {
        return Color.rgb(0, 191, 142);
    }

    @Override
    public int initUnCheckedTextColor() {
        return Color.GRAY;
    }
}
