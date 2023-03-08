package com.example.farmerbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button getOtp;
    TextInputEditText phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(/*context=*/ this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        phone = findViewById(R.id.phone);
        getOtp = findViewById(R.id.idBtnGetOtp);
        getOtp.setOnClickListener(v -> {
            String _phone = phone.getText().toString().trim();
            SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("phone_number",_phone);
            editor.apply();
            String phoneNo = "+91"+_phone;
            startActivity(new Intent(MainActivity.this, OtpActivity.class)
                    .putExtra("phoneNo",phoneNo));
        });
    }
}