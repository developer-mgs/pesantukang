package com.mgs.pesantukang;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Kelas.SharedVariabel;

public class EditProfilActivity extends AppCompatActivity {
EditText etNope,etAlamat;
Button btnUbah;
NiftyDialogBuilder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etNope=(EditText) findViewById(R.id.etNope);
        etAlamat=(EditText) findViewById(R.id.etAlamat);
        btnUbah=(Button) findViewById(R.id.btnUbah);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAlamat.getText().toString().isEmpty()){
                    etAlamat.setError("Alamat harus diisi !");
                }else if(etNope.getText().toString().isEmpty()){
                    etNope.setError("No. Telepon harus diisi !");
                }else{
                    ubahData(SharedVariabel.ID_USER_PERMANEN, etAlamat.getText().toString().trim(), etNope.getText().toString().trim());
                }

            }
        });

//        Log.d("Nomor ", SharedVariabel.ID_USER_PERMANEN);
    }


    public void ubahData(final String id_user, final String alamat, final String notelp){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("user_id", id_user));
                nameValuePairs.add(new BasicNameValuePair("user_alamat", alamat));
                nameValuePairs.add(new BasicNameValuePair("user_notelp", notelp));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://gascoding.id/api_pesantukang/api_updateprofil.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equalsIgnoreCase("success")){

                    Toast.makeText(getApplication(),"profil berhasil diubah",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditProfilActivity.this, BerandaActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplication(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_user, alamat, notelp);

    }
}
