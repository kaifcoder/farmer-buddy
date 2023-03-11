package com.example.farmerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://farmer-buddy-fca75-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("users/"+phone_number);
        ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(ProfileRegistration.this, "profile already exist", Toast.LENGTH_SHORT).show();
                    Intent loginIntent;
                    loginIntent = new Intent(getApplicationContext(), HomeActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(loginIntent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(ProfileRegistration.this, "error occured", Toast.LENGTH_SHORT).show();
            }
        };

        databaseRef.addListenerForSingleValueEvent(dataListener);
        button.setOnClickListener(v -> {
            User u = new User(
                    name.getText().toString().trim(),
                    "+91"+phone_no.getText().toString().trim(),
                    add.getText().toString().trim(),
                    city.getText().toString().trim()
            );
            makeProfile(u);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("haveProfile",true);
            editor.putString("phoneNo",phone_no.getText().toString());
            editor.apply();
            
            Intent i = new Intent(ProfileRegistration.this,HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
    }

    private void makeProfile(User u) {
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
        String phone_number = sharedPreferences.getString("phone_number",null);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://farmer-buddy-fca75-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("users/"+phone_number);
        databaseRef.setValue(u);
        Toast.makeText(this, "created user profile", Toast.LENGTH_SHORT).show();
    }
}