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

public class BolAdapter extends ArrayAdapter {

    private ArrayList<Bol> bolList;
    private Context context;
    private BolResultActivity bolResultActivity;

    public BolAdapter(@NonNull Context context, ArrayList<Bol> data) {
        super(context, 0, data);

        this.bolList = data;
        this.bolResultActivity = (BolResultActivity) context;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.bollistlayout, parent, false);
        }

        final Bol tk = bolList.get(position);

        final String mvieTitle = tk.getTitle();
        final String mviePrice = tk.getPrice();

        final TextView movieNm = (TextView) view.findViewById(R.id.bolName);
        final TextView moviePrc = (TextView) view.findViewById(R.id.bolPrice);

        movieNm.setText(mvieTitle);
        moviePrc.setText(mviePrice);

        //view.setOnClickListener(new MovieAdapter.GetOptions(tk));

        return view;
    }

    public int getCount(){
        return  super.getCount();
    }

}
