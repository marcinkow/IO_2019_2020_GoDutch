package com.GoDutch.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SettingsDialog extends AppCompatDialogFragment {
    EditText editTextName;
    EditText editTextAccNum;
    ExampleDialogListener listener;

    String myName;
    String myAccNum;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_settings, null);

        myName = MainActivity.getActivityInstance().getMyName();
        myAccNum = MainActivity.getActivityInstance().getMyAccNum();

        builder.setView(view).setTitle("Ustawienia").setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editTextName.getText().toString();
                String accNum = editTextAccNum.getText().toString();
                listener.apply(name, accNum);
            }
        });

        editTextName = view.findViewById(R.id.name);
        editTextAccNum = view.findViewById(R.id.accNumber);

        editTextName.setText(myName);
        editTextAccNum.setText(myAccNum);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface  ExampleDialogListener{
        void apply(String name, String accNum);
    }
}

































