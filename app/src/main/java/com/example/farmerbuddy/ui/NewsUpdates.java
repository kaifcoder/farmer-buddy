package com.example.farmerbuddy.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.farmerbuddy.GovPolicyAdapter;
import com.example.farmerbuddy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsUpdates#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        String[] Link = {"link 1", "link 2"};
        View view = inflater.inflate(R.layout.fragment_news_updates, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.gov_policy_recycler_view);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        return view;
    }
}