package Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mgs.pesantukang.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterPesanan;
import Kelas.Pesanan;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPesanan extends Fragment {
RecyclerView recyclerView;
AdapterPesanan adapter;
List<Pesanan> pesananList;
ProgressDialog loading;


    public FragmentPesanan() {
        // Required empty   public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_pesanan, container, false);

        BottomNavigationView navigation = view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        pesananList = new ArrayList<Pesanan>();
        recyclerView = view.findViewById(R.id.recycler_view_aktif);
        adapter = new AdapterPesanan(getActivity(),pesananList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        getDataAktif();

        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_aktif:
                    getDataAktif();
                    //getData(SharedVariable.idPengguna);
                    //SharedVariable.MENU_LAPORANKU = false;
                    return true;
                case R.id.navigation_selesai:
                    getDataSelesai();
                    return true;
            }
            return false;
        }
    };


    void getDataAktif(){
        pesananList.clear();
        adapter.notifyDataSetChanged();
        Pesanan pesanan = new Pesanan(
                "1992",
                "Perbaikan AC",
                "20-05-2019",
                "Jl. ZA. Pagar Alam No.29, Labuhan Ratu, Kec. Kedaton, Kota Bandar Lampung, Lampung 35142",
                "http://gascoding.id/api_pesantukang/image/ac.png",
                "Menunggu",
                "Rp 900.000",
                "AC");
        pesananList.add(pesanan);

        Pesanan pesanan2 = new Pesanan(
                "1993",
                "Alumunium & Kaca",
                "20-05-2019",
                "Jl. ZA. Pagar Alam No.29, Labuhan Ratu, Kec. Kedaton, Kota Bandar Lampung, Lampung 35142",
                "http://gascoding.id/api_pesantukang/image/ac.png",
                "Di Proses",
                "Rp 500.000",
                "Alumunium");
        pesananList.add(pesanan2);

        adapter.notifyDataSetChanged();
    }


    void getDataSelesai(){
        pesananList.clear();
        adapter.notifyDataSetChanged();
        Pesanan pesanan = new Pesanan(
                "1995",
                "Perbaikan AC",
                "20-05-2019",
                "Jl. ZA. Pagar Alam No.29, Labuhan Ratu, Kec. Kedaton, Kota Bandar Lampung, Lampung 35142",
                "http://gascoding.id/api_pesantukang/image/ac.png",
                "Selesai",
                "Rp 100.000",
                "AC");
        pesananList.add(pesanan);
        adapter.notifyDataSetChanged();
    }


}
