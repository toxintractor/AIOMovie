package com.bentaher.aiomovie;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button reviewButton, compared;
    Movie movie;
    ImageView torrentImage, bolImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_option);

        txtName = (TextView) findViewById(R.id.movieTxt);
        txtStory = (TextView) findViewById(R.id.storyTxt);
        reviewButton = (Button) findViewById(R.id.reviewBtn);
        compared = (Button) findViewById(R.id.relatedBtn);
        torrentImage = (ImageView) findViewById(R.id.torrentBtn);
        bolImage = (ImageView) findViewById(R.id.buyBtn);

        movie = (Movie) getIntent().getSerializableExtra("movieData");

        txtName.setText(movie.getTitle() + " - " + movie.getYear().substring(0, movie.getYear().indexOf("-")));
        txtStory.setText(movie.getStory());
        actorParse();

        torrentImage.setOnClickListener(new MovieOptionActivity.GetTorrents());
        bolImage.setOnClickListener(new MovieOptionActivity.GetBol());
        reviewButton.setOnClickListener(new MovieOptionActivity.GetReviews());
        compared.setOnClickListener(new MovieOptionActivity.GetRelated());


    }

    //Onclick listener om de reviews op te vragen.
    public class GetReviews implements View.OnClickListener {

        public GetReviews(){
        }

        @Override
        public void onClick(View view) {
            Intent jumpPage = new Intent(MovieOptionActivity.this, ReviewsActivity.class);
            jumpPage.putExtra("idString", movie.getId());
            startActivity(jumpPage);

        }
    }

    //Onlick listener om de gerelateerde films op te vragen.
    public class GetRelated implements View.OnClickListener {

        public GetRelated(){
        }

        @Override
        public void onClick(View view) {
            relatedParse();
        }
    }

    //Onclick listener om de torrents op te vragen.
    public class GetTorrents implements View.OnClickListener {

        public GetTorrents(){
        }

        @Override
        public void onClick(View view) {
            jsonIMDBParse();

        }
    }

    //Onlick listener om de Bol.com producten op te vragen.
    public class GetBol implements View.OnClickListener {

        public GetBol(){
        }

        @Override
        public void onClick(View view) {
            bolParse();

        }
    }


    //Functie om de IMDB code te verkrijgen
    public void jsonIMDBParse(){

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

    //Functie om de torrent gegevens te parsen uit de Yify API.
    public void jsonYifyParse(String IMDBId){

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        //Request aan de hand van de IMDB code
        String yifyUrl = "https://yts.am/api/v2/list_movies.json?query_term=" + IMDBId;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, yifyUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Torrent> torrentArray = new ArrayList<>();

                        JSONObject jsonObject = new JSONObject();
                        JSONArray jsonArray = new JSONArray();
                        String title, year, resolution, size, sitelink, imageLink, magnetlink;

                        //Parsen van de Yify API Json
                        try {
                            jsonObject = response.getJSONObject("data");

                            if (!jsonObject.has("movies")){
                                Toast.makeText(MovieOptionActivity.this, "No Torrents found", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                jsonArray = jsonObject.getJSONArray("movies");

                                for(int i=0; i < jsonArray.length(); i++){
                                    title = jsonArray.getJSONObject(i).getString("title");
                                    year = jsonArray.getJSONObject(i).getString("year");
                                    imageLink = jsonArray.getJSONObject(i).getString("medium_cover_image");

                                    JSONArray torrentJsonArray = new JSONArray();


                                    torrentJsonArray = jsonArray.getJSONObject(i).getJSONArray("torrents");
                                    for(int k=0; k < torrentJsonArray.length(); k++){
                                        resolution = torrentJsonArray.getJSONObject(k).getString("quality");
                                        size = torrentJsonArray.getJSONObject(k).getString("size");
                                        sitelink = torrentJsonArray.getJSONObject(k).getString("url");
                                        magnetlink = torrentJsonArray.getJSONObject(k).getString("url");

                                        Log.i("torrent title:", size + " - "+ resolution + " - "+ torrentJsonArray.length());

                                        Torrent torrent = new Torrent(title, resolution, size, sitelink, imageLink, magnetlink);
                                        torrentArray.add(torrent);
                                    }
                                }
                                goToTorrent(torrentArray);
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

    //Parsen van de Bol.com API.
    public void bolParse(){

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        String bolUrl = "https://api.bol.com/catalog/v4/search/?q=" + movie.getTitle() + "&offset=0&limit=40&dataoutput=products,categories&apikey=C46AD0F51E7D43E2B1EE160AEE827820&format=json";

        Log.i("check true:", movie.getTitle());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, bolUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Bol> bolArray = new ArrayList<>();

                        JSONArray jsonArray, offerArray, linkArray = new JSONArray();
                        JSONObject offerData = new JSONObject();

                        String title, type, price, shopLink, imageLink;

                        //Parsen van de Json response.
                        try {
                            if(!response.has("products")){
                                Toast.makeText(MovieOptionActivity.this, "No products found", Toast.LENGTH_SHORT).show();
                            }
                            else{
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
                                        if(jsonArray.getJSONObject(i).has("images")){
                                            linkArray = jsonArray.getJSONObject(i).getJSONArray("images");
                                            if(linkArray.getJSONObject(3).has("url")){
                                                imageLink = linkArray.getJSONObject(3).getString("url");
                                            }
                                            else{
                                                imageLink = "https://s.s-bol.com/nl/static/images/main/noimage_124x100default.gif";
                                            }

                                        }
                                        else{
                                            imageLink = "https://s.s-bol.com/nl/static/images/main/noimage_124x100default.gif";
                                        }
                                        Bol bol = new Bol(title, type, price, shopLink, imageLink);
                                        bolArray.add(bol);
                                    }
                                }
                                Log.i("lenth", String.valueOf(bolArray.size()));
                                if(bolArray.size() == 0){
                                    Toast.makeText(MovieOptionActivity.this, "No products found", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    goToBol(bolArray);
                                }

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

    //Functie om de acteurs te parsen.
    public void actorParse(){

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        String tmdbUrl = "https://api.themoviedb.org/3/movie/" + movie.getId() + "/credits?api_key=1e9f1e07ae99796a8c5c9932ada044ab";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, tmdbUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        txtStory.append("\n\n");
                        txtStory.append(Html.fromHtml( "<b>Actors:</b>" ));
                        txtStory.append("\n");

                        String actor;
                        JSONArray jsonArray = new JSONArray();

                        //JSON wordt geparst gelijk in de textview gestopt
                        try {
                            jsonArray = response.getJSONArray("cast");

                            for(int i=0; i<jsonArray.length(); i++){
                                actor = jsonArray.getJSONObject(i).getString("name");
                                Log.i("ID:", actor);
                                txtStory.append(actor);
                                if(i == 5){
                                    break;
                                }
                                txtStory.append(", ");
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

    //Gerelateerde films ophalen.
    public void relatedParse(){

        RequestQueue mQueue;
        String url = "https://api.themoviedb.org/3/movie/" + movie.getId() + "/recommendations?api_key=1e9f1e07ae99796a8c5c9932ada044ab";
        mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Movie> movieArray = new ArrayList<>();

                        JSONArray jsonArray = new JSONArray();
                        String title, id, rating, imageLink, genres, story, year;

                        //Gson wordt geparst
                        try {
                            jsonArray = response.getJSONArray("results");

                            if (jsonArray.length() == 0) {
                                Toast.makeText(MovieOptionActivity.this, "No related movies found", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                for(int i=0; i < jsonArray.length(); i++){
                                    title = jsonArray.getJSONObject(i).getString("title");
                                    id = jsonArray.getJSONObject(i).getString("id");
                                    rating = jsonArray.getJSONObject(i).getString("vote_average");
                                    imageLink = "http://image.tmdb.org/t/p/w185/" + jsonArray.getJSONObject(i).getString("poster_path");
                                    genres = jsonArray.getJSONObject(i).getString("genre_ids");
                                    story = jsonArray.getJSONObject(i).getString("overview");
                                    if(jsonArray.getJSONObject(i).getString("release_date").matches("")){
                                        year = "unknown-unknown";
                                    }else{
                                        year = jsonArray.getJSONObject(i).getString("release_date");
                                    }
                                    Log.i("Array", imageLink);

                                    Movie movies = new Movie(title, id, rating, imageLink, genres, story, year, null);
                                    movieArray.add(movies);
                                }

                                Intent jumpPage = new Intent(MovieOptionActivity.this, SearchResultActivity.class);
                                jumpPage.putExtra("JsonText", movieArray);
                                startActivity(jumpPage);
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

    //Functies om naar andere pagina te gaan met data van de Torrents.
    public void goToTorrent(ArrayList<Torrent> torrentArray){
        Intent jumpPage = new Intent(MovieOptionActivity.this, TorrentResultActivity.class);
        jumpPage.putExtra("dataArray", torrentArray);
        startActivity(jumpPage);
    }

    //Functies om naar andere pagina te gaan met data van de Bol.com producten.
    public void goToBol(ArrayList<Bol> bolArray){
        Intent jumpPage = new Intent(MovieOptionActivity.this, BolResultActivity.class);
        jumpPage.putExtra("dataArray", bolArray);
        startActivity(jumpPage);
    }

}
