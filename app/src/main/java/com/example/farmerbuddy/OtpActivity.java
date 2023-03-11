package com.example.farmerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    Button verify;
    PinView pinView;
    String otp;
    String codeBySystem;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
       verify = findViewById(R.id.verify);
        pinView = findViewById(R.id.pinView);

        String phoneNo = getIntent().getStringExtra("phoneNo");
        Log.i("OTP", phoneNo);
        sendVerificationCodeToUser(phoneNo);

    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem =s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        String code = phoneAuthCredential.getSmsCode();
                        if(code!=null){
                            pinView.setText(code);
                            verifyCode(code);
                        }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(
                codeBySystem,code
        );
        signInWithPhoneAuthCredential(credential);


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // User signed in successfully
                        String phoneNo = getIntent().getStringExtra("phoneNo");
                        FirebaseUser user = task.getResult().getUser();
                        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (user != null) {
                            Toast.makeText(this, "verification completed", Toast.LENGTH_SHORT).show();
                            if(Objects.equals(phoneNo, "+911234567890")){
                                editor.putBoolean("isAdmin",true);
                                editor.apply();
                                startActivity(new Intent(this,AdminActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            }   else {
                            // User exists, do something
                                editor.putBoolean("isUserLoggedIn", true);
                                editor.putBoolean("isAdmin",false);
                                editor.apply();
                                Intent intent = new Intent(this,ProfileRegistration.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("phoneNo",phoneNo);
                            startActivity(intent);
                            }
                        } else {
                            // User does not exist, do something else
                            Intent intent = new Intent(
                                    this,ProfileRegistration.class
                            ).putExtra("phoneNo",phoneNo);
                            startActivity(intent);
                        }

                    } else {
                        // Sign in failed, display a message and update the UI
                        Toast.makeText(this, "Verification Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void callNextScreen(View view) {
        String code = pinView.getText().toString();

        if(!code.isEmpty()){
            verifyCode(code);
        }

    }
}