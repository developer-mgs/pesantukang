package com.mgs.pesantukang;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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


public class EditPasswordActivity extends AppCompatActivity {
    EditText etPassword, etPasswordBaru, etPasswordUlang;
    Button btnUbahPsw;
    NiftyDialogBuilder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        etPassword=(EditText) findViewById(R.id.etPassword);
        etPasswordBaru=(EditText) findViewById(R.id.etPasswordBaru);
        etPasswordUlang=(EditText) findViewById(R.id.etPasswordUlang);
        btnUbahPsw=(Button) findViewById(R.id.btnUbahPsw);


        etPasswordUlang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(etPasswordUlang.getText().toString().equals(etPasswordBaru.getText().toString())){

                }else if(etPasswordUlang.getText().toString().isEmpty()){

                }else{
                    etPasswordUlang.setError("Password tidak sama !");
                }

            }
        });

        btnUbahPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Password Lama Kosong !");
                }else if(etPasswordBaru.getText().toString().isEmpty()){
                    etPassword.setError("Password Baru Kosong !");
                }else if(etPasswordUlang.getText().toString().isEmpty()){
                    etPassword.setError("Konfirmasi Password Kosong !");
                }else{
                    ubahPassword(SharedVariabel.ID_USER_PERMANEN, etPasswordBaru.getText().toString().trim());
                }
            }
        });
    }


    public void ubahPassword(final String id_user, final String password){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("user_id", id_user));
                nameValuePairs.add(new BasicNameValuePair("user_password", password));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://gascoding.id/api_pesantukang/api_ubahpassword.php");
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
                    Intent intent = new Intent(EditPasswordActivity.this, BerandaActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplication(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_user, password);

    }
}
