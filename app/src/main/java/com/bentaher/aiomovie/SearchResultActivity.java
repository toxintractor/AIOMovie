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
    String receivedText;
    ArrayList<Movie> movieAdapter = new ArrayList<>();
    private MovieAdapter adapter;

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        mQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        receivedText = intent.getStringExtra("JsonText");

        jsonParse();

        setAdapter();



    }

    public void jsonParse(){
        String url = "https://api.themoviedb.org/3/search/movie?api_key=1e9f1e07ae99796a8c5c9932ada044ab&query=" + receivedText;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray = new JSONArray();
                        String title, rating, genres, story;


                        try {
                            //jsonArray = response.getJSONObject("data");
                            jsonArray = response.getJSONArray("results");

                            for(int i=0; i < jsonArray.length(); i++){
                                title = jsonArray.getJSONObject(i).getString("title");
                                rating = jsonArray.getJSONObject(i).getString("vote_average");
                                genres = jsonArray.getJSONObject(i).getString("genre_ids");
                                story = jsonArray.getJSONObject(i).getString("overview");

                                Movie movies = new Movie(title, rating, genres, story, null);
                                movieAdapter.add(movies);
                            }
                            for (Movie a: movieAdapter) {
                                Log.i("Array", a.getTitle()+ " - " + a.getRating()+ " - " + a.getGenres()+ " - " + a.getStory());
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

    //Functie om de adapter aan te roepen en te laten zien.
    public  void setAdapter(){
        adapter = new MovieAdapter(this, movieAdapter);
        movieList = (ListView) findViewById(R.id.movieLst);
        movieList.setAdapter(adapter);

    }
}
