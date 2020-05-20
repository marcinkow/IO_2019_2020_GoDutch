package com.GoDutch.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PodzialParagonu extends AppCompatActivity{

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podzial_paragonu);

        arrayList = getIntent().getStringArrayListExtra("arrayList");

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);



        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, arrayList);

        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);
        spinner8.setAdapter(adapter);


    }
}
