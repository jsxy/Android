package com.example.xiyan.testapplication.chart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xiyan.testapplication.R;
import com.supconit.azpt.chart.charts.LineChart;
import com.supconit.azpt.chart.components.Title;
import com.supconit.azpt.chart.data.DataElement;
import com.supconit.azpt.chart.data.LineData;
import com.supconit.azpt.chart.data.LineDataSet;
import com.supconit.azpt.chart.formatter.PercentFormatter;
import com.supconit.azpt.chart.interfaces.datasets.ILineDataSet;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;

import org.apache.log4j.Level;

import java.util.ArrayList;

@AZActivity(R.layout.activity_linechart)
public class LinechartActivity extends Activity {

    @AZView
    private TextView   IntroduceView,titleView,setTitleView,TextSizeView,TextColorView,PositionView,animateView,animateXView,RightAxisValueFormatterView;

    @AZView
    private TextView animateYView,animateXYView,AxisView,LeftAxisMaxView,LeftAxisMinView,RightAxisMaxView,RightAxisMinView,otherView,GridEnableView,FillcolorView,FillAlphaView,ValueTextColorView,ValueTextSizeView;

    @AZView
    private EditText  setTitleEdit,TextSizeEdit,TextColorEdit,animateXEdit,animateYEdit,animateXYEdit,LeftAxisMaxEdit,LeftAxisMinEdit,FillcolorEdit,FillAlphaEdit,ValueTextColorEdit,ValueTextSizeEdit ;

    @AZView
    private EditText RightAxisMaxEdit,RightAxisMinEdit;

    @AZView
    private CheckBox GridEnable_x,GridEnable_y;

    @AZView
    private Button createbt,addbt,refreshbt;

    @AZView
    private RadioGroup TitlePosition,DrawFillRadio;

    @AZView
    private LineChart lineChart;

    private boolean grade_x,grade_y,DrawFillvalue;

    @AZAfter
    public void init() {
        TitlePosition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.title_top:
                        lineChart.setTitlePosition(Title.TitlePosition.TOP_CHART_CENTER);
                        break;
                    case R.id.title_bottom:
                        lineChart.setTitlePosition(Title.TitlePosition.BOTTOM_CHART_CENTER);
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
            xVals.add(("" + i));
        }
//line1
        ArrayList<DataElement> yVals = new ArrayList<DataElement>();

        for (int i = 0; i < count; i++) {

            float mult = (range + 10);
            float val = (float) (Math.random() * mult) + 3;// + (float)

            yVals.add(new DataElement(val, i));
        }
//line2
        ArrayList<DataElement> yVals2 = new ArrayList<DataElement>();

        for (int i = 0; i < count; i++) {

            float mult = (range + 2);
            float val = (float) (Math.random() * mult) + 6;// + (float)

            yVals2.add(new DataElement(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "【随机值曲线1是某一天的心情指数】");
        LineDataSet set2 = new LineDataSet(yVals2, "【随机值曲线2是某一天的心情指数】");

        //数值圆圈的颜色
        set1.setCircleColor(getResources().getColor(R.color.colorAccent));
        //数值圆圈是否空心
        set1.setDrawCircleHole(true);
        //数值圆圈大小
        set1.setCircleRadius(3);

        //是否显示折线
        set1.setVisible(true);
        //线条颜色
        set1.setColor(Color.rgb(255, 0, 0));
        //线条的粗细
        set1.setLineWidth(2f);
        //设置点之间连线的平滑度，数值越大越平滑
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);

        //是否显示填色
        set1.setDrawFilled(DrawFillvalue);
        set1.setFillColor(Color.RED);
        //曲线和x轴的填充颜色透明度
        if(FillAlphaEdit.getText().toString() !=null && FillAlphaEdit.getText().toString().length() !=0) {
            set1.setFillAlpha(Integer.parseInt(FillAlphaEdit.getText().toString()));
        }else {
            set1.setFillAlpha(80);
        }

        //是否显示数值，数值颜色大小
        set1.setDrawValues(true);
        set1.setValueTextColor(Color.BLUE);
        if(ValueTextSizeEdit.getText().toString() !=null && ValueTextSizeEdit.getText().toString().length() !=0) {
            set1.setValueTextSize(Integer.parseInt(ValueTextSizeEdit.getText().toString()));
        }else {
            set1.setValueTextSize(10);
        }

//      set1.setValueTextColors();
//       set1.setValueTypeface(Typeface.SANS_SERIF);

        //线条颜色淡紫色（和透明度set2.setColor(Color.rgb(132,112, 255)),100）
        set2.setColor(Color.rgb(132,112, 255));
        set2.setCircleColor(getResources().getColor(R.color.colorHui));

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets
        dataSets.add(set2);
        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        lineChart.setData(data);
    }



    @AZListener(value = {R.id.createbt}, type= ListenerType.Click)
    public void createchart(View view){
        if(view.getId() == R.id.createbt) {
            if(setTitleEdit.getText().toString() !=null && setTitleEdit.getText().toString().length()!=0){
                lineChart.setTitle(setTitleEdit.getText().toString());
            }else{
                lineChart.setTitle("Test图表标题");
            }
            lineChart.setTitleTextColor(Color.RED);
            if(TextSizeEdit.getText().toString() !=null && TextSizeEdit.getText().toString().length()!=0){
                lineChart.setTitleTextSize(Float.parseFloat(TextSizeEdit.getText().toString()));
            }else {
                lineChart.setTitleTextSize(15);
            }

            //x和y轴生成数据点的时间间隔
            if(animateXEdit.getText().toString() !=null && animateXEdit.getText().toString().length()!=0){
                lineChart.animateX(Integer.parseInt(animateXEdit.getText().toString()));
            }else{
                lineChart.animateX(2000);
            }
            if(animateYEdit.getText().toString() !=null && animateYEdit.getText().toString().length()!=0){
                lineChart.animateY(Integer.parseInt(animateYEdit.getText().toString()));
            }else{
                lineChart.animateY(3000);
            }

//          lineChart.animateXY(Integer.parseInt(animateXEdit.getText().toString()), Integer.parseInt(animateYEdit.getText().toString()));

            lineChart.setBackgroundResource(R.color.colorLightGreen);
            //设置显示坐标轴
            lineChart.setAxisEnabled(true, true, true);

            //左y轴最大和最小值设置
            if(LeftAxisMinEdit.getText().toString() !=null && LeftAxisMinEdit.getText().toString().length()!=0){
                lineChart.setLeftAxisMinValue(Float.parseFloat(LeftAxisMinEdit.getText().toString()));
            }else{
//                lineChart.setLeftAxisMinValue(-50f);
            }
            if(LeftAxisMaxEdit.getText().toString() !=null && LeftAxisMaxEdit.getText().toString().length()!=0){
                lineChart.setLeftAxisMaxValue(Float.parseFloat(LeftAxisMaxEdit.getText().toString()));
            }else{
//                lineChart.setLeftAxisMaxValue(50f);
            }

            //右y轴最大和最小值设置
            if(RightAxisMinEdit.getText().toString() !=null && RightAxisMinEdit.getText().toString().length()!=0){
                lineChart.setRightAxisMinValue(Float.parseFloat(RightAxisMinEdit.getText().toString()));
            }else{
//                lineChart.setRightAxisMinValue(6f);
            }
            if(RightAxisMaxEdit.getText().toString() !=null && RightAxisMaxEdit.getText().toString().length()!=0){
                lineChart.setRightAxisMaxValue(Float.parseFloat(RightAxisMaxEdit.getText().toString()));
            }else{
//                lineChart.setRightAxisMaxValue(-6f);
            }

            //是否显示x和y轴的网格
            lineChart.setGridEnable(grade_x, grade_y);

//          lineChart.setLeftAxisValueFormatter(new PercentFormatter());
//          lineChart.setRightAxisValueFormatter(new PercentFormatter());
//          lineChart.setXAxisValueFormatter();

            setData(8, -20);
        }
    }


    @AZListener(R.id.addbt)
    public void addData(View view) {
        lineChart.addData((float) (Math.random() * 100) + 3);
    }

    @AZListener(R.id.refreshbt)
    public void refresh(View view) {
        lineChart.refresh().animateX(3000);
    }


}
