package com.example.itakg.musicapp.Activities;

import android.support.v4.app.Fragment;

import com.example.itakg.musicapp.Fragments.SingleSongFragment;
import com.example.itakg.musicapp.Information.MySharedPreferences;
import com.example.itakg.musicapp.Messages.MyMessage;
import com.example.itakg.musicapp.R;

public class SingleSong extends FragmentActivity {
    private static final String KEY = "toolbar";

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected String setToolbarTitle() {
        return MySharedPreferences.getPreference(this, KEY);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.single_song_layout;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_single_song;
    }

    @Override
    protected Fragment getFragmentInstance() {
        return new SingleSongFragment();
    }

}
