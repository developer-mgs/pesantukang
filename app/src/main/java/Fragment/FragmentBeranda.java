package Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgs.pesantukang.R;
import com.mgs.pesantukang.activity.DetailActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBeranda extends Fragment {
    CarouselView carouselView;
    ImageView ico_mn1;

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
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        ico_mn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };



}
