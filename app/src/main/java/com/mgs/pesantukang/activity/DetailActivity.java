package com.mgs.pesantukang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.mgs.pesantukang.R;

public class DetailActivity extends AppCompatActivity {
    NiftyDialogBuilder dialogs;
    Button btn_pilih1,btn_pilih2;
    ImageView mIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("AC");
        dialogs = NiftyDialogBuilder.getInstance(this);
        mIcon = findViewById(R.id.imgDetail);
        btn_pilih1 = (Button) findViewById(R.id.btnPilih1);
        btn_pilih2 = (Button) findViewById(R.id.btnPilih2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gb_1);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        mDrawable.setCircular(false);
        mIcon.setImageDrawable(mDrawable);

        mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageViewDetail);
                photoView.setImageResource(R.drawable.gb_1);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        btn_pilih1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogs
                        .withTitle("Perbaikan / Instalasi")
                        .withMessage("Syarat dan ketentuan :\n" +
                                "Tarif diatas adalah biaya minimum jasa Mitra pesantukang.com yaitu Perawatan Standar (Inspeksi dan Cleaning) 1 (satu) unit AC Split maksimal 1 PK\n" +
                                "\n" +
                                "Perawatan Standar AC Split lebih dari 1 unit atau 1 PK, Mitra pesantukang.com akan mengajukan tambahan biaya pekerjaan melalui Aplikasi\n" +
                                "\n" +
                                "Untuk pekerjaan selain Perawatan Standar AC seperti : tambah/isi freon, bongkar pasang AC lama, pasang AC baru, penanganan kebocoran, ganti sparepart, Mitra pesantukang.com akan mengajukan penawaran harga melalui Aplikasi. Setiap penawaran harga melalui Aplikasi sudah termasuk tarif jasa diatas.\n")
                        .withDialogColor("#00867d")
                        .withButton1Text("Batal")
                        .withButton2Text("Lanjut")
                        .withEffect(Effectstype.Fall);
                dialogs.isCancelableOnTouchOutside(true);
                dialogs.setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailActivity.this,PemesananActivity.class);
                        startActivity(intent);
                    }
                });

                dialogs.setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogs.dismiss();
                    }
                });
                dialogs.show();

            }
        });
    }
}
