package com.mgs.pesantukang;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Fragment.FragmentBeranda;
import Fragment.FragmentPesanan;
import Fragment.FragmentProfil;
import Fragment.FragmentBantuan;
import Kelas.SharedVariabel;

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

        getJSON(nama);

        //Log.d("Nama ", nama);

        fragmentBeranda = new FragmentBeranda();
        goToFragment(fragmentBeranda,true);
    }


    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BerandaActivity.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(getApplication(),"Data "+id,Toast.LENGTH_LONG).show();
                //showDetail(s);
                showJSON2(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put("mail", id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://pstiubl.com/api_diskom1/dt_user.php?mail=", nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showJSON2(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject c = result.getJSONObject(i);
                String id_user = c.getString("id_user");
                String nama = c.getString("nm_lengkap");
                String no_hp = c.getString("no_hp");
                String pass = c.getString("password");

                SharedVariabel.ID_USER_PERMANEN = id_user;


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

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
