package com.example.farmerbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CropDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_details);
        Intent intent = getIntent();
        String crop_name = intent.getStringExtra("crop_name");

        TextView tv_crop_name = findViewById(R.id.textView_crop_name);
        tv_crop_name.setText(crop_name);

    }
}