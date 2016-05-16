package com.example.xiyan.testapplication.fragmant;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyan.testapplication.R;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
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
public class Fragment3 extends AZTabFragment {


    private AZTimePickerDialog.Style style_hour = AZTimePickerDialog.Style.HOUR;
    private AZTimePickerDialog.Style style_min = AZTimePickerDialog.Style.HOUR_MINUTE;
    private AZTimePickerDialog.Style style_sec = AZTimePickerDialog.Style.HOUR_MINUTE_SECOND;


    @AZView(R.id.show_dataonly)
    private Button show_dataonly;

    @AZView(R.id.dataonly_Edit)
    private EditText dataonly_Edit;


    @AZView(R.id.show_timeonly)
    private Button show_timeonly;

    @AZView(R.id.timeonly_Edit)
    private EditText timeonly_Edit;

    @AZView(R.id.selectonly_Edit)
    private EditText selectonly_Edit;

    @AZView
    private AZTimePickerDialog dialogTime;
    @AZView
    private AZDatePickerDialog dialogDate;
    @AZView
    private AZSelectMenu menu;

    private int color;
    private AZSelectMenu.Style style = AZSelectMenu.Style.SLIDE_AUTO_ARRANGE_SINGLE;


    @AZListener(R.id.show_dataonly)
    public void show(View view) {
        AZTimePickerDialog dialogTime = new AZTimePickerDialog(getActivity());
        color =ContextCompat.getColor(getActivity(), com.supconit.azpt.ui.R.color.azpt_common_bg_green);
        dialogTime.setStyle(style_hour).setThemeColor(color).show();
        dialogTime.setOnSelectedListener(new AZTimePickerDialog.OnSelectedListener() {
            @Override
            public void onSelected(int hour, int minute, int second) {
                //Toast.makeText(getActivity(), hour + "  " + minute + "  " + second, Toast.LENGTH_LONG).show();
                String time =hour + ":" + (minute == 0 ? "00" : minute) + ":" + (second == 0 ? "00" : second);
                dataonly_Edit.setText(time);
            }
        });
    }

    @AZListener(R.id.show_timeonly)
    public void showCalendar(View view) {
        AZDatePickerDialog dialogDate = new AZDatePickerDialog(getActivity());
        color =ContextCompat.getColor(getActivity(), com.supconit.azpt.ui.R.color.azpt_common_bg_green);
        dialogDate.setThemeColor(color).show();
        dialogDate.setOnSelectListener(new AZDatePickerDialog.OnSelectedListener() {
            @Override
            public void onSelect(Calendar calendar) {
                String date = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1)
                        + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
                Toast.makeText(getActivity(), date, Toast.LENGTH_LONG).show();
                timeonly_Edit.setText(date);
            }
        });
    }



    @AZListener(R.id.show_selectonly)
    public void select(View view) {
        AZSelectMenu menu = new AZSelectMenu(getActivity());
        List<AZSelectMenu.SelectMenuItem> list = new ArrayList<AZSelectMenu.SelectMenuItem>();
        int num = 1;
        for (int i = 0; i < 10; i++) {
            num = num * 2;
            AZSelectMenu.SelectMenuItem selectItem = new AZSelectMenu.SelectMenuItem(num + "杭州");
            if (i == 0)
                selectItem.setSelected(true);
            list.add(selectItem);
        }

        //menu.setThemeColor(color);
        menu.buildMenu("选择分类", list, style);
        menu.setOnSingleCheckedListener(new AZSelectMenu.OnSingleSelectedListener() {
            @Override
            public void onSelected(AZSelectMenu.SelectMenuItem selectItem, long selectedIndex) {
                Toast.makeText(getActivity(), selectItem.getLabel(), Toast.LENGTH_SHORT).show();
            }
        });
        menu.setOnMultiCheckedListener(new AZSelectMenu.OnMultiSelectedListener() {
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
                Toast.makeText(getActivity(), lables + "\n" + checkeds + "\n" + indexs, Toast.LENGTH_SHORT).show();
                selectonly_Edit.setText(lables + "\n" + checkeds + "\n" + indexs);
            }
        });
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = createRootView(inflater, R.layout.datatimelayout);
        InjectCore.inject(this, root);

        setTitle("列表");

        TextView tv = (TextView) root.findViewById(R.id.home_tv);
//        tv.setText("Fragment List");
        return root;
    }

    @Override
    public int initCheckedResId() {
        return R.mipmap.keypad_checked;
    }

    @Override
    public int initUncheckedResId() {
        return R.mipmap.keypad_unchecked;
    }

    @Override
    public boolean initChecked() {
        return false;
    }
    @Override
    public String initTabLable() {
        return "列表";
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
