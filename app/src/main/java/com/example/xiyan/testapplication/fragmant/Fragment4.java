package com.example.xiyan.testapplication.fragmant;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyan.testapplication.R;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;
import com.supconit.azpt.ioc.core.InjectCore;
import com.supconit.azpt.ui.fragment.AZTabFragment;
import com.supconit.azpt.ui.widget.AZDatePickerDialog;
import com.supconit.azpt.ui.widget.AZSelectMenu;
import com.supconit.azpt.ui.widget.AZTimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by xiyan on 2016/4/19.
 */
public class Fragment4 extends AZTabFragment {

    @AZView(R.id.select_only)
    private LinearLayout select_only;


    @AZView(R.id.select_more)
    private LinearLayout select_more;

    @AZView(R.id.selectfruit_only)
    private LinearLayout selectfruit_only;

    @AZView(R.id.selectfruit_more)
    private LinearLayout selectfruit_more;

    @AZView(R.id.data_edit)
    private EditText data_edit;
    @AZView(R.id.time_edit)
    private EditText time_edit;

    @AZView(R.id.sex_select)
    private Spinner sex_select;
    @AZView(R.id.Imagedata)
    private ImageButton Imagedata;

    @AZView(R.id.selectonly_edit)
    private EditText selectonly_edit;

    @AZView(R.id.selectmore_edit)
    private EditText selectmore_edit;

    @AZView(R.id.selectfruit_edit)
    private EditText selectfruit_edit;

    @AZView(R.id.selectfruitmore_edit)
    private EditText selectfruitmore_edit;


    @AZView
    private AZTimePickerDialog select_time;

    @AZView
    private AZDatePickerDialog select_date;

//    private  AZSelectMenu menu = new AZSelectMenu(getActivity());
    private AZTimePickerDialog.Style style = AZTimePickerDialog.Style.HOUR_MINUTE_SECOND;

//    private AZSelectMenu.Style select_style = AZSelectMenu.Style.SLIDE_AUTO_ARRANGE_SINGLE;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = createRootView(inflater, R.layout.orderinfolayout);
        InjectCore.inject(this, root);

        setTitle("我的");

//        TextView tv = (TextView) root.findViewById(R.id.home_tv);
//        tv.setText("Fragment Self");



        return root;
    }


//    @AZListener(R.id.Imagedata)
    @AZListener(value = {R.id.Imagedata},type = ListenerType.Click)
    public void selectdate(View view){
        AZDatePickerDialog select_date = new AZDatePickerDialog(getActivity());
        select_date.show();
        select_date.setOnSelectListener(new AZDatePickerDialog.OnSelectedListener() {
            @Override
            public void onSelect(Calendar calendar) {
                String date = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1)
                        + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
//                Toast.makeText(getActivity(), date, Toast.LENGTH_LONG).show();
                data_edit.setText(date);
            }
        });

    }


    @AZListener(value = {R.id.Imagetime},type = ListenerType.Click)
    public void selecttime(View view){
//        AZTimePickerDialog select_time = new AZTimePickerDialog(getActivity());
//        style = AZTimePickerDialog.Style.HOUR_MINUTE_SECOND;
//        select_time.setStyle(style).setThemeColor(0x7f0e0007).show();
        select_time.show();
        select_time.setOnSelectedListener(new AZTimePickerDialog.OnSelectedListener() {
            @Override
            public void onSelected(int hour, int minute, int second) {
                String date = hour + " 时 " + minute + " 分 " + second + " 秒 ";
//                Toast.makeText(getActivity(), hour + "  " + minute + "  " + second, Toast.LENGTH_LONG).show();
                time_edit.setText(date);
            }
        });

    }

    @AZListener(value = {R.id.select_only},type = ListenerType.Click)
    public void selectarea(View view){
        AZSelectMenu menu_area = new AZSelectMenu(getActivity());
        List<AZSelectMenu.SelectMenuItem> list = new ArrayList<AZSelectMenu.SelectMenuItem>();

        AZSelectMenu.SelectMenuItem selectItem1 = new AZSelectMenu.SelectMenuItem("杭州");
        selectItem1.setSelected(true);
        AZSelectMenu.SelectMenuItem selectItem2 = new AZSelectMenu.SelectMenuItem("上海");
        AZSelectMenu.SelectMenuItem selectItem3 = new AZSelectMenu.SelectMenuItem("苏州");
        AZSelectMenu.SelectMenuItem selectItem4 = new AZSelectMenu.SelectMenuItem("乌鲁木齐");
        list.add(selectItem1);
        list.add(selectItem2);
        list.add(selectItem3);
        list.add(selectItem4);
        menu_area.buildMenu("城市", list, AZSelectMenu.Style.DIALOG_AUTO_ARRANGE_SINGLE);
        menu_area.setOnSingleCheckedListener(new AZSelectMenu.OnSingleSelectedListener() {
            @Override
            public void onSelected(AZSelectMenu.SelectMenuItem selectItem, long selectedIndex) {
//                Toast.makeText(getActivity(), selectItem.getLabel(), Toast.LENGTH_SHORT).show();
                selectonly_edit.setText(selectItem.getLabel());
            }
        });
    }

    @AZListener(value = {R.id.select_more},type = ListenerType.Click)
    public void select_more(View view){
        AZSelectMenu menu_area = new AZSelectMenu(getActivity());
        List<AZSelectMenu.SelectMenuItem> list = new ArrayList<AZSelectMenu.SelectMenuItem>();

        ArrayList<String> data_list = new ArrayList<String>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");
        data_list.add("乌鲁木齐");
        for(int i=0;i<data_list.size();i++){
            AZSelectMenu.SelectMenuItem selectItem =new AZSelectMenu.SelectMenuItem(data_list.get(i));
            list.add(selectItem);
            if(i==0){
                selectItem.setSelected(true);
            }
        }
        menu_area.setThemeColor(Color.rgb(255,170,212));
        menu_area.buildMenu("城市", list, AZSelectMenu.Style.DIALOG_AUTO_ARRANGE_MULTI);
        menu_area.setOnMultiCheckedListener(new AZSelectMenu.OnMultiSelectedListener() {
            @Override
            public void onSelected(AZSelectMenu.SelectMenuItem[] selectItems, long[] selectedIndexs) {
                String lables = "";
                String indexs = "";
                String checkeds = "";
                for (AZSelectMenu.SelectMenuItem item : selectItems) {
                    lables += item.getLabel() + "  ";
                    checkeds += item.isSelected() + "  ";
                }
                for (long index : selectedIndexs) {
                    indexs += index + "  ";
                }
//                Toast.makeText(getActivity(), lables + "\n" + checkeds + "\n" + indexs, Toast.LENGTH_SHORT).show();
                selectmore_edit.setText(lables);
            }
        });

   }

    @AZListener(value = {R.id.selectfruit_only},type = ListenerType.Click)
    public void selectfruit(View view){
        AZSelectMenu menu_area = new AZSelectMenu(getActivity());
        List<AZSelectMenu.SelectMenuItem> list = new ArrayList<AZSelectMenu.SelectMenuItem>();
        ArrayList<String> data_list = new ArrayList<String>();
        data_list.add("苹果");
        data_list.add("香蕉");
        data_list.add("葡萄");
        data_list.add("火龙果");
        data_list.add("西瓜");
        data_list.add("哈密瓜");
        data_list.add("柚子");
        data_list.add("龙眼");
        data_list.add("猕猴桃");
        data_list.add("枇杷");
        data_list.add("凤梨");
        data_list.add("桑椹");
        data_list.add("人参果");
        for(int i=0;i<data_list.size();i++){
            AZSelectMenu.SelectMenuItem selectItem =new AZSelectMenu.SelectMenuItem(data_list.get(i));
            list.add(selectItem);
            if(i==0){
                selectItem.setSelected(true);
            }
        }
        menu_area.setThemeColor(Color.rgb(255,170,212));
        menu_area.buildMenu("水果", list, AZSelectMenu.Style.SLIDE_LIST_SINGLE);
        menu_area.setOnSingleCheckedListener(new AZSelectMenu.OnSingleSelectedListener() {
            @Override
            public void onSelected(AZSelectMenu.SelectMenuItem selectItem, long selectedIndex) {
                Toast.makeText(getActivity(), selectItem.getLabel(), Toast.LENGTH_SHORT).show();
                selectfruit_edit.setText(selectItem.getLabel());
            }
        });
    }


    @AZListener(value = {R.id.selectfruit_more},type = ListenerType.Click)
    public void selectruit_more(View view){
        AZSelectMenu menu_area = new AZSelectMenu(getActivity());
        List<AZSelectMenu.SelectMenuItem> list = new ArrayList<AZSelectMenu.SelectMenuItem>();
        ArrayList<String> data_list = new ArrayList<String>();
        data_list.add("苹果");
        data_list.add("香蕉");
        data_list.add("葡萄");
        data_list.add("火龙果");
        data_list.add("西瓜");
        data_list.add("哈密瓜");
        data_list.add("柚子");
        data_list.add("龙眼");
        data_list.add("猕猴桃");
        data_list.add("枇杷");
        data_list.add("凤梨");
        data_list.add("桑椹");
        data_list.add("人参果");
        for(int i=0;i<data_list.size();i++){
            AZSelectMenu.SelectMenuItem selectItem =new AZSelectMenu.SelectMenuItem(data_list.get(i));
            list.add(selectItem);
            if(i==0){
                selectItem.setSelected(true);
            }
        }
        menu_area.buildMenu("水果", list, AZSelectMenu.Style.SLIDE_AUTO_ARRANGE_MULTY);
        menu_area.setOnMultiCheckedListener(new AZSelectMenu.OnMultiSelectedListener() {
            @Override
            public void onSelected(AZSelectMenu.SelectMenuItem[] selectItems, long[] selectedIndexs) {
                String lables = "";
                String indexs = "";
                String checkeds = "";
                for (AZSelectMenu.SelectMenuItem item : selectItems) {
                    lables += item.getLabel() + "  ";
                    checkeds += item.isSelected() + "  ";
                }
                for (long index : selectedIndexs) {
                    indexs += index + "  ";
                }
//                Toast.makeText(getActivity(), lables + "\n" + checkeds + "\n" + indexs, Toast.LENGTH_SHORT).show();
                selectfruitmore_edit.setText(lables);
            }
        });

    }

    @Override
    public int initCheckedResId() {
        return R.mipmap.self_checked;
    }

    @Override
    public int initUncheckedResId() {
        return R.mipmap.self_unchecked;
    }

    @Override
    public boolean initChecked() {
        return false;
    }

    @Override
    public String initTabLable() {
        return "我的";
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
