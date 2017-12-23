package com.example.itakg.musicapp.Messages;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class MyAlertDialog {
    private static AlertDialog.Builder dialog;
    private static final String TITLE = "MESSAGE";
    private static final String POSITIVE_BUTTON = "OKAY";

    public static void showDialog(Context context, String message) {
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(TITLE)
                .setMessage(message)
                .setPositiveButton(POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}
