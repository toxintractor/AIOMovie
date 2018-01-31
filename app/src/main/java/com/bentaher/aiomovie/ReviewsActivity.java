package com.bentaher.aiomovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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

public class ReviewsActivity extends AppCompatActivity {

    TextView reviewText;
    String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        reviewText = (TextView) findViewById(R.id.reviewTxt);

        movieId = (String) getIntent().getSerializableExtra("idString");

        reviewParse();

    }

    public void reviewParse(){

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        //String tmdbUrl = "https://api.themoviedb.org/3/movie/" + movieId + "/credits?api_key=1e9f1e07ae99796a8c5c9932ada044ab";
        String tmdbUrl = "https://api.themoviedb.org/3/movie/" + movieId + "/reviews?api_key=1e9f1e07ae99796a8c5c9932ada044ab";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, tmdbUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String author, review;
                        JSONArray jsonArray = new JSONArray();

                        try {
                            jsonArray = response.getJSONArray("results");

                            for(int i=0; i<jsonArray.length(); i++){
                                author = jsonArray.getJSONObject(i).getString("author");
                                review = jsonArray.getJSONObject(i).getString("content");
                                Log.i("ID:", review);
                                reviewText.append(author + ":\n" + review + "\n\n");

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
