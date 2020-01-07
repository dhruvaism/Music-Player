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



public class SearchAlbumAdapter extends RecyclerView.Adapter<SearchAlbumAdapter.SearchAlbumViewHolder> {


    ArrayList<ArrayList<AudioListModel>> audioListModelsAlbum;
    private Context context;
    public static class SearchAlbumViewHolder extends RecyclerView.ViewHolder {


        public TextView albumName, count;
        public ImageView albumImage;


        public SearchAlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            albumImage = itemView.findViewById(R.id.search_album_art);
            albumName = itemView.findViewById(R.id.search_album_name);
            count = itemView.findViewById(R.id.search_album_count);

        }
    }

    public SearchAlbumAdapter( ArrayList<ArrayList<AudioListModel>> arrayList, Context context)
    {
        this.audioListModelsAlbum = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public SearchAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_song_items,parent,false);
        return new SearchAlbumViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final SearchAlbumViewHolder holder, final int position) {


        holder.albumName.setText(audioListModelsAlbum.get(position).get(0).getTrack());
        holder.count.setText(audioListModelsAlbum.get(position).size());
        Glide.with(context)
                .load(audioListModelsAlbum.get(position).get(0).getAlbumArtUri())
                .error(R.drawable.ic_album_black_24dp)
                .into(holder.albumImage);
    }

    @Override
    public int getItemCount() {
        return audioListModelsAlbum.size();
    }




}