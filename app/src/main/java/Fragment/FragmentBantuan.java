package Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgs.pesantukang.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBantuan extends Fragment {


    public FragmentBantuan() {
        // Required empty public constructor
    }

    TextView txtBuatLaporan,txtCekLaporan,txtProfil;
    CardView cardLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_bantuan, container, false);

        return view;
    }




}
