package com.example.farmerbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void logOutAdmin(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isUserLoggedIn", false);
        editor.putBoolean("isAdmin",false);
        editor.apply();
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void AddCrop(View view) {
        Intent i = new Intent(this, AddCrops.class);
        startActivity(i);
    }

    public void ShowCrops(View view) {
        Intent i = new Intent(this, ShowCrops.class);
        startActivity(i);
    }

    public void AddGovPolicies(View view) {
        Intent i = new Intent(this, AddGovPolicy.class);
        startActivity(i);
    }

    public void ShowPolicies(View view) {
        Intent i = new Intent(this, ViewGovPolicy.class);
        startActivity(i);
    }
}