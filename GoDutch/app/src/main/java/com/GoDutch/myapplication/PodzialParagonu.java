package com.GoDutch.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        BasicSpinnerAdapter adapter = new BasicSpinnerAdapter(napis, liczby_nasze, arrayList, this);
        lvSpinner.setAdapter(adapter);

    }
}
