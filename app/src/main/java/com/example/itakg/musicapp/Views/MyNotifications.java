package com.example.itakg.musicapp.Views;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.itakg.musicapp.Activities.SingleSong;
import com.example.itakg.musicapp.Information.MySharedPreferences;
import com.example.itakg.musicapp.R;

public class MyNotifications {
    public static Notification showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, SingleSong.class), 0);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Let's Play Some Music")
                .setTicker("Let's Play Some Music")
                .setContentText(MySharedPreferences.getPreference(context, "toolbar"))
                .setSmallIcon(R.drawable.notification)
                .setContentIntent(contentIntent)
                .build();
        return notification;

    }
}
