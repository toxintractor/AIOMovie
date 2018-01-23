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

    EditText filmName;
    Spinner genre, jaar;
    Button buttonSearch;
    TextView txtJson;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmName = (EditText) findViewById(R.id.taakNaam);
        genre = (Spinner) findViewById(R.id.spinnerGenre);
        jaar = (Spinner) findViewById(R.id.spinnerJaar);
        buttonSearch = (Button) findViewById(R.id.btnSearch);
        txtJson = (TextView) findViewById(R.id.textJson);


        buttonSearch.setOnClickListener(new MainActivity.Search());

    }

    public class Search implements View.OnClickListener {

        public Search(){
        }

        @Override
        public void onClick(View view) {
            String searchFilmname = filmName.getText().toString();
            Intent jumpPage = new Intent(MainActivity.this, SearchResultActivity.class);
            jumpPage.putExtra("JsonText", searchFilmname);
            startActivity(jumpPage);

        }
    }



}
