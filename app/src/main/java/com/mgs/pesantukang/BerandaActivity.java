package com.mgs.pesantukang;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import Fragment.FragmentBeranda;
import Fragment.FragmentPesanan;
import Fragment.FragmentProfil;
import Fragment.FragmentBantuan;

public class BerandaActivity extends AppCompatActivity {
    FragmentBeranda fragmentBeranda;
    FragmentPesanan fragmentPesanan;
    FragmentProfil fragmentProfil;
    FragmentBantuan fragmentBantuan;
    SessionSharePreference session;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentBeranda = new FragmentBeranda();
                    goToFragment(fragmentBeranda,true);
                    return true;
                case R.id.navigation_pesan:
                    fragmentPesanan = new FragmentPesanan();
                    goToFragment(fragmentPesanan,true);
                    return true;
                case R.id.navigation_profile:
                    fragmentProfil = new FragmentProfil();
                    goToFragment(fragmentProfil,true);
                    return true;

                case R.id.navigation_help:
                    fragmentBantuan = new FragmentBantuan();
                    goToFragment(fragmentBantuan,true);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        session = new SessionSharePreference(BerandaActivity.this.getApplicationContext());
        String nama = session.getNama();

        //Log.d("Nama ", nama);

        fragmentBeranda = new FragmentBeranda();
        goToFragment(fragmentBeranda,true);
    }

    void goToFragment(Fragment fragment, boolean isTop) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (!isTop)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
