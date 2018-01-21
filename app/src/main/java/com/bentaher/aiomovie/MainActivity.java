package com.bentaher.aiomovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
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

public class MainActivity extends AppCompatActivity {

    Spinner genre, jaar;
    Button buttonSearch;
    TextView txtJson;

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genre = (Spinner) findViewById(R.id.spinnerGenre);
        jaar = (Spinner) findViewById(R.id.spinnerJaar);
        buttonSearch = (Button) findViewById(R.id.btnSearch);
        txtJson = (TextView) findViewById(R.id.textJson);

        mQueue = Volley.newRequestQueue(this);

        buttonSearch.setOnClickListener(new MainActivity.Search());

    }

    public class Search implements View.OnClickListener {

        public Search(){
        }

        @Override
        public void onClick(View view) {
            jsonParse();

        }
    }

    public void jsonParse(){
        String url = "https://yts.am/api/v2/list_movies.json?query_term=batman%v%superman";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject jsonArray =  new JSONObject();
                        JSONArray jsonArray1 = new JSONArray();
                        try {
                            jsonArray = response.getJSONObject("data");
                            jsonArray1 = jsonArray.getJSONArray("movies");
                            txtJson.append(jsonArray1.toString());
                            Log.d("Array", jsonArray1.toString());
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
