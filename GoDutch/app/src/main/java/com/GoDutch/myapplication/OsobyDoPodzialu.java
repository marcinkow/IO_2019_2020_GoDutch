package com.GoDutch.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class OsobyDoPodzialu extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    EditText editText;
    String tempCH;
    Button btnAdd, btnForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osoby_do_podzialu);

        editText = findViewById(R.id.edittxt);
        btnAdd = findViewById(R.id.additems);
        btnForward = findViewById(R.id.passForward);
        listView = findViewById(R.id.listView);
        String[] osoby = {};
        arrayList = new ArrayList<>(Arrays.asList(osoby));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCH = editText.getText().toString();
                if(TextUtils.isEmpty(tempCH))
                {

                    Toast.makeText(getBaseContext(), "Podaj imię!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String additem = editText.getText().toString();
                    arrayList.add(additem);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    Toast.makeText(getBaseContext(), "Dodano osobę", Toast.LENGTH_SHORT).show();
                }

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.remove(arrayList.get(position));
                adapter.notifyDataSetChanged();

                Toast.makeText(getBaseContext(), "Osoba usunięta!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OsobyDoPodzialu.this, PodzialParagonu.class);
                intent.putExtra("arrayList", arrayList);
                startActivity(intent);
            }
        });

    }
}


































