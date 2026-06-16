package com.KIPTSOFT.Shayari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import static com.KIPTSOFT.Shayari.R.*;
import static com.KIPTSOFT.Shayari.R.id.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AdView mAdView ;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    Button mode_switch;
    Boolean isNightModeOn;
    SharedPreferences.Editor sharedPrefsEdit;
    private FirebaseAnalytics mFirebaseAnalytics;
    ToggleButton b1;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        drawerLayout = (DrawerLayout) findViewById(drawer);
        /*Button b = findViewById(R.id.buttondark);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });*/

        //***************************************************


//*****************************************************************

        b1 = (ToggleButton) findViewById(owlimage);
        sharedPreferences = getSharedPreferences("sharepreference", 0);
        sharedPrefsEdit = sharedPreferences.edit();
        isNightModeOn = sharedPreferences.getBoolean("NightMode", false);




      /*  b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (true){
                   // sharedPrefsEdit.putBoolean("NightMode",false);
                    getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                   // sharedPrefsEdit.apply();
                }
                else
                {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

            }
        });*/

        // @SuppressLint("UseSwitchCompatOrMaterialCode") Switch s = (Switch)findViewById(R.id.s);
        b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        //********************************************************************************************
        //need improvement leter
        permission(this);
// ***************************

        findViewById(love).setOnClickListener(this);
        findViewById(beuty).setOnClickListener(this);
        findViewById(bewafa).setOnClickListener(this);
        findViewById(attitude).setOnClickListener(this);
        findViewById(romantic).setOnClickListener(this);
        findViewById(life).setOnClickListener(this);
        findViewById(yaad).setOnClickListener(this);
        findViewById(dosti).setOnClickListener(this);
        findViewById(birthday).setOnClickListener(this);
        findViewById(dard).setOnClickListener(this);
        findViewById(festival).setOnClickListener(this);
        findViewById(desh).setOnClickListener(this);
        findViewById(funny).setOnClickListener(this);
        findViewById(night).setOnClickListener(this);
        findViewById(morning).setOnClickListener(this);
        findViewById(motivation).setOnClickListener(this);
        findViewById(valentin).setOnClickListener(this);
        findViewById(tru).setOnClickListener(this);
        findViewById(sorry).setOnClickListener(this);
        findViewById(sharabi).setOnClickListener(this);
        findViewById(LinerStatus).setOnClickListener(this);

        Button drawerLayoutm = findViewById(imagetool);
        drawerLayoutm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }


        });
        NavigationView navigationView = findViewById(nev_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == rateus) {
                        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=com.KIPTSOFT.Shayari");
                        startActivity(new Intent(Intent.ACTION_VIEW, uri1));
                } else if (id == about) {
                        Intent myIn1 = new Intent(MainActivity.this, Page_about.class);
                        startActivity(myIn1);
                } else if (id == setting) {
                        Intent myIn2 = new Intent(MainActivity.this, Settings.class);
                        startActivity(myIn2);
                } else if (id == privacy) {
                        Uri uri = Uri.parse("https://apiyan.blogspot.com/2021/02/ShayariAndStatusPrivacyPolicy.html");
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else if (id == update) {
                        Uri uri2 = Uri.parse("https://play.google.com/store/apps/details?id=com.KIPTSOFT.Shayari");
                        startActivity(new Intent(Intent.ACTION_VIEW, uri2));
                } else if (id == sendshayari) {
                        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "kiptsoft.appfeedback@gmail.com"));
                        startActivity(intent);
                }
                return false;
            }
        });


    }

    //********************************************************
    //********************************************************
    //request Permission
    private void permission(Context meena) {
        if (ActivityCompat.checkSelfPermission(meena, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) meena, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
    //*********************************************************************************

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent myIn = new Intent(this, QuoteListActivity.class);
        String category = "success";
        int id = v.getId();
        
        if (id == love) category = "love";
        else if (id == beuty) category = "beauty";
        else if (id == bewafa) category = "bewafa";
        else if (id == attitude) category = "attitude";
        else if (id == romantic) category = "romantic";
        else if (id == life) category = "life";
        else if (id == yaad) category = "yaad";
        else if (id == dosti) category = "dosti";
        else if (id == birthday) category = "birthday";
        else if (id == dard) category = "dard";
        else if (id == festival) category = "festival";
        else if (id == desh) category = "desh";
        else if (id == funny) category = "funny";
        else if (id == night) category = "night";
        else if (id == morning) category = "morning";
        else if (id == motivation) category = "motivation";
        else if (id == valentin) category = "valentine";
        else if (id == tru) category = "true";
        else if (id == sorry) category = "sorry";
        else if (id == sharabi) category = "sharabi";
        else if (id == LinerStatus) category = "status";

        myIn.putExtra("CATEGORY", category);
        startActivity(myIn);

    }


}
