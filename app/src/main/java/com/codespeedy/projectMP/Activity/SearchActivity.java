package com.codespeedy.projectMP.Activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codespeedy.projectMP.Adapter.SearchAlbumAdapter;
import com.codespeedy.projectMP.Adapter.SearchArtistAdapter;
import com.codespeedy.projectMP.Adapter.SearchSongAdapter;
import com.codespeedy.projectMP.BottomSheet.SongInfoBottomSheet;
import com.codespeedy.projectMP.Model.AudioListModel;
import com.codespeedy.projectMP.R;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import static com.codespeedy.projectMP.Activity.MainActivity.AlbumArrayList;
import static com.codespeedy.projectMP.Activity.MainActivity.ArtistArrayList;
import static com.codespeedy.projectMP.Activity.MainActivity.AudioArrayList;
import static com.codespeedy.projectMP.Activity.MainActivity.PresentArrayList;
import static com.codespeedy.projectMP.Activity.MainActivity.controlMedia;
import static com.codespeedy.projectMP.Activity.MainActivity.playSong;
import static com.codespeedy.projectMP.Activity.MainActivity.position;
import static com.codespeedy.projectMP.Activity.MainActivity.setReference;


public class SearchActivity extends AppCompatActivity {

    ArrayList<AudioListModel> songsList;
    ArrayList<ArrayList<AudioListModel>> albumList, artistList;
    EditText search;
    RecyclerView song,album,artist;
    LinearLayout songLayout, albumLayout, artistLayout;
    ImageView searchMic, searchClear, closeBtn;
    TextView noSong, youtubeSearchText;
    LinearLayout youtubeSearch;

    SongInfoBottomSheet songInfoBottomSheet;
    SharedPreferences themePref;
    String theme;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        search = findViewById(R.id.search_edit_text);
        songLayout = findViewById(R.id.search_songs_layout);
        albumLayout = findViewById(R.id.search_albums_layout);
        artistLayout = findViewById(R.id.search_artists_layout);
        searchMic = findViewById(R.id.searchmic);
        searchClear = findViewById(R.id.search_clear);
        song = findViewById(R.id.search_songs_recView);
        album = findViewById(R.id.search_albums_recView);
        artist = findViewById(R.id.search_artists_recView);
        noSong = findViewById(R.id.no_songs);
        youtubeSearch = findViewById(R.id.youtube_search);
        youtubeSearchText = findViewById(R.id.youtube_search_text);
        closeBtn = findViewById(R.id.search_btn_close);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        LinearLayout linearLayout = findViewById(R.id.ll_1);
        TextView searchText = findViewById(R.id.search_text);
        songsList = new ArrayList<>();
        albumList = new ArrayList<>();
        artistList = new ArrayList<>();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DoOnTextChanged(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setText("");
            }
        });

        searchMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchActivity.this, "Will be given in Next Update!", Toast.LENGTH_SHORT).show();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        themePref = PreferenceManager.getDefaultSharedPreferences(MainActivity.activity);
        theme = themePref.getString("theme",null);
        if(theme!=null && theme.equals("dark")) {
         appBarLayout.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
         linearLayout.setBackgroundColor(getResources().getColor(R.color.colorDark));
         searchText.setTextColor(getResources().getColor(R.color.darkgrey));
         closeBtn.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.darkgrey));
         youtubeSearchText.setTextColor(getResources().getColor(R.color.darkgrey));


        }

    }

    private void DoOnTextChanged(CharSequence charSequence)
    {
        searchMic.setVisibility(View.GONE);
        searchClear.setVisibility(View.VISIBLE);
        noSong.setVisibility(View.GONE);
        youtubeSearch.setVisibility(View.VISIBLE);
        if(search.getText().toString().trim().length()==0)
        {
            searchMic.setVisibility(View.VISIBLE);
            searchClear.setVisibility(View.GONE);
            songLayout.setVisibility(View.GONE);
            albumLayout.setVisibility(View.GONE);
            artistLayout.setVisibility(View.GONE);
            noSong.setVisibility(View.VISIBLE);
            if(theme!=null && theme.equals("dark")) {
               noSong.setTextColor(getResources().getColor(R.color.darkgrey));
            }
            youtubeSearch.setVisibility(View.GONE);
        }
        else {

            try {
                setRecyclerViews(charSequence);
            }catch (IndexOutOfBoundsException e){}

        }
    }

    private void setRecyclerViews(CharSequence charSequence) throws IndexOutOfBoundsException
    {
          youtubeSearch.setVisibility(View.VISIBLE);
          youtubeSearch.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Toast.makeText(SearchActivity.this, "Will be given in Next Update!", Toast.LENGTH_SHORT).show();
              }
          });
          youtubeSearchText.setText("Search '"+charSequence.toString().trim()+"' on Youtube");
          songsList.clear();
          artistList.clear();
          albumList.clear();
        for (int i = 0; i < AudioArrayList.size(); i++) {
            if (AudioArrayList.get(i).getTrack().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                songsList.add(AudioArrayList.get(i));
            }
        }
        for(int i=0;i<AlbumArrayList.size();i++)
        {
            if(AlbumArrayList.get(i).size()!=0 && AlbumArrayList.get(i).get(0).getAlbum().toLowerCase().contains(charSequence.toString().toLowerCase()))
            {
                albumList.add(AlbumArrayList.get(i));
            }
        }

        for(int i=0;i<ArtistArrayList.size();i++)
        {
            if(ArtistArrayList.get(i).size()!=0 && ArtistArrayList.get(i).get(0).getArtist().toLowerCase().contains(charSequence.toString().toLowerCase()))
            {
                artistList.add(ArtistArrayList.get(i));
            }
        }

        //SONG make object of adater set list with it
        if (songsList.size() != 0) {
            RecyclerView.LayoutManager sLayoutManager = new LinearLayoutManager(this);
            SearchSongAdapter searchSongAdapter = new SearchSongAdapter(songsList,this);
            song.setHasFixedSize(true);
            song.setLayoutManager(sLayoutManager);
            song.setAdapter(searchSongAdapter);
        } else {
            songLayout.setVisibility(View.GONE);
//            noSong.setVisibility(View.VISIBLE);
//            if(theme!=null && theme.equals("dark")) {
//                noSong.setTextColor(getResources().getColor(R.color.darkgrey));
//            }
        }


        //ALBUM make object of adater set list with it
//        if (albumList.size() != 0) {
//            RecyclerView.LayoutManager alLayoutManager = new LinearLayoutManager(this);
//            SearchAlbumAdapter searchAlbumAdapter = new SearchAlbumAdapter(albumList,this);
//            album.setHasFixedSize(true);
//            album.setLayoutManager(alLayoutManager);
//            album.setAdapter(searchAlbumAdapter);
//
//
//        } else {
//            albumLayout.setVisibility(View.GONE);
//        }
//
//
//        //ARTIST make object of adater set list with it
//        if (artistList.size() != 0) {
//            artistLayout.setVisibility(View.VISIBLE);
//            RecyclerView.LayoutManager arLayoutManager = new LinearLayoutManager(this);
//            SearchArtistAdapter searchArtistAdapter = new SearchArtistAdapter(artistList,this);
//            artist.setHasFixedSize(true);
//            artist.setLayoutManager(arLayoutManager);
//            artist.setAdapter(searchArtistAdapter);
//
//
//        } else {
//            artistLayout.setVisibility(View.GONE);
//        }



    }




    public void onInfoSelected(int pos)
    {
        int actualPos = -1;
        for(int j=0;j<AudioArrayList.size();j++)
        {
            if(AudioArrayList.get(j).getTrack() == songsList.get(pos).getTrack())
            {
                actualPos = j;
                break;
            }
        }

        songInfoBottomSheet = new SongInfoBottomSheet(actualPos);
        songInfoBottomSheet.show(getSupportFragmentManager(),"fragment");

    }

    public void onSongSelected(int pos) throws Exception
    {
        onBackPressed();
        int actualPos = -1;
        for(int j=0;j<AudioArrayList.size();j++)
        {
            if(AudioArrayList.get(j).getTrack() == songsList.get(pos).getTrack())
            {
                actualPos = j;
                break;
            }
        }

        if(PresentArrayList!=null)
        {
            PresentArrayList=null;

        }
        PresentArrayList = AudioArrayList;
        position = actualPos;
        //Toast.makeText(context, ""+PresentArrayList.size(), Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putInt("position",position).apply();
        prefs.edit().putInt("which",0).apply();

        setReference();
        playSong();
        controlMedia();





    }




    @Override
    public void onBackPressed() {
        if(songInfoBottomSheet!=null && songInfoBottomSheet.isVisible())
        {
            songInfoBottomSheet.dismiss();
        }
        super.onBackPressed();

    }
}
