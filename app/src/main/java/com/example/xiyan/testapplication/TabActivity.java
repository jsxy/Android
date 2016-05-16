package com.example.xiyan.testapplication;

import com.example.xiyan.testapplication.fragmant.Fragment1;
import com.example.xiyan.testapplication.fragmant.Fragment2;
import com.example.xiyan.testapplication.fragmant.Fragment3;
import com.example.xiyan.testapplication.fragmant.Fragment4;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ui.activity.AZTabActivity;
import com.supconit.azpt.ui.fragment.AZTabFragment;

/**
 * Created by yanfei on 2016/4/19.
 */

public class TabActivity extends AZTabActivity {

    @AZAfter
    public void init() {
        //setAnimationEnabled(false);
    }

    @Override
    public AZTabFragment[] fragments() {
        return new AZTabFragment[] { new Fragment1(), new Fragment2(), new Fragment3(), new Fragment4()};
    }
}
