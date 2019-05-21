package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.util.List;

import Kelas.Pesanan;
import de.hdodenhof.circleimageview.CircleImageView;

import com.mgs.pesantukang.R;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.MyViewHolder> {

    private Context mContext;
    private List<Pesanan> pesananList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtLayanan,txtBiaya,txtWaktu,txtAlamat,txtIdlayanan;
        public ImageView imgStatus,imgFoto;
        //public CircleImageView imgFoto;
        public CardView cv_main;
        public RelativeLayout relaList;

        public MyViewHolder(View view) {
            super(view);
            txtLayanan = view.findViewById(R.id.txtLayanan);
            txtBiaya = view.findViewById(R.id.txtBiaya);
            txtWaktu = view.findViewById(R.id.txtWaktuPesan);
            txtAlamat = view.findViewById(R.id.txt_alamat_pesan);
            txtIdlayanan = view.findViewById(R.id.txt_idlayanan);
            imgStatus = view.findViewById(R.id.imgStatus);
            imgFoto = view.findViewById(R.id.imgFoto);
            cv_main = view.findViewById(R.id.cardlist_item);
            relaList = view.findViewById(R.id.relaList);
        }
    }

    public AdapterPesanan(Context mContext, List<Pesanan> pesananList) {
        this.mContext = mContext;
        this.pesananList = pesananList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_list_pesanan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (pesananList.isEmpty()) {
            Toast.makeText(mContext.getApplicationContext(), "Data Kosong !", Toast.LENGTH_LONG).show();
            Log.d("isiuserList : ", "" + pesananList.size());
        } else {

            final Pesanan pesanan = pesananList.get(position);

            if (pesanan.getStatus().equals("Menunggu")) {
                holder.imgStatus.setImageResource(R.drawable.ic_clock64);
            } else if (pesanan.getStatus().equals("Di Proses")) {
                holder.imgStatus.setImageResource(R.drawable.ic_proses);
            } else if (pesanan.getStatus().equals("Selesai")) {
                holder.imgStatus.setImageResource(R.drawable.check);
            }

            holder.txtIdlayanan.setText(pesanan.getId_layanan());
            holder.txtLayanan.setText(pesanan.getNama_layanan());
            holder.txtWaktu.setText(pesanan.getWaktu_pesanan());
            holder.txtAlamat.setText(pesanan.getAlamat_pesanan());

            if(pesanan.getJenis_layanan().equals("AC")){
                holder.imgFoto.setImageResource(R.drawable.mn_acok);
            }else if(pesanan.getJenis_layanan().equals("Alumunium")){
                holder.imgFoto.setImageResource(R.drawable.mn_kaca);
            }

            holder.txtBiaya.setText(pesanan.getBiaya_layanan());

            Log.d("Foto : ",pesanan.getFoto_layanan());

            holder.cv_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            holder.relaList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(mContext.getApplicationContext(), Frm_det_aspirasi.class);
                    intent.putExtra("id_aspirasi", pesanan.getId_layanan());
                    mContext.startActivity(intent);*/
                }
            });


            holder.relaList.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        //return namaWisata.length;
        return pesananList.size();
    }
}
