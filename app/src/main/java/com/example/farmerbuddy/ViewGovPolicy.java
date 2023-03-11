package com.example.farmerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.farmerbuddy.GovPolicyAdapter;
import com.example.farmerbuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewGovPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gov_policy);
        ListView listView = findViewById(R.id.gov_policy_list_view_admin);
        List<GovPolicy> govPolicyList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Government Policies");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    GovPolicy govPolicy = snapshot.getValue(GovPolicy.class);
                    govPolicyList.add(govPolicy);
                }
                listView.setAdapter(new GovPolicyAdapter(ViewGovPolicy.this, govPolicyList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewGovPolicy.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(this, "clicked"+govPolicyList.get(position).getLink(), Toast.LENGTH_SHORT).show();
            String url = govPolicyList.get(position).getLink();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
    }
}