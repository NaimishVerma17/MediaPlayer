package com.example.itakg.musicapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itakg.musicapp.Activities.SingleSong;
import com.example.itakg.musicapp.Information.LoadSongs;
import com.example.itakg.musicapp.Information.MyAdapter;
import com.example.itakg.musicapp.Information.MySharedPreferences;
import com.example.itakg.musicapp.Information.SongClicked;
import com.example.itakg.musicapp.R;
import com.example.itakg.musicapp.Services.PlaySongServices;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements SongClicked {
    private LoadSongs loadSongs;
    private MyAdapter adapter;
    private static final String TOOLBAR_TITLE = "toolbar";
    private static final String SONG_URL = "songurl";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadSongs = new LoadSongs(getContext());
        adapter = new MyAdapter(getContext(), loadSongs.getSongs());
        adapter.getInstance(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void clicked(int position, String title, String url) {
        //MyMessage.showToast(getContext(), "" + position);
        MySharedPreferences.setPreference(getContext(), SONG_URL, url);
        MySharedPreferences.setPreference(getContext(), TOOLBAR_TITLE, title);
        Intent intent = new Intent(getActivity(), PlaySongServices.class);
        getActivity().startService(intent);
        startActivity(new Intent(getActivity(), SingleSong.class));
    }
}
