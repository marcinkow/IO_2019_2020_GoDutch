package com.GoDutch.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class PodzialParagonu extends AppCompatActivity {

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podzial_paragonu);

        arrayList = getIntent().getStringArrayListExtra("arrayList");

    }
}
