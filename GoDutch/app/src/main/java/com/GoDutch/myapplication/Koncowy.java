package com.GoDutch.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.HashMap;

public class Koncowy extends AppCompatActivity {

    HashMap<String, Double> sumy;
    ListView lvKoncowy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koncowy);
        sumy = (HashMap<String, Double>) getIntent().getSerializableExtra("sumy");
        lvKoncowy = (ListView) findViewById(R.id.listview_koncowy);

        KoncowyAdapter adapter = new KoncowyAdapter(sumy, this);
        lvKoncowy.setAdapter(adapter);

    }
}
