package com.mgs.pesantukang;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class activity_register extends AppCompatActivity {
EditText etNik, etNama, etEmail, etNope, etPassword, etPassword2;
Spinner spKelamin;
Button btnDaftar;
NiftyDialogBuilder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        dialog = NiftyDialogBuilder.getInstance(this);

        etNik =(EditText) findViewById(R.id.etNik);
        etNama =(EditText) findViewById(R.id.etNama);
        etEmail =(EditText) findViewById(R.id.etEmail);
        etNope = (EditText) findViewById(R.id.etNope);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        spKelamin=(Spinner) findViewById(R.id.spKelamin);
        btnDaftar=(Button) findViewById(R.id.btnDaftar);

        etPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(etPassword2.getText().toString().equals(etPassword.getText().toString())){

                }else if(etPassword2.getText().toString().isEmpty()){

                }else{
                    etPassword2.setError("Password tidak sama !");
                }

            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNik.getText().toString().isEmpty()){
                    etNik.setError("Nik harus diisi !");
                }else if(etNama.getText().toString().isEmpty()){
                    etNama.setError("Nama harus di isi !");
                }else if(etNope.getText().toString().isEmpty()){
                    etNope.setError("No. Hp harus di isi !");
                }else if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Password harus di isi");
                }else if(etPassword2.getText().toString().isEmpty()){
                    etPassword2.setError("Re type Password harus di isi !");
                }else{
                    simpanData(etNik.getText().toString().trim(), etNama.getText().toString().trim(),
                            etEmail.getText().toString().trim(), etNope.getText().toString().trim(),
                            etPassword.getText().toString().trim(), spKelamin.getSelectedItem().toString().trim());
                }
            }
        });


    }


    public void simpanData(final String nik, final String nama, final String email, final String notelp, final String password, final String sex){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("user_nik", nik));
                nameValuePairs.add(new BasicNameValuePair("user_nm", nama));
                nameValuePairs.add(new BasicNameValuePair("user_email", email));
                nameValuePairs.add(new BasicNameValuePair("user_notelp", notelp));
                nameValuePairs.add(new BasicNameValuePair("user_password", password));
                nameValuePairs.add(new BasicNameValuePair("user_sex", sex));
                
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://gascoding.id/api_pesantukang/api_simpanuser.php");
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

                   Toast.makeText(getApplication(),"Berhasil register",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplication(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nik, nama, email, notelp, password, sex);

    }


}
