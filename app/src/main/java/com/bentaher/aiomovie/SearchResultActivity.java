package com.bentaher.aiomovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    ListView movieList;
    ArrayList<Movie> movieArray = new ArrayList<>();
    private MovieAdapter adapter;

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        movieArray = (ArrayList<Movie>) getIntent().getSerializableExtra("JsonText");

        setAdapter();



    }


    //Functie om de adapter aan te roepen en te laten zien.
    public  void setAdapter(){
        adapter = new MovieAdapter(this, movieArray);
        movieList = (ListView) findViewById(R.id.movieLst);
        movieList.setAdapter(adapter);

    }

}
