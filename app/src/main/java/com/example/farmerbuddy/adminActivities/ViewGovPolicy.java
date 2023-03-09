package com.example.farmerbuddy.adminActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.farmerbuddy.GovPolicyAdapter;
import com.example.farmerbuddy.R;

public class ViewGovPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gov_policy);
        String[] Title = {"title 1","title 2"};
        String[] Link = {"www.google.com", "www.youtube.com"};
        ListView listView = findViewById(R.id.gov_policy_list_view_admin);
        listView.setAdapter(new GovPolicyAdapter(this,Title,Link));
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(this, "clicked"+Link[+position], Toast.LENGTH_SHORT).show();
            String url = "http://"+Link[+position];
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
    }
}