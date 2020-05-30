package com.GoDutch.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KoncowyAdapter extends BaseAdapter {

    private HashMap<String, Double> mSumy;
    private Context mContext;

    public KoncowyAdapter(HashMap<String, Double> sumy, Context context) {
        mContext = context;
        mSumy = sumy;
    }

    @Override
    public int getCount() {
        return mSumy.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int row_position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_koncowy, null);
        }

        final TextView textView = (TextView) view.findViewById(R.id.row_item_podsumowanie_osoba);
        final TextView textView1 = (TextView) view.findViewById(R.id.row_item_podsumowanie_suma);
        final Button button = (Button) view.findViewById(R.id.button_podsumowanie);

        Iterator it = mSumy.entrySet().iterator();
        Map.Entry pair = (Map.Entry)it.next();
        textView.setText(pair.getKey().toString());
        textView1.setText(pair.getValue().toString());
        it.remove();


        return view;
    }

}

