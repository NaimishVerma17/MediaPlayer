package com.example.itakg.musicapp.Information;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.itakg.musicapp.Messages.MyMessage;

import java.util.ArrayList;

public class LoadSongs {
    Context context;

    public LoadSongs(Context context) {
        this.context = context;
    }

    private ArrayList<SongInformation> songs;


    public ArrayList<SongInformation> getSongs() {
        MyMessage.showLog(Thread.currentThread().getId() + "");
        songs = new ArrayList<>();
        Thread thread = new Thread(new MyThread());
        thread.start();
        return songs;
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            MyMessage.showLog(Thread.currentThread().getId() + "");
            final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            final String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
            Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        String songImage = String.valueOf(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                        if (songImage.equalsIgnoreCase("-1")) {
                            songImage = "R.drawable.real";
                        }
                        MyMessage.showLog(songImage);
                        String songURI = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                        String songTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                        songs.add(new SongInformation(songTitle, artist, songImage, songURI));
                    } while (cursor.moveToNext());
                }
            }

        }
    }
}
