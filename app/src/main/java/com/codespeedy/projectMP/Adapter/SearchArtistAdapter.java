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



public class SearchArtistAdapter extends RecyclerView.Adapter<SearchArtistAdapter.SearchArtistViewHolder> {


    private ArrayList<ArrayList<AudioListModel>> audioListModelsArtist;
    private Context context;
    public static class SearchArtistViewHolder extends RecyclerView.ViewHolder{


        public TextView artistName,count;
        public ImageView albumImage;


        public SearchArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.search_artist_art);
            artistName = itemView.findViewById(R.id.search_artist_name);
            count = itemView.findViewById(R.id.search_artist_count);

        }

    }

    public SearchArtistAdapter(ArrayList<ArrayList<AudioListModel>> arrayList, Context context)
    {
        this.audioListModelsArtist = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public SearchArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_song_items,parent,false);
        return new SearchArtistViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final SearchArtistViewHolder holder, final int position) {


        holder.artistName.setText(audioListModelsArtist.get(position).get(0).getArtist());
        holder.count.setText(audioListModelsArtist.get(position).size());
        Glide.with(context)
                .load(audioListModelsArtist.get(position).get(0).getAlbumArtUri())
                .error(R.drawable.ic_album_black_24dp)
                .into(holder.albumImage);
    }

    @Override
    public int getItemCount() {
        return audioListModelsArtist.size();
    }




}