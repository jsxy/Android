package com.example.xiyan.testapplication.chart;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xiyan.testapplication.R;
import com.supconit.azpt.chart.charts.PieChart;
import com.supconit.azpt.chart.components.Title;
import com.supconit.azpt.chart.data.DataElement;
import com.supconit.azpt.chart.data.PieData;
import com.supconit.azpt.chart.data.PieDataSet;
import com.supconit.azpt.chart.formatter.PercentFormatter;
import com.supconit.azpt.chart.utils.ColorTemplate;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;

import java.io.File;
import java.util.ArrayList;

@AZActivity(R.layout.activity_piechart)
public class PiechartActivity extends BaseDemo {
    @AZView
    private PieChart pieChart;

    @AZView
    private EditText TextColorEdit,setTitleEdit,TextSizeEdit,animateXEdit,animateYEdit,setCenterTitleEdit,TextCenterSizeEdit,HoleRadiusEdit,HoleColorEdit;

    @AZView
    private TextView IntroduceView,PositionView,titleView,TextColorView,setTitleView,TextSizeView,animateXView,animateYView,setCenterTitleView,TextCenterSizeView,HoleRadiusView,HoleColorView;

    @AZView
    private RadioGroup TitlePosition,HoleEnabledEdit;

    private boolean HoleEnabled;

    @AZAfter
    public void init() {

        TitlePosition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.title_top:
                        pieChart.setTitlePosition(Title.TitlePosition.TOP_CHART_CENTER);
                        break;
                    case R.id.title_bottom:
                        pieChart.setTitlePosition(Title.TitlePosition.BOTTOM_CHART_CENTER);
                        break;
                    default:
                        break;
                }
            }
        });
        HoleEnabledEdit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.HoleEnabledEdit_true:
                        HoleEnabled =true;
                        break;
                    case R.id.HoleEnabledEdit_false:
                        HoleEnabled =false;
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void setData(int count, float range) {

        float mult = range;
        ArrayList<DataElement> yVals1 = new ArrayList<DataElement>();
        for (int i = 0; i < count; i++) {
            yVals1.add(new DataElement((float) (Math.random() * mult) + mult / 5, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

       /* for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);*/

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);
        //data.setValueTypeface(tf);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }


    @AZListener(value = {R.id.createbt}, type= ListenerType.Click)
    public void createchart(View view){
        if(view.getId() == R.id.createbt) {

            //标题
            if(setTitleEdit.getText().toString() !=null && setTitleEdit.getText().toString().length()!=0){
                pieChart.setTitle(setTitleEdit.getText().toString());
            }else{
                pieChart.setTitle("Test图表标题");
            }
            pieChart.setTitleTextColor(Color.RED);
            if(TextSizeEdit.getText().toString() !=null && TextSizeEdit.getText().toString().length()!=0){
                pieChart.setTitleTextSize(Float.parseFloat(TextSizeEdit.getText().toString()));
            }else {
                pieChart.setTitleTextSize(15);
            }

            //中间标题是否显示、内圈半径大小颜色、外圈半径大小颜色
            pieChart.setDrawHoleEnabled(HoleEnabled);
            if(HoleRadiusEdit.getText().toString() !=null && HoleRadiusEdit.getText().toString().length()!=0){
                pieChart.setHoleRadius(Float.parseFloat(HoleRadiusEdit.getText().toString()));
            }else {
                pieChart.setHoleRadius(20f);
            }

//          pieChart.setHoleColor(Color.TRANSPARENT);透明色
            pieChart.setTransparentCircleRadius(25f);
            pieChart.setTransparentCircleColor(Color.YELLOW);
            pieChart.setDrawSlicesUnderHole(false);//是否盖住圈
            pieChart.setRotationEnabled(true); //可以手动旋转
//          pieChart.setUsePercentValues(true);//显示成百分比

            if(setCenterTitleEdit.getText().toString() !=null && setCenterTitleEdit.getText().toString().length()!=0){
                pieChart.setCenterText(setCenterTitleEdit.getText().toString());
            }else{
                pieChart.setCenterText("TestCenter标题");
            }
            pieChart.setCenterTextColor(Color.RED);
            if(TextCenterSizeEdit.getText().toString() !=null && TextCenterSizeEdit.getText().toString().length()!=0){
                pieChart.setCenterTextSize(Float.parseFloat(TextCenterSizeEdit.getText().toString()));
            }else{
                pieChart.setCenterTextSize(20f);
            }
            pieChart.setCenterTextSizePixels(20f);


            //x和y轴生成数据点的时间间隔
            if(animateXEdit.getText().toString() !=null && animateXEdit.getText().toString().length()!=0){
                pieChart.animateX(Integer.parseInt(animateXEdit.getText().toString()));
            } else {
                pieChart.animateX(2000);
            }
            if(animateYEdit.getText().toString() !=null && animateYEdit.getText().toString().length()!=0){
                pieChart.animateY(Integer.parseInt(animateYEdit.getText().toString()));
            }else{
                pieChart.animateY(1500);
            }

            pieChart.setBackgroundResource(R.color.colorLightGreen);



            File file = getCacheDir();
            File[] files = file.listFiles();

            pieChart.setBackgroundColor(getResources().getColor(R.color.colorLightGreen));
            //pieChart.getTitle().setTitle("股份构成图");
            //pieChart.getTitle().setTextSize(15);
            //pieChart.setTitlePosition(Title.TitlePosition.BOTTOM_CHART_CENTER);
            //pieChart.setTransparentCircleRadius(0);

            //pieChart.getTitle().setPosition(Title.Position.BOTTOM_CHART_CENTER);
            //pieChart.getLegend().setEnabled(false);
            //pieChart.setLegendPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);


            setData(5, 100);
        }
    }


    @AZListener(R.id.refreshbt)
    public void refresh(View view) {
        pieChart.refresh().animateX(500);
    }
}
