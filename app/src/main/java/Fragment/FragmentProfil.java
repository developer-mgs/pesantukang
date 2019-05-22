package Fragment;


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

import com.mgs.pesantukang.R;
import com.mgs.pesantukang.SessionSharePreference;
import com.mgs.pesantukang.activity_awal;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment {
SessionSharePreference session;
CardView cardLogout;

    public FragmentProfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profil, container, false);
        cardLogout = view.findViewById(R.id.cardLogout);
        session = new SessionSharePreference(getActivity());

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setNama(null);
                Intent intent = new Intent(getActivity(), activity_awal.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
