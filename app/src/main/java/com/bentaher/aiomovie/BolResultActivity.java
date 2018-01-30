package com.bentaher.aiomovie;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BolResultActivity extends AppCompatActivity {

    ListView bolList;
    ArrayList<Bol> bolArray = new ArrayList<>();
    private BolAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bol_result);

        bolArray = (ArrayList<Bol>) getIntent().getSerializableExtra("dataArray");

        setAdapter();



    }

    //Functie om de adapter aan te roepen en te laten zien.
    public  void setAdapter(){
        adapter = new BolAdapter(this, bolArray);
        bolList = (ListView) findViewById(R.id.bolLst);
        bolList.setAdapter(adapter);

    }


}
