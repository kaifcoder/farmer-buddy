package com.example.farmerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.farmerbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGovPolicy extends AppCompatActivity {

    String title,link;
    TextInputEditText edt_title,edt_link;
    MaterialButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gov_policy);
        edt_link = findViewById(R.id.link_input);
        edt_title = findViewById(R.id.title_input);
        add = findViewById(R.id.add_policy_btn);
        add.setOnClickListener(v ->
        {
            title = edt_title.getText().toString().trim();
            link = edt_link.getText().toString().trim();
            GovPolicy govPolicy =new GovPolicy(title,link);
            DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://farmer-buddy-fca75-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .getReference("Government Policies/"+title);
            databaseRef.setValue(govPolicy).addOnCompleteListener(task -> {
                Toast.makeText(AddGovPolicy.this, "added Successfully", Toast.LENGTH_SHORT).show();
                edt_title.setText("");
                edt_link.setText("");
            });
        });

    }
}