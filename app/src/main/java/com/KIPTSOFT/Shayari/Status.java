package com.KIPTSOFT.Shayari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import static com.KIPTSOFT.Shayari.R.*;
import static com.KIPTSOFT.Shayari.R.id.*;

public class Status extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        findViewById(statusc1).setOnClickListener(this);
        findViewById(mahakals).setOnClickListener(this);
        findViewById(dardedills).setOnClickListener(this);
        findViewById(randoms).setOnClickListener(this);
        findViewById(lifes).setOnClickListener(this);
        findViewById(girlattitude).setOnClickListener(this);
        findViewById(funnystatus).setOnClickListener(this);
        findViewById(twolinestatus).setOnClickListener(this);

        //********************************************
        //  getActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        //**********************************************



    }





    @Override
    public void onClick(View v) {
        Intent myIn;
        int id = v.getId();
        if (id == statusc1) {
                myIn = new Intent(this, attitudestatus.class);
                startActivity(myIn);
        } else if (id == mahakals) {
                myIn = new Intent(this, MahakalS.class);
                startActivity(myIn);
        } else if (id == dardedills) {
                myIn = new Intent(this, DardEdilS.class);
                startActivity(myIn);
        } else if (id == randoms) {
                myIn = new Intent(this, RandomS.class);
                startActivity(myIn);
        } else if (id == lifes) {
                myIn = new Intent(this, LifeS.class);
                startActivity(myIn);
        } else if (id == girlattitude) {
                myIn = new Intent(this, GirlAttitude.class);
                startActivity(myIn);
        } else if (id == funnystatus) {
                myIn = new Intent(this, Funnystatus.class);
                startActivity(myIn);
        } else if (id == twolinestatus) {
                myIn = new Intent(this, twolinestatus.class);
                startActivity(myIn);
        } else {
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }
}