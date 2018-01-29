package com.bentaher.aiomovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mocro on 29/01/2018.
 */

public class TorrentAdapter extends ArrayAdapter {

    private ArrayList<Torrent> torrentList;
    private Context context;
    private TorrentResultActivity torrentResultActivity;

    public TorrentAdapter(@NonNull Context context, ArrayList<Torrent> data) {
        super(context, 0, data);

        this.torrentList = data;
        this.torrentResultActivity = (TorrentResultActivity) context;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.torrentlistlayout, parent, false);
        }

        final Torrent tk = torrentList.get(position);

        final String mvieTitle = tk.getTitle();
        final String mvieSize= tk.getSize();

        final TextView torrentNm = (TextView) view.findViewById(R.id.torrentName);
        final TextView torrentSz = (TextView) view.findViewById(R.id.torrentSize);

        torrentNm.setText(mvieTitle);
        torrentNm.setText(mvieSize);

        //view.setOnClickListener(new MovieAdapter.GetOptions(tk));

        return view;
    }

    public int getCount(){
        return  super.getCount();
    }

}
