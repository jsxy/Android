package com.example.xiyan.testapplication.chart;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xiyan.testapplication.R;
import com.example.xiyan.testapplication.chart.BaseDemo;
import com.supconit.azpt.chart.charts.BarChart;
import com.supconit.azpt.chart.components.Title;
import com.supconit.azpt.chart.data.BarData;
import com.supconit.azpt.chart.data.BarDataElement;
import com.supconit.azpt.chart.data.BarDataSet;
import com.supconit.azpt.chart.interfaces.datasets.IBarDataSet;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;

import java.util.ArrayList;

@AZActivity(R.layout.activity_barchart)
public class BarchartActivity extends BaseDemo {
    @AZView
    private BarChart barChart;

    @AZView
    private TextView IntroduceView,titleView,setTitleView,TextSizeView,TextColorView,PositionView,LeftAxisMinView,LeftAxisMaxView,RightAxisMinView,RightAxisMaxView,animateXView,animateYView,animateXYView,ValueTextSizeView,ValueTextColorView;

    @AZView
    private EditText setTitleEdit,TextSizeEdit,TextColorEdit,LeftAxisMinEdit,LeftAxisMaxEdit,RightAxisMinEdit,RightAxisMaxEdit,animateXEdit,animateYEdit,animateXYEdit,ValueTextSizeEdit,ValueTextColorEdit;

    @AZView
    private RadioGroup TitlePosition,DrawFillRadio;

    @AZView
    private Button createbt,addbt,refreshbt;

    @AZView
    private CheckBox GridEnable_x,GridEnable_y;

    private boolean grade_x,grade_y,DrawFillvalue;

    @AZAfter
    public void init() {
        TitlePosition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.title_top:
                        barChart.setTitlePosition(Title.TitlePosition.TOP_CHART_CENTER);
                        break;
                    case R.id.title_bottom:
                        barChart.setTitlePosition(Title.TitlePosition.BOTTOM_CHART_CENTER);
                        break;
                    default:
                        break;
                }
            }
        });

        DrawFillRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.DrawFill_true:
                        DrawFillvalue=true;
                        break;
                    case R.id.DrawFill_false:
                        DrawFillvalue=false;
                        break;
                    default:
                        break;
                }
            }
        });

        GridEnable_x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    grade_x =true;
                }else{
                    grade_x =false;
                }
            }
        });

        GridEnable_y.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    grade_y =true;
                }else{
                    grade_y =false;
                }
            }
        });

    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i % 12]);
        }

        ArrayList<BarDataElement> yVals1 = new ArrayList<BarDataElement>();
        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            yVals1.add(new BarDataElement(val, i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet 1");
        //柱状颜色和透明度
        set1.setColor(Color.rgb(0, 0, 255),100);
        //是否显示数值，数值颜色大小
        set1.setDrawValues(true);
        set1.setValueTextColor(Color.BLUE);
        if(ValueTextSizeEdit.getText().toString() !=null && ValueTextSizeEdit.getText().toString().length() !=0) {
            set1.setValueTextSize(Integer.parseInt(ValueTextSizeEdit.getText().toString()));
        }else {
            set1.setValueTextSize(10);
        }



        ArrayList<BarDataElement> yVals2 = new ArrayList<BarDataElement>();
        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            yVals2.add(new BarDataElement(val, i));
        }
        BarDataSet set2 = new BarDataSet(yVals2, "DataSet 2");
        set2.setColor(Color.rgb(132, 112, 255), 100);


        ArrayList<BarDataElement> yVals3 = new ArrayList<BarDataElement>();
        for (int i = 0; i < count; i++) {
            float mult = (range + 5);
            float val = (float) (Math.random() * mult);
            yVals3.add(new BarDataElement(val, i));
        }
        BarDataSet set3 = new BarDataSet(yVals3, "DataSet 3");
        set2.setColor(Color.rgb(255,0, 0),100);


        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BarData data = new BarData(xVals, dataSets);
        if(ValueTextSizeEdit.getText().toString() !=null && ValueTextSizeEdit.getText().toString().length() !=0) {
            data.setValueTextSize(Integer.parseInt(ValueTextSizeEdit.getText().toString()));
        }else {
            data.setValueTextSize(10f);
        }
        //data.setValueTypeface(mTf);

        barChart.setData(data);
    }


    @AZListener(value = {R.id.createbt}, type= ListenerType.Click)
    public void createchart(View view){
        if(view.getId() == R.id.createbt) {
            if(setTitleEdit.getText().toString() !=null && setTitleEdit.getText().toString().length()!=0){
                barChart.setTitle(setTitleEdit.getText().toString());
            }else{
                barChart.setTitle("Test图表标题");
            }
            barChart.setTitleTextColor(Color.RED);
            if(TextSizeEdit.getText().toString() !=null && TextSizeEdit.getText().toString().length()!=0){
                barChart.setTitleTextSize(Float.parseFloat(TextSizeEdit.getText().toString()));
            }else {
                barChart.setTitleTextSize(15);
            }


            barChart.setBackgroundResource(R.color.colorLightGreen);
            //是否显示x和y轴的网格
            barChart.setGridEnable(grade_x, grade_y);

            //设置显示坐标轴
            barChart.setAxisEnabled(true, true, true);

            //x和y轴生成数据点的时间间隔
            if(animateXEdit.getText().toString() !=null && animateXEdit.getText().toString().length()!=0){
                barChart.animateX(Integer.parseInt(animateXEdit.getText().toString()));
            }else{
                barChart.animateX(2000);
            }
            if(animateYEdit.getText().toString() !=null && animateYEdit.getText().toString().length()!=0){
                barChart.animateY(Integer.parseInt(animateYEdit.getText().toString()));
            }else{
                barChart.animateY(3000);
            }

            //左y轴最大和最小值设置
            if(LeftAxisMinEdit.getText().toString() !=null && LeftAxisMinEdit.getText().toString().length()!=0){
                barChart.setLeftAxisMinValue(Float.parseFloat(LeftAxisMinEdit.getText().toString()));
            }else{
//                barChart.setLeftAxisMinValue(-50f);
            }
            if(LeftAxisMaxEdit.getText().toString() !=null && LeftAxisMaxEdit.getText().toString().length()!=0){
                barChart.setLeftAxisMaxValue(Float.parseFloat(LeftAxisMaxEdit.getText().toString()));
            }else{
//                barChart.setLeftAxisMaxValue(50f);
            }

            //右y轴最大和最小值设置
            if(RightAxisMinEdit.getText().toString() !=null && RightAxisMinEdit.getText().toString().length()!=0){
                barChart.setRightAxisMinValue(Float.parseFloat(RightAxisMinEdit.getText().toString()));
            }else{
//                barChart.setRightAxisMinValue(6f);
            }
            if(RightAxisMaxEdit.getText().toString() !=null && RightAxisMaxEdit.getText().toString().length()!=0){
                barChart.setRightAxisMaxValue(Float.parseFloat(RightAxisMaxEdit.getText().toString()));
            }else{
//                barChart.setRightAxisMaxValue(-6f);
            }

            barChart.setLeftAxisMinValue(0);

            //barChart.setLegendShape(Legend.LegendForm.SQUARE);

            barChart.animateY(1000);
            setData(10, 50);
        }
    }

    @AZListener(R.id.refreshbt)
    public void refresh(View view) {
        barChart.refresh().animateY(2000);
    }

    @AZListener(R.id.addbt)
    public void add(View view) {
        barChart.addData("new", 45);
    }

}
