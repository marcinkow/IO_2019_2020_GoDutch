package com.GoDutch.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Hashtable;

public class SpinnerAdapter extends BaseAdapter {

    private ArrayList<String> mSpinnerItems;
    private ArrayList<String> mData;
    private ArrayList<String> mData2;
    private Context mContext;
    Hashtable<Integer, Integer> spinnerSelected = new Hashtable<Integer, Integer>();

    public SpinnerAdapter(ArrayList<String> data, ArrayList<String> data2, ArrayList<String> spinnerItems, Context context) {
        mData = data;
        mData2 = data2;
        mContext = context;
        mSpinnerItems = spinnerItems;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int row_position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_spinner, null);
        }

        final TextView textView = (TextView) view.findViewById(R.id.row_item_textview);
        final TextView textView1 = (TextView) view.findViewById(R.id.row_item_textview2);
        final Spinner spinner = (Spinner) view.findViewById(R.id.row_item_spinner);
        textView.setText(mData.get(row_position));
        textView1.setText(mData2.get(row_position));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mSpinnerItems);
        spinner.setAdapter(adapter);
        if (spinnerSelected.containsKey(row_position)) {
            spinner.setSelection(spinnerSelected.get(row_position));
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSelected.put(row_position, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                AlertDialog alertDialog1 = new AlertDialog.Builder(mContext).create();
                final EditText editText = new EditText(mContext);
                final EditText editText1 = new EditText(mContext);
                alertDialog.setTitle("Dopasuj produkt");
                alertDialog1.setTitle("Dopasuj cenę");
                alertDialog.setView(editText);
                alertDialog1.setView(editText1);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mData.set(row_position, editText.getText().toString());
                        textView.setText(editText.getText());
                    }
                });
                alertDialog1.setButton(DialogInterface.BUTTON_POSITIVE, "ZAPISZ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mData2.set(row_position, editText1.getText().toString());
                        textView1.setText(editText1.getText());
                    }
                });
                editText.setText(textView.getText());
                editText1.setText(textView1.getText());
                alertDialog1.show();
                alertDialog.show();
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        return view;
    }
}
