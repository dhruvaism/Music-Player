package com.codespeedy.projectMP.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.codespeedy.projectMP.Model.AudioListModel;
import com.codespeedy.projectMP.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;



public class SearchSongAdapter extends RecyclerView.Adapter<SearchSongAdapter.SearchSongViewHolder> {


    private ArrayList<AudioListModel> audioListModels;
    private Context context;
    public static class SearchSongViewHolder extends RecyclerView.ViewHolder{

        public TextView songName,songArtist;


        public SearchSongViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.search_songs_name);
            songArtist = itemView.findViewById(R.id.search_songs_artist);
        }

    }

    public SearchSongAdapter(ArrayList<AudioListModel> arrayList, Context context)
    {
        this.audioListModels = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public SearchSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_song_items,parent,false);
        return new SearchSongViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final SearchSongViewHolder holder, final int position) {


      holder.songName.setText(audioListModels.get(position).getTrack());
      holder.songArtist.setText(audioListModels.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return audioListModels.size();
    }




}