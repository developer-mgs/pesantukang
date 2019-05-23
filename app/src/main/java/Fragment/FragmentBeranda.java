package Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgs.pesantukang.BrandActivity;
import com.mgs.pesantukang.R;
import com.mgs.pesantukang.activity.DetailActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import Adapter.CustomAdapter;
import Kelas.HackSmileModelClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBeranda extends Fragment {
    CarouselView carouselView;
    ImageView ico_mn1;
    TextView txtLihatBrand;

    int[] sampleImages = {R.drawable.slidder1, R.drawable.slidder2, R.drawable.slider3};

    public FragmentBeranda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setLogo(R.drawable.logo_barok);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        ico_mn1 = view.findViewById(R.id.ico_mn1);
        txtLihatBrand = view.findViewById(R.id.txtLihatBrand);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        ico_mn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        txtLihatBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BrandActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<HackSmileModelClass> items = new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(getActivity(), items);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_brand);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(adapter);

        // let's create 10 random items

        /*for (int i = 0; i < 10; i++) {
            items.add(new HackSmileModelClass(R.drawable.mn_acok, "Title " + i));
            adapter.notifyDataSetChanged();
        }*/

        items.add(new HackSmileModelClass(R.drawable.br1));
        items.add(new HackSmileModelClass(R.drawable.br2));
        items.add(new HackSmileModelClass(R.drawable.br3));
        items.add(new HackSmileModelClass(R.drawable.br4));
        items.add(new HackSmileModelClass(R.drawable.br5));
        items.add(new HackSmileModelClass(R.drawable.br6));
        items.add(new HackSmileModelClass(R.drawable.br7));
        adapter.notifyDataSetChanged();


        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };




}
