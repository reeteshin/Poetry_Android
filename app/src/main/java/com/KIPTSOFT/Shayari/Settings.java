package com.KIPTSOFT.Shayari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    Button mode_switch;
    Boolean isNightModeOn;

    SharedPreferences.Editor sharedPrefsEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
         findViewById(R.id.opensource).setOnClickListener(this);;
         findViewById(R.id.darkmode).setOnClickListener(this);;
        findViewById(R.id.path).setOnClickListener(this);;
//**************************************************************************************
        //********************************************
        //  getActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        //**********************************************

// ****************************************************************************


    }
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(), Settings.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {

        Intent myIn;
        int id = v.getId();
        if (id == R.id.opensource) {
                myIn = new Intent(this, opensourcde.class);
                startActivity(myIn);
        } else if (id == R.id.darkmode) {
                Toast.makeText(this, "Home Screen", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.path) {
                Toast.makeText(this, "Folder:- MotivationQuotes In File Manager", Toast.LENGTH_SHORT).show();
        }
    }
}