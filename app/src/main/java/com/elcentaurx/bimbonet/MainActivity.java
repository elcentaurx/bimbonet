package com.elcentaurx.bimbonet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elcentaurx.bimbonet.data.preferences.Preferences;

public class MainActivity extends AppCompatActivity {
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = new Preferences();
        preferences.setDefaults("isDbinitialized","N", getApplicationContext());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}