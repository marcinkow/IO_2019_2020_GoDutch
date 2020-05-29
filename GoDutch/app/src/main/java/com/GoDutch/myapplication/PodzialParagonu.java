package com.GoDutch.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PodzialParagonu extends AppCompatActivity{

    ArrayList<String> arrayList;
    ArrayList<String> napis;
    ArrayList<String> liczby_nasze;
    ListView lvSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = getIntent().getStringArrayListExtra("arrayList");
        napis = getIntent().getStringArrayListExtra("napis");
        liczby_nasze = getIntent().getStringArrayListExtra("liczby_nasze");

        setContentView(R.layout.activity_podzial_paragonu);
        lvSpinner = (ListView) findViewById(R.id.listview_spinner);

        SpinnerAdapter adapter = new SpinnerAdapter(napis, liczby_nasze, arrayList, this);
        lvSpinner.setAdapter(adapter);
    }
}
