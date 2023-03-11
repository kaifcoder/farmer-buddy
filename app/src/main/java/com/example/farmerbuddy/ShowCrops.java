package com.example.farmerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.farmerbuddy.CropDetailsActivity;
import com.example.farmerbuddy.R;
import com.example.farmerbuddy.SuggestionGridAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowCrops extends AppCompatActivity {
    ArrayList<Crops> cropsList;
    DatabaseReference databaseReference;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_crops);
        gridView = findViewById(R.id.allcrops_grid_admin);
        cropsList = new ArrayList<>();
        SuggestionGridAdapter SuggestionGridAdapter = new SuggestionGridAdapter(this,cropsList);
        gridView.setAdapter(SuggestionGridAdapter);
        databaseReference = FirebaseDatabase.getInstance("https://farmer-buddy-fca75-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("crops");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                Crops crop = dataSnapshot.getValue(Crops.class);
                cropsList.add(crop);
                SuggestionGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(this, "You Clicked at " +cropsList.get(position).getCrop_name(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CropDetailsActivity.class).putExtra("crop_name",cropsList.get(position).getCrop_name());
            startActivity(intent);
        });
    }
}