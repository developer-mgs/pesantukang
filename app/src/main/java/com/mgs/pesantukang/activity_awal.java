package com.mgs.pesantukang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_awal extends AppCompatActivity {
Button btnLog,btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
