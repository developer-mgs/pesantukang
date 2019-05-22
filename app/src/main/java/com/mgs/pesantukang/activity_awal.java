package com.mgs.pesantukang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_awal extends AppCompatActivity {
Button btnLog,btnSignUp;
SessionSharePreference session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        session = new SessionSharePreference(activity_awal.this.getApplicationContext());

        String nama = session.getNama();
        if(nama != null){
            Intent intent = new Intent(activity_awal.this, BerandaActivity.class);
            startActivity(intent);
        }else{
            //Toast.makeText(getApplication(),"")
        }

        btnLog = (Button) findViewById(R.id.btnLog);
        btnSignUp = (Button) findViewById(R.id.btnSign);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_awal.this,activity_login.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_awal.this, activity_register.class);
                startActivity(intent);
            }
        });

    }
}
