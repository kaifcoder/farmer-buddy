package com.example.farmerbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileRegistration extends AppCompatActivity {


Button button;
TextInputEditText name,phone_no,add,city;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_profile_registration);
        button = findViewById(R.id.proceed_btn);
        name=findViewById(R.id.name_input);
        phone_no = findViewById(R.id.phone_input);
        add = findViewById(R.id.address_input);
        city = findViewById(R.id.city_input);
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
        String phone_number = sharedPreferences.getString("phone_number",null);
        phone_no.setText(phone_number);

        button.setOnClickListener(v -> {
            makeProfile();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("haveProfile",true);
            editor.apply();
            Intent i = new Intent(ProfileRegistration.this,HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
    }

    private void makeProfile() {

    }
}