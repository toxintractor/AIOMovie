package com.bentaher.aiomovie;

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

public class MovieOptionActivity extends AppCompatActivity {

    TextView txtName, txtStory;
    Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_option);

        txtName = (TextView) findViewById(R.id.movieTxt);
        txtStory = (TextView) findViewById(R.id.storyTxt);

        movie = (Movie) getIntent().getSerializableExtra("movieData");

        txtName.setText(movie.getTitle() + " - " + movie.getYear().substring(0, movie.getYear().indexOf("-")));
        txtStory.setText(movie.getStory());

        jsonIMDBParse();


    }

    public void jsonIMDBParse(){

        String searchFilmname = movie.getTitle();
        String searchYear = movie.getYear().substring(0, movie.getYear().indexOf("-"));

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        String tmdbUrl = "https://api.themoviedb.org/3/movie/"+ movie.getId() + "/external_ids?api_key=1e9f1e07ae99796a8c5c9932ada044ab";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, tmdbUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String imdbId = "";

                        try {
                            imdbId = response.getString("imdb_id");
                            Log.i("imdb code", imdbId);

                            jsonYifyParse(imdbId);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }

    public void jsonYifyParse(String IMDBId){

        String searchFilemname = movie.getTitle();
        final String searchYear = movie.getYear().substring(0, movie.getYear().indexOf("-"));

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        String yifyUrl = "https://yts.am/api/v2/list_movies.json?query_term=" + IMDBId;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, yifyUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject jsonObject = new JSONObject();
                        JSONArray jsonArray = new JSONArray();
                        String title, year;

                        try {
                            jsonObject = response.getJSONObject("data");
                            jsonArray = jsonObject.getJSONArray("movies");

                            for(int i=0; i < jsonArray.length(); i++){
                                title = jsonArray.getJSONObject(i).getString("title");
                                year = jsonArray.getJSONObject(i).getString("year");

                                Log.i("torrent title:", year + " - "+ searchYear);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
}
