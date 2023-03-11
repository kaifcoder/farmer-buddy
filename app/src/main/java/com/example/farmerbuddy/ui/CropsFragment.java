package com.example.farmerbuddy.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.farmerbuddy.CropDetailsActivity;
import com.example.farmerbuddy.Crops;
import com.example.farmerbuddy.R;
import com.example.farmerbuddy.SuggestionGridAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CropsFragment extends Fragment {

    ArrayList<Crops> cropsList;
    DatabaseReference databaseReference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CropsFragment() {
        // Required empty public constructor
    }

    public static CropsFragment newInstance(String param1, String param2) {
        CropsFragment fragment = new CropsFragment();
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
        View view = inflater.inflate(R.layout.fragment_crops, container, false);
        GridView gridView = view.findViewById(R.id.allcrops_grid);
        cropsList = new ArrayList<>();
        SuggestionGridAdapter SuggestionGridAdapter = new SuggestionGridAdapter(getContext(),cropsList);
        gridView.setAdapter(SuggestionGridAdapter);
        databaseReference = FirebaseDatabase.getInstance()
                .getReference("crops");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                Crops crop = dataSnapshot.getValue(Crops.class);
                cropsList.add(crop);
                SuggestionGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(getContext(), "You Clicked at " +cropsList.get(position).getCrop_name(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), CropDetailsActivity.class).putExtra("crop_name",cropsList.get(position).getCrop_name());
            startActivity(intent);
        });
//        grid.setAdapter(new SuggestionGridAdapter(getContext(),crop_name,crop_image));

        return view;
    }
}