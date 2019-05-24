package Fragment;
// Create Ari & Selamat


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgs.pesantukang.BerandaActivity;
import com.mgs.pesantukang.EditPasswordActivity;
import com.mgs.pesantukang.EditProfilActivity;
import com.mgs.pesantukang.R;
import com.mgs.pesantukang.RequestHandler;
import com.mgs.pesantukang.SessionSharePreference;
import com.mgs.pesantukang.activity_awal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Kelas.SharedVariabel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment {
SessionSharePreference session;
CardView cardLogout,cardGantiPswd;
TextView txtNamaProfil, txtEmail,txtPhone,txtAlamat;
FloatingActionButton fabProfil;

    public FragmentProfil() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profil, container, false);
        cardLogout = view.findViewById(R.id.cardLogout);
        cardGantiPswd = view.findViewById(R.id.cardGantiPswd);
        session = new SessionSharePreference(getActivity());

        txtNamaProfil = view.findViewById(R.id.txtNamaProfil);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtAlamat = view.findViewById(R.id.txtAlamat);
        fabProfil = view.findViewById(R.id.btnEditProfile);

        getJSON(SharedVariabel.ID_USER_PERMANEN);

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setNama(null);
                Intent intent = new Intent(getActivity(), activity_awal.class);
                startActivity(intent);
            }
        });

        cardGantiPswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditPasswordActivity.class);
                startActivity(intent);
            }
        });

        fabProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfilActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Menampilkan Data","Tunggu Sebentar...",false,false);
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
                HashMap<String,String> user_id = new HashMap<>();
                user_id.put("user_id", id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://gascoding.id/api_pesantukang/api_profil.php?user_id=", user_id);
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
                String user_id = c.getString("user_id");
                String user_nik = c.getString("user_nik");
                String user_nm = c.getString("user_nm");
                String user_email   = c.getString("user_email");
                String user_notelp   = c.getString("user_notelp");
                String user_password   = c.getString("user_password");
                String user_sex   = c.getString("user_sex");
                String user_buat   = c.getString("user_buat");
                String user_alamat   = c.getString("user_alamat");

                txtNamaProfil.setText(user_nm);
                txtEmail.setText(user_email);
                txtPhone.setText(user_notelp);
                txtAlamat.setText(user_alamat);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
