package com.abcdroid.kommmida.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class Util {

    public static AlertDialog dialog(String content, Activity a){
        return new AlertDialog.Builder(a).setTitle("Mensaje")
                .setMessage(content)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
