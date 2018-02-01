package com.bentaher.aiomovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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

    //De reviews parsen in de view zetten.
    public void reviewParse(){

        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        String tmdbUrl = "https://api.themoviedb.org/3/movie/" + movieId + "/reviews?api_key=1e9f1e07ae99796a8c5c9932ada044ab";
        Log.i("movie ID:", movieId);

        //Parsen van de Json aan de hand van een request met de MovieID.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, tmdbUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String author, review;
                        JSONArray jsonArray = new JSONArray();

                        try {
                            jsonArray = response.getJSONArray("results");

                            if(jsonArray.length() == 0){
                                Toast.makeText(ReviewsActivity.this, "No reviews found", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            for(int i=0; i<jsonArray.length(); i++){
                                author = jsonArray.getJSONObject(i).getString("author");
                                review = jsonArray.getJSONObject(i).getString("content");
                                //De reviews in de view zetten.
                                reviewText.append(Html.fromHtml( "<b>"+ author +"</b>" ));
                                reviewText.append(":\n" + review + "\n\n");

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
