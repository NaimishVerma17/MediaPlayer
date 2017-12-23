package com.example.itakg.musicapp.Messages;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MyMessage {
    public static void showLog(String message) {
        Log.i("Message", message);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
