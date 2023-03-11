package com.example.farmerbuddy.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farmerbuddy.Crops;
import com.example.farmerbuddy.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fertilizer extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextInputEditText searchCrop;
    MaterialButton searchbtn;
    TextView nitrogenTv, potassiumTv, phosphorustv;
    CardView fertilizerCard;

    public Fertilizer() {
        // Required empty public constructor
    }

    public static Fertilizer newInstance(String param1, String param2) {
        Fertilizer fragment = new Fertilizer();
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
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        searchCrop = view.findViewById(R.id.crop_search_edt);
        searchbtn = view.findViewById(R.id.searchcropbtn);
        fertilizerCard = view.findViewById(R.id.fertilizerCard);
        nitrogenTv = view.findViewById(R.id.nitrogenTV);
        potassiumTv = view.findViewById(R.id.potassiumTv);
        phosphorustv = view.findViewById(R.id.phosphorusTv);

        searchbtn.setOnClickListener(v -> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("crops/"+searchCrop.getText().toString().trim().toLowerCase());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Crops crops = snapshot.getValue(Crops.class);
                    fertilizerCard.setVisibility(View.VISIBLE);
                    nitrogenTv.setText(crops.getN()+ " Kg/Ha");
                    phosphorustv.setText(crops.getP()+ " Kg/Ha");
                    potassiumTv.setText(crops.getK()+ " Kg/Ha");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        return view;
    }
}