package com.example.itakg.musicapp.Fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.itakg.musicapp.Activities.MainActivity;
import com.example.itakg.musicapp.Information.MySharedPreferences;
import com.example.itakg.musicapp.Messages.MyMessage;
import com.example.itakg.musicapp.R;
import com.example.itakg.musicapp.Services.PlaySongServices;
import com.example.itakg.musicapp.Views.MyBoldTextView;
import com.example.itakg.musicapp.Views.MyLightTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class SingleSongFragment extends Fragment {
    private int flag = 1;
    @BindView(R.id.song_title)
    MyBoldTextView songTitle;
    @BindView(R.id.play_song)
    FancyButton playSong;
    @BindView(R.id.stop_song)
    FancyButton stopSong;
    @BindView(R.id.play_another_song)
    MyLightTextView playAnotherSong;
    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @OnClick(R.id.play_another_song)
    public void setPlayAnotherSong() {
        flag = 0;
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @OnClick(R.id.stop_song)
    public void stopSong(View view) {
        FancyButton button = (FancyButton) view;
        if (button.getText().equals("STOP")) {
            flag = 0;
            services.stopSong();
            getActivity().stopService(new Intent(getActivity(), PlaySongServices.class));
            getActivity().finish();
        }
    }

    @OnClick(R.id.play_song)
    public void controlSong(View view) {
        FancyButton button = (FancyButton) view;
        if (button.getText().equals("PAUSE")) {
            services.pauseSong();
            button.setText("PLAY");
        } else if (button.getText().equals("PLAY")) {
            services.restartSong();
            button.setText("PAUSE");
        }

    }

    private static final String KEY = "toolbar";
    public PlaySongServices services;
    private ServiceConnection connection;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_song_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        songTitle.setText(MySharedPreferences.getPreference(getContext(), KEY));
        final Intent intent = new Intent(getActivity(), PlaySongServices.class);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                PlaySongServices.MyIBinder myIBinder = (PlaySongServices.MyIBinder) iBinder;
                services = myIBinder.getInstance();
                updateStatusBar();
                MyMessage.showLog("1:BindService Called");
                MyMessage.showLog("2:Services value: " + services);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unbindService(connection);
    }

    private void updateStatusBar() {

        final int max = services.getDuration() / 1000;
        MyMessage.showLog("Max time:" + max);
        seekBar.setMax(max);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (getActivity() != null && flag == 1) {
                    MyMessage.showLog("Current time:" + services.getCurrentPosition());
                    seekBar.setProgress(services.getCurrentPosition() / 1000);
                }
            }
        }).start();
    }
}
