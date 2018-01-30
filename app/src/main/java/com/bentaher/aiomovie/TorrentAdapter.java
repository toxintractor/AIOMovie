package com.bentaher.aiomovie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        final String mvieImage= tk.getImagelink();

        Log.i("torrent iamge:", mvieImage);

        final TextView torrentNm = (TextView) view.findViewById(R.id.torrentName);
        final TextView torrentSz = (TextView) view.findViewById(R.id.torrentSize);
        final ImageView torrentImg = (ImageView) view.findViewById(R.id.imageTrnt);

        torrentNm.setText(mvieTitle);
        torrentSz.setText(mvieSize);

        Picasso.with(context).load(mvieImage).into(torrentImg);

        view.setOnClickListener(new TorrentAdapter.GoLink(tk));

        return view;
    }

    public int getCount(){
        return  super.getCount();
    }

    public class GoLink implements View.OnClickListener {

        Torrent tk1;
        public GoLink(Torrent tk2){
            tk1 = tk2;
        }
        @Override
        public void onClick(View view) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tk1.getMagnetlink()));
            context.startActivity(browserIntent);

        }
    }

}
