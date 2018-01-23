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
 * Created by mocro on 23/01/2018.
 */

public class MovieAdapter extends ArrayAdapter {

    private ArrayList<Movie> movieList;
    private Context context;
    private SearchResultActivity resultActivity;

    public MovieAdapter(@NonNull Context context, ArrayList<Movie> data) {
        super(context, 0, data);

        this.movieList = data;
        this.resultActivity = (SearchResultActivity) context;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.movielistlayout, parent, false);
        }

        final Movie tk = movieList.get(position);

        final String mvieTitle = tk.getTitle();
        final String mvieRating= tk.getRating();

        final TextView nameTxt = (TextView) view.findViewById(R.id.movieName);
        final TextView ratingTxt = (TextView) view.findViewById(R.id.movieRating);

        nameTxt.setText(mvieTitle);
        ratingTxt.setText(mvieRating);

        return view;
    }

    public int getCount(){
        return  super.getCount();
    }
}
