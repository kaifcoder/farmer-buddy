package com.example.farmerbuddy.adminActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.farmerbuddy.CropDetailsActivity;
import com.example.farmerbuddy.R;
import com.example.farmerbuddy.SuggestionGridAdapter;

public class ShowCrops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_crops);
        String[] crop_name = {"mango","tomato","potato","guava","rice"};
        int[] crop_image= {R.drawable.bg,R.drawable.bg,R.drawable.bg,R.drawable.bg,R.drawable.bg};
        GridView gridView = findViewById(R.id.allcrops_grid_admin);
        gridView.setAdapter(new SuggestionGridAdapter(this,crop_name,crop_image));
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(this, "You Clicked at " +crop_name[+ position], Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CropDetailsActivity.class).putExtra("crop_name",crop_name[+position]);
            startActivity(intent);
        });
    }
}