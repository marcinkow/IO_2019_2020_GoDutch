package com.GoDutch.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    int[] prodIds = {R.id.textViewProd1, R.id.textViewProd2, R.id.textViewProd3, R.id.textViewProd4, R.id.textViewProd5, R.id.textViewProd6, R.id.textViewProd7, R.id.textViewProd8};
    int[] cenaIds = {R.id.textViewCena1, R.id.textViewCena2, R.id.textViewCena3, R.id.textViewCena4, R.id.textViewCena5, R.id.textViewCena6, R.id.textViewCena7, R.id.textViewCena8};
    TextView prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, cena1, cena2, cena3, cena4, cena5, cena6, cena7, cena8;
    AlertDialog dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8, dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8;
    EditText etp1, etp2, etp3, etp4, etp5, etp6, etp7, etp8, etc1, etc2, etc3, etc4, etc5, etc6, etc7, etc8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podzial_paragonu);

        arrayList = getIntent().getStringArrayListExtra("arrayList");
        napis = getIntent().getStringArrayListExtra("napis");
        liczby_nasze = getIntent().getStringArrayListExtra("liczby_nasze");

        for(int i=0; i<napis.size(); i++)
        {
            TextView prod = (TextView) findViewById(prodIds[i]);
            prod.setText(napis.get(i));
        }

        for(int i=0; i<liczby_nasze.size(); i++)
        {
            TextView cena = (TextView) findViewById(cenaIds[i]);
            cena.setText(liczby_nasze.get(i));
        }

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

        prod1 = (TextView) findViewById(R.id.textViewProd1);
        dp1 = new AlertDialog.Builder(this).create();
        etp1 = new EditText(this);
        dp1.setTitle("Dopasuj produkt");
        dp1.setView(etp1);
        dp1.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod1.setText(etp1.getText());
            }
        });
        prod1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp1.setText(prod1.getText());
                dp1.show();
                return false;
            }
        });

        prod2 = (TextView) findViewById(R.id.textViewProd2);
        dp2 = new AlertDialog.Builder(this).create();
        etp2 = new EditText(this);
        dp2.setTitle("Dopasuj produkt");
        dp2.setView(etp2);
        dp2.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod2.setText(etp2.getText());
            }
        });
        prod2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp2.setText(prod2.getText());
                dp2.show();
                return false;
            }
        });

        prod3 = (TextView) findViewById(R.id.textViewProd3);
        dp3 = new AlertDialog.Builder(this).create();
        etp3 = new EditText(this);
        dp3.setTitle("Dopasuj produkt");
        dp3.setView(etp3);
        dp3.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod3.setText(etp3.getText());
            }
        });
        prod3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp3.setText(prod3.getText());
                dp3.show();
                return false;
            }
        });

        prod4 = (TextView) findViewById(R.id.textViewProd4);
        dp4 = new AlertDialog.Builder(this).create();
        etp4 = new EditText(this);
        dp4.setTitle("Dopasuj produkt");
        dp4.setView(etp4);
        dp4.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod4.setText(etp4.getText());
            }
        });
        prod4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp4.setText(prod4.getText());
                dp4.show();
                return false;
            }
        });

        prod5 = (TextView) findViewById(R.id.textViewProd5);
        dp5 = new AlertDialog.Builder(this).create();
        etp5 = new EditText(this);
        dp5.setTitle("Dopasuj produkt");
        dp5.setView(etp5);
        dp5.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod5.setText(etp5.getText());
            }
        });
        prod5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp5.setText(prod5.getText());
                dp5.show();
                return false;
            }
        });

        prod6 = (TextView) findViewById(R.id.textViewProd6);
        dp6 = new AlertDialog.Builder(this).create();
        etp6 = new EditText(this);
        dp6.setTitle("Dopasuj produkt");
        dp6.setView(etp6);
        dp6.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod6.setText(etp6.getText());
            }
        });
        prod6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp6.setText(prod6.getText());
                dp6.show();
                return false;
            }
        });

        prod7 = (TextView) findViewById(R.id.textViewProd7);
        dp7 = new AlertDialog.Builder(this).create();
        etp7 = new EditText(this);
        dp7.setTitle("Dopasuj produkt");
        dp7.setView(etp7);
        dp7.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod7.setText(etp7.getText());
            }
        });
        prod7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp7.setText(prod7.getText());
                dp7.show();
                return false;
            }
        });

        prod8 = (TextView) findViewById(R.id.textViewProd8);
        dp8 = new AlertDialog.Builder(this).create();
        etp8 = new EditText(this);
        dp8.setTitle("Dopasuj produkt");
        dp8.setView(etp8);
        dp8.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prod8.setText(etp8.getText());
            }
        });
        prod8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etp8.setText(prod8.getText());
                dp8.show();
                return false;
            }
        });

        cena1 = (TextView) findViewById(R.id.textViewCena1);
        dc1 = new AlertDialog.Builder(this).create();
        etc1 = new EditText(this);
        dc1.setTitle("Dopasuj cenę");
        dc1.setView(etc1);
        dc1.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena1.setText(etc1.getText());
            }
        });
        cena1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc1.setText(cena1.getText());
                dc1.show();
                return false;
            }
        });

        cena2 = (TextView) findViewById(R.id.textViewCena2);
        dc2 = new AlertDialog.Builder(this).create();
        etc2 = new EditText(this);
        dc2.setTitle("Dopasuj cenę");
        dc2.setView(etc2);
        dc2.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena2.setText(etc2.getText());
            }
        });
        cena2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc2.setText(cena2.getText());
                dc2.show();
                return false;
            }
        });

        cena3 = (TextView) findViewById(R.id.textViewCena3);
        dc3 = new AlertDialog.Builder(this).create();
        etc3 = new EditText(this);
        dc3.setTitle("Dopasuj cenę");
        dc3.setView(etc3);
        dc3.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena3.setText(etc3.getText());
            }
        });
        cena3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc3.setText(cena3.getText());
                dc3.show();
                return false;
            }
        });

        cena4 = (TextView) findViewById(R.id.textViewCena4);
        dc4 = new AlertDialog.Builder(this).create();
        etc4 = new EditText(this);
        dc4.setTitle("Dopasuj cenę");
        dc4.setView(etc4);
        dc4.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena4.setText(etc4.getText());
            }
        });
        cena4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc4.setText(cena4.getText());
                dc4.show();
                return false;
            }
        });

        cena5 = (TextView) findViewById(R.id.textViewCena5);
        dc5 = new AlertDialog.Builder(this).create();
        etc5 = new EditText(this);
        dc5.setTitle("Dopasuj cenę");
        dc5.setView(etc5);
        dc5.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena5.setText(etc5.getText());
            }
        });
        cena5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc5.setText(cena5.getText());
                dc5.show();
                return false;
            }
        });

        cena6 = (TextView) findViewById(R.id.textViewCena6);
        dc6 = new AlertDialog.Builder(this).create();
        etc6 = new EditText(this);
        dc6.setTitle("Dopasuj cenę");
        dc6.setView(etc6);
        dc6.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena6.setText(etc6.getText());
            }
        });
        cena6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc6.setText(cena6.getText());
                dc6.show();
                return false;
            }
        });

        cena7 = (TextView) findViewById(R.id.textViewCena7);
        dc7 = new AlertDialog.Builder(this).create();
        etc7 = new EditText(this);
        dc7.setTitle("Dopasuj cenę");
        dc7.setView(etc7);
        dc7.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena7.setText(etc7.getText());
            }
        });
        cena7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc7.setText(cena7.getText());
                dc7.show();
                return false;
            }
        });

        cena8 = (TextView) findViewById(R.id.textViewCena8);
        dc8 = new AlertDialog.Builder(this).create();
        etc8 = new EditText(this);
        dc8.setTitle("Dopasuj cenę");
        dc8.setView(etc8);
        dc8.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cena8.setText(etc8.getText());
            }
        });
        cena8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etc8.setText(cena8.getText());
                dc8.show();
                return false;
            }
        });
    }
}
