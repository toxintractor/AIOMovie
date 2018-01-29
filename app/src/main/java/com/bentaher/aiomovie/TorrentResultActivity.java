package com.bentaher.aiomovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TorrentResultActivity extends AppCompatActivity {

    ListView torrentList;
    ArrayList<Torrent> torrentArray = new ArrayList<>();
    private TorrentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent_result);

        torrentArray = (ArrayList<Torrent>) getIntent().getSerializableExtra("dataArray");

        setAdapter();
    }

    //Functie om de adapter aan te roepen en te laten zien.
    public  void setAdapter(){
        adapter = new TorrentAdapter(this, torrentArray);
        torrentList = (ListView) findViewById(R.id.torrentLst);
        torrentList.setAdapter(adapter);

    }
}
