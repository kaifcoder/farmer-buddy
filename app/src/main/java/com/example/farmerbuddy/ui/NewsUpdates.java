package com.example.farmerbuddy.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.farmerbuddy.GovPolicyAdapter;
import com.example.farmerbuddy.R;

public class NewsUpdates extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NewsUpdates() {
        // Required empty public constructor
    }


    public static NewsUpdates newInstance(String param1, String param2) {
        NewsUpdates fragment = new NewsUpdates();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] Title = {"title 1","title 2"};
        String[] Link = {"www.google.com", "www.youtube.com"};
        View view = inflater.inflate(R.layout.fragment_news_updates, container, false);
        ListView listView = view.findViewById(R.id.gov_policy_list_view);
        listView.setAdapter(new GovPolicyAdapter(getContext(),Title,Link));
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(getContext(), "clicked"+Link[+position], Toast.LENGTH_SHORT).show();
            String url = "http://"+Link[+position];
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        return view;
    }


}