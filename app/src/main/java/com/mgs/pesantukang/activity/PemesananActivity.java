package com.mgs.pesantukang.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.mgs.pesantukang.R;

import java.io.ByteArrayOutputStream;

import Kelas.SharedVariabel;

public class PemesananActivity extends AppCompatActivity {
    private int PLACE_PICKER_REQUEST = 1;
    private static final int CAMERA_REQUEST_CODE = 7777;
    static final int RC_PERMISSION_READ_EXTERNAL_STORAGE = 1;
    static final int RC_IMAGE_GALLERY = 2;
    private Bitmap bitmap;
    Uri uriGambar,file;

    Button btn_tag;
    EditText txtDetailLokasi;
    ImageView imgFoto;
    NiftyDialogBuilder dialogs;
    CardView cardImage;
    TextView txtFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        getSupportActionBar().setTitle("Form Pemesanan");
        dialogs = NiftyDialogBuilder.getInstance(this);
        Camerapermission();

        btn_tag = (Button) findViewById(R.id.btn_tag);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        cardImage = (CardView) findViewById(R.id.cardImage);
        txtFoto = (TextView) findViewById(R.id.txtFoto);

        txtDetailLokasi = (EditText) findViewById(R.id.etDetailLokasi);

        btn_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(PemesananActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogs
                        .withTitle("Upload Gambar")
                        .withMessage("Pilih Media Upload")
                        .withDialogColor("#00867d")
                        .withButton1Text("Galeri")
                        .withButton2Text("Kamera")
                        .withEffect(Effectstype.Fall);
                dialogs.isCancelableOnTouchOutside(true);
                dialogs.setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_REQUEST_CODE);
                        dialogs.dismiss();
                    }
                });

                dialogs.setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(PemesananActivity.this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, RC_PERMISSION_READ_EXTERNAL_STORAGE);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            startActivityForResult(intent, RC_IMAGE_GALLERY);
                            dialogs.dismiss();
                        }
                    }
                });
                dialogs.show();
            }
        });
    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void Camerapermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(PemesananActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(PemesananActivity.this,
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(PemesananActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // menangkap hasil balikan dari Place Picker, dan menampilkannya pada TextView
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data,this);
                /*String toastMsg = String.format(
                        "Place: %s \n" +
                                "Alamat: %s \n" +
                                "Latlng %s \n", place.getName(), place.getAddress(), place.getLatLng().latitude+" "+place.getLatLng().longitude);
                                */
                //tvPlaceAPI.setText(toastMsg);
                //tvPlaceAPI.setText(toastMsg);
                SharedVariabel.LATI = place.getLatLng().latitude;
                SharedVariabel.LONGI = place.getLatLng().longitude;

                btn_tag.setText(place.getName());
                txtDetailLokasi.setText(place.getAddress());
            }

        }

        switch (requestCode) {
            case(CAMERA_REQUEST_CODE) :
                if(resultCode == Activity.RESULT_OK)
                {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    imgFoto.setImageBitmap(bitmap);
                    txtFoto.setText("Foto terpilih");
                }
                break;
        }

        if (requestCode == RC_IMAGE_GALLERY && resultCode == RESULT_OK) {
            uriGambar = data.getData();
            imgFoto.setImageURI(uriGambar);
            txtFoto.setText("Foto terpilih");

        }
        else if (requestCode == 100 && resultCode == RESULT_OK){
            uriGambar = file;
            imgFoto.setImageURI(uriGambar);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RC_PERMISSION_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, RC_IMAGE_GALLERY);
            }
        }
    }

}
