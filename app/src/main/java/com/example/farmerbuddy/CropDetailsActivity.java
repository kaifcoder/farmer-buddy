package com.example.farmerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CropDetailsActivity extends AppCompatActivity {

    TextView cropName,n,p,k,rainfall,temp,humidity,soiltype;
    ImageView cropImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_details);
        Intent intent = getIntent();
        String crop_name = intent.getStringExtra("crop_name");
        cropName = findViewById(R.id.cropNameTV);
        soiltype =findViewById(R.id.soilTypeTV);
        humidity= findViewById(R.id.humidityTV);
        temp = findViewById(R.id.tempTv);
        n= findViewById(R.id.Ntv);
        p= findViewById(R.id.Ptv);
        k= findViewById(R.id.Ktv);
        rainfall= findViewById(R.id.rainfallTV);
        cropImage = findViewById(R.id.cropImageView);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("crops/"+crop_name);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Crops crop = snapshot.getValue(Crops.class);
                cropName.setText(crop.getCrop_name());
                soiltype.setText(crop.getSoiltype());
                humidity.setText(crop.getHumidity());
                temp.setText(crop.getTemp());
                n.setText(crop.getN());
                p.setText(crop.getP());
                k.setText(crop.getK());
                rainfall.setText(crop.getRainfall());
                Picasso.get().load(crop.getImage()).into(cropImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TextView tv_crop_name = findViewById(R.id.textView_crop_name);
        tv_crop_name.setText(crop_name.toUpperCase());

    }
}