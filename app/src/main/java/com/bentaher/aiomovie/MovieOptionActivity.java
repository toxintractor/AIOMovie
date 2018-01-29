package com.bentaher.aiomovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    ArrayList<Torrent> torrentArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_option);

        txtName = (TextView) findViewById(R.id.movieTxt);
        txtStory = (TextView) findViewById(R.id.storyTxt);

        movie = (Movie) getIntent().getSerializableExtra("movieData");

        txtName.setText(movie.getTitle() + " - " + movie.getYear().substring(0, movie.getYear().indexOf("-")));
        txtStory.setText(movie.getStory());





    }

    public class GetTorrents implements View.OnClickListener {

        public GetTorrents(){
        }

        @Override
        public void onClick(View view) {
            jsonIMDBParse();

        }
    }

    public class GetBol implements View.OnClickListener {

        public GetBol(){
        }

        @Override
        public void onClick(View view) {
            bolParse(movie.getTitle());

        }
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
                        String title, year, resolution, size, sitelink, magnetlink;

                        try {
                            jsonObject = response.getJSONObject("data");
                            jsonArray = jsonObject.getJSONArray("movies");

                            for(int i=0; i < jsonArray.length(); i++){
                                title = jsonArray.getJSONObject(i).getString("title");
                                year = jsonArray.getJSONObject(i).getString("year");

                                Log.i("torrent title:",year + " - " + searchYear);

                                JSONArray torrentJsonArray = new JSONArray();


                                torrentJsonArray = jsonArray.getJSONObject(i).getJSONArray("torrents");
                                for(int k=0; k < torrentJsonArray.length(); k++){
                                    resolution = torrentJsonArray.getJSONObject(k).getString("quality");
                                    size = torrentJsonArray.getJSONObject(k).getString("size");
                                    sitelink = torrentJsonArray.getJSONObject(k).getString("url");
                                    magnetlink = torrentJsonArray.getJSONObject(k).getString("url");

                                    Log.i("torrent title:", size + " - "+ resolution + " - "+ torrentJsonArray.length());

                                    Torrent torrent = new Torrent(title, resolution, size, sitelink, magnetlink);
                                    torrentArray.add(torrent);
                                }
                            }

                            goToTorrent(torrentArray);

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

    public void bolParse(String movieTitle){

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        String bolUrl = "https://api.bol.com/catalog/v4/search/?q=" + movieTitle + "&offset=0&limit=20&dataoutput=products,categories&apikey=C46AD0F51E7D43E2B1EE160AEE827820&format=json";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, bolUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Bol> bolArray = new ArrayList<>();

                        JSONArray jsonArray, offerArray, linkArray = new JSONArray();
                        JSONObject offerData = new JSONObject();

                        String title, type, price, shopLink;

                        try {
                            jsonArray = response.getJSONArray("products");


                            for(int i=0; i < jsonArray.length(); i++){

                                if(jsonArray.getJSONObject(i).getString("gpc").equals("dvdmo")){

                                    title = jsonArray.getJSONObject(i).getString("title");
                                    type = jsonArray.getJSONObject(i).getString("summary");

                                    offerData = jsonArray.getJSONObject(i).getJSONObject("offerData");
                                    offerArray = offerData.getJSONArray("offers");
                                    price = offerArray.getJSONObject(0).getString("price");

                                    linkArray = jsonArray.getJSONObject(i).getJSONArray("urls");
                                    shopLink = linkArray.getJSONObject(0).getString("value");

                                    Log.i("check true:", shopLink);

                                    Bol bol = new Bol(title, type, price, shopLink);
                                    bolArray.add(bol);


                                }

                            }

                            goToBol(bolArray);

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

    public void goToTorrent(ArrayList<Torrent> torrentArray){
        Intent jumpPage = new Intent(MovieOptionActivity.this, TorrentResultActivity.class);
        jumpPage.putExtra("dataArray", torrentArray);
        startActivity(jumpPage);
    }

    public void goToBol(ArrayList<Bol> bolArray){
        Intent jumpPage = new Intent(MovieOptionActivity.this, BolResultActivity.class);
        jumpPage.putExtra("dataArray", bolArray);
        startActivity(jumpPage);
    }

}
