package com.bentaher.aiomovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class MainActivity extends AppCompatActivity {

    EditText filmName, keyWordName;
    Button buttonSearch;
    TextView txtJson;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmName = (EditText) findViewById(R.id.movieNM);
        keyWordName = (EditText) findViewById(R.id.keyWrd);
        buttonSearch = (Button) findViewById(R.id.btnSearch);
        txtJson = (TextView) findViewById(R.id.textJson);


        buttonSearch.setOnClickListener(new MainActivity.Search());


    }

    public class Search implements View.OnClickListener {

        public Search(){
        }

        @Override
        public void onClick(View view) {
            String nameFilm = filmName.getText().toString();
            String nameKeyWork = keyWordName.getText().toString();
            if(nameFilm.matches("") && nameKeyWork.matches("")){
                Toast.makeText(MainActivity.this, "Insert moviename or keyword", Toast.LENGTH_SHORT).show();
            }
            else if(!nameKeyWork.matches("")){
                jsonKeyId();
            }
            else{
                jsonNameParse(null);
            }

        }
    }

    public void jsonNameParse(String id){

        String searchFilmname = filmName.getText().toString();

        RequestQueue mQueue;

        String url;

        if (id == null){
            url = "https://api.themoviedb.org/3/search/movie?api_key=1e9f1e07ae99796a8c5c9932ada044ab&query=" + searchFilmname;
        }
        else{
            url = "https://api.themoviedb.org/3/discover/movie?api_key=1e9f1e07ae99796a8c5c9932ada044ab&sort_by=popularity.desc&with_keywords=" + id;
        }

        //TextView txtvw = (TextView) findViewById(R.id.jsontext);
        //txtvw.setText(url);
        mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Movie> movieArray = new ArrayList<>();

                        JSONArray jsonArray = new JSONArray();
                        String title, id, rating, imageLink, genres, story, year;


                        try {
                            //jsonArray = response.getJSONObject("data");
                            jsonArray = response.getJSONArray("results");

                            for(int i=0; i < jsonArray.length(); i++){
                                title = jsonArray.getJSONObject(i).getString("title");
                                id = jsonArray.getJSONObject(i).getString("id");
                                rating = jsonArray.getJSONObject(i).getString("vote_average");
                                imageLink = "http://image.tmdb.org/t/p/w185/" + jsonArray.getJSONObject(i).getString("poster_path");
                                genres = jsonArray.getJSONObject(i).getString("genre_ids");
                                story = jsonArray.getJSONObject(i).getString("overview");
                                year = jsonArray.getJSONObject(i).getString("release_date");
                                Log.i("Array", imageLink);

                                Movie movies = new Movie(title, id, rating, imageLink, genres, story, year, null);
                                movieArray.add(movies);
                                txtJson.append(title + "\n");
                            }

                            Intent jumpPage = new Intent(MainActivity.this, SearchResultActivity.class);
                            jumpPage.putExtra("JsonText", movieArray);
                            startActivity(jumpPage);

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




    public void jsonKeyId(){

        String searchKeyword = keyWordName.getText().toString();

        RequestQueue mQueue;
        String keyUrl = "https://api.themoviedb.org/3/search/keyword?api_key=1e9f1e07ae99796a8c5c9932ada044ab&query=" + searchKeyword;

        mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, keyUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray = new JSONArray();
                        String keyId;

                        try {
                            jsonArray = response.getJSONArray("results");
                            keyId = jsonArray.getJSONObject(0).getString("id");
                            jsonNameParse(keyId);
                            Log.i("Array", keyId);

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
