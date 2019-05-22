package com.mgs.pesantukang;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import Kelas.SharedVariabel;

public class EditProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Log.d("Nomor ", SharedVariabel.ID_USER_PERMANEN);
    }
}
