package com.example.itakg.musicapp.Information;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.itakg.musicapp.R;
import com.example.itakg.musicapp.Views.MyBoldTextView;
import com.example.itakg.musicapp.Views.MyLightTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Bitmap b;
    private Context context;
    private static final int ANIMATION_TIME = 800;
    private ArrayList<SongInformation> informations;
    private LayoutInflater inflater;
    private SongClicked songClicked;

    public MyAdapter(Context context, ArrayList<SongInformation> informations) {
        this.context = context;
        this.informations = informations;
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.real);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void getInstance(SongClicked songClicked) {
        this.songClicked = songClicked;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.song_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SongInformation temp = informations.get(position);
        holder.songTitle.setText(temp.songTitle);
        holder.songArtist.setText(temp.songArtist);
        YoYo.with(Techniques.ZoomIn)
                .duration(ANIMATION_TIME)
                .playOn(holder.itemView);

        if (temp.songImage.equalsIgnoreCase("R.drawable.real")) {

            holder.songImage.setImageBitmap(b);
        } else {
            Drawable img = Drawable.createFromPath(temp.songImage);
            holder.songImage.setImageDrawable(img);
        }
    }

    @Override
    public int getItemCount() {
        return informations.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SongInformation current;
        @BindView(R.id.song_title)
        MyBoldTextView songTitle;

        @BindView(R.id.song_artist)
        MyLightTextView songArtist;

        @BindView(R.id.song_image)
        ImageView songImage;

        @BindView(R.id.play_button)
        ImageView playButton;

        @OnClick(R.id.play_button)
        public void clickedSong() {
            current = informations.get(getAdapterPosition());
            songClicked.clicked(getAdapterPosition(), current.songTitle, current.songURL);
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}