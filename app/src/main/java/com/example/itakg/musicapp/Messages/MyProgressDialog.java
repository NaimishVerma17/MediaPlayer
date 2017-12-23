package com.example.itakg.musicapp.Messages;

import android.app.ProgressDialog;
import android.content.Context;

public class MyProgressDialog {
    private static ProgressDialog progressDialog;

    public static void showDialog(Context context, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }
}
