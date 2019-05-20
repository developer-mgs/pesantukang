package com.mgs.pesantukang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mgs.pesantukang.R;

public class PemesananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        getSupportActionBar().setTitle("Form Pemesanan");
    }
}
