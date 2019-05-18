package Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mgs.pesantukang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPesanan extends Fragment {


    public FragmentPesanan() {
        // Required empty   public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_pesanan, container, false);

        return view;
    }


}
