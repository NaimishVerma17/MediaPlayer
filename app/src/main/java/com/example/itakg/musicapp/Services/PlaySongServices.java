package com.example.itakg.musicapp.Services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.itakg.musicapp.Information.MySharedPreferences;
import com.example.itakg.musicapp.Messages.MyMessage;
import com.example.itakg.musicapp.Views.MyNotifications;

import java.io.IOException;

public class PlaySongServices extends Service implements MediaPlayer.OnCompletionListener {
    private IBinder mIBinder = new MyIBinder();
    private MediaPlayer mediaPlayer;
    private int currentLength;
    private static final String SONG_URL = "songurl";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopSelf();
    }

    public class MyIBinder extends Binder {
        public PlaySongServices getInstance() {
            return PlaySongServices.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        playSong();
        showNotification();
        MyMessage.showLog("Start service called : ");
        return START_STICKY;
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }


    private void showNotification() {
        startForeground(101, MyNotifications.showNotification(this));
    }


    public void playSong() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(MySharedPreferences.getPreference(this, SONG_URL));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void pauseSong() {
        currentLength = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
    }

    public void restartSong() {
        mediaPlayer.seekTo(currentLength);
        mediaPlayer.start();
    }

    public void stopSong() {
        mediaPlayer.stop();
    }

}

