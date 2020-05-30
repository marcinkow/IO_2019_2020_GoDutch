package com.GoDutch.myapplication;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class PodzialParagonu extends AppCompatActivity{

    ArrayList<String> arrayList;
    ArrayList<String> napis;
    ArrayList<String> liczby_nasze;
    ListView lvSpinner;
    Button btnNext;
    HashMap<String, Double> sumy = new HashMap<String, Double>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = getIntent().getStringArrayListExtra("arrayList");
        napis = getIntent().getStringArrayListExtra("napis");
        liczby_nasze = getIntent().getStringArrayListExtra("liczby_nasze");
        setContentView(R.layout.activity_podzial_paragonu);
        btnNext = findViewById(R.id.button_podsumowanie);
        lvSpinner = (ListView) findViewById(R.id.listview_spinner);

        for(int i = 0; i < arrayList.size(); i++) {
            sumy.put(arrayList.get(i), 0.0);
        }

        final SpinnerAdapter adapter = new SpinnerAdapter(napis, liczby_nasze, arrayList, this);
        lvSpinner.setAdapter(adapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                View view;
                TextView tv;
                Spinner spn;
                String imie, cenaTxt;
                double cena;
                for (int i = 0; i < napis.size(); i++) {
                    view = lvSpinner.getAdapter().getView(i, null, null);
                    spn = view.findViewById(R.id.row_item_spinner);
                    imie = spn.getSelectedItem().toString();
                    tv = view.findViewById(R.id.row_item_textview2);
                    cenaTxt = tv.getText().toString();
                    cena = Double.parseDouble(cenaTxt);
                    sumy.put(imie, Double.sum(sumy.get(imie),cena));
                }
                openKoncowy();
            }
        });
    }

    private void openKoncowy() {
        Intent intent = new Intent(this, Koncowy.class);
        intent.putExtra("sumy", sumy);
        startActivity(intent);
    }
}
