package com.mgs.pesantukang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.mgs.pesantukang.R;

public class DetailActivity extends AppCompatActivity {
    NiftyDialogBuilder dialogs;
    Button btn_pilih1,btn_pilih2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("AC");
        dialogs = NiftyDialogBuilder.getInstance(this);
        btn_pilih1 = (Button) findViewById(R.id.btnPilih1);
        btn_pilih2 = (Button) findViewById(R.id.btnPilih2);

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
