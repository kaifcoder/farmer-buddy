package com.example.farmerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddCrops extends AppCompatActivity {

    String crop_name, n,p,k,temp,humidity,rainfall,soiltype,crop_image;

    TextInputEditText edt_crop_name, edt_n, edt_p, edt_k, edt_temp,edt_humidity,edt_rainfall,edt_soiltype,edt_cropimage;


    MaterialButton addCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crops);
        edt_crop_name = findViewById(R.id.crop_name_edittext);
        edt_cropimage = findViewById(R.id.crop_img_url);
        edt_n = findViewById(R.id.n_edittext);
        edt_p = findViewById(R.id.p_edittext);
        edt_k = findViewById(R.id.k_edittext);
        edt_temp = findViewById(R.id.temperature_edittext);
        edt_humidity = findViewById(R.id.humidity_edittext);
        edt_rainfall = findViewById(R.id.rainfall_edittext);
        edt_soiltype = findViewById(R.id.soiltype_edittext);
        addCrop = findViewById(R.id.add_crop_btn_admin);

        addCrop.setOnClickListener(v -> {
            crop_name = edt_crop_name.getText().toString().trim();
            crop_image = edt_cropimage.getText().toString().trim();
            n = edt_n.getText().toString().trim();
            p= edt_p.getText().toString().trim();
            k= edt_k.getText().toString().trim();
            temp =edt_temp.getText().toString().trim();
            humidity = edt_humidity.getText().toString().trim();
            rainfall = edt_rainfall.getText().toString().trim();
            soiltype = edt_soiltype.getText().toString().trim();
            Crops crops = new Crops(
                    crop_name,
                    crop_image,
                    n,
                    p,
                    k,
                    rainfall,
                    humidity,
                    temp,
                    soiltype
            );
            DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://farmer-buddy-fca75-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .getReference("crops/"+crop_name);
            databaseRef.setValue(crops).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AddCrops.this, "Crop added Successfully", Toast.LENGTH_SHORT).show();
                    edt_crop_name.setText("");
                    edt_cropimage.setText("");
                    edt_humidity.setText("");
                    edt_k.setText("");
                    edt_p.setText("");
                    edt_n.setText("");
                    edt_rainfall.setText("");
                    edt_soiltype.setText("");
                    edt_temp.setText("");
                }
            });
        });



    }




}