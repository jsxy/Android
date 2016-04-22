package com.example.xiyan.testapplication.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by xiyan on 16-4-12.
 */
public class Prompt extends View {
    private AlertDialog.Builder builder;

    public Prompt(Context context) {
        super(context);
        builder = new AlertDialog.Builder(context);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public void show(String title, String msg) {
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.create().show();;
    }
}