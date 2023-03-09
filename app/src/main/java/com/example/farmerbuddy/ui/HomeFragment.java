package com.example.farmerbuddy.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.farmerbuddy.CropDetailsActivity;
import com.example.farmerbuddy.R;
import com.example.farmerbuddy.SuggestionGridAdapter;


public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        String[] crop_name = {"mango","tomato","potato"};
        int[] crop_image= {R.drawable.bg,R.drawable.bg,R.drawable.bg};
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        GridView grid = view.findViewById(R.id.suggestion_grid);
        grid.setAdapter(new SuggestionGridAdapter(getContext(),crop_name,crop_image));
        grid.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(getContext(), "You Clicked at " +crop_name[+ position], Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), CropDetailsActivity.class).putExtra("crop_name",crop_name[+position]);
            startActivity(intent);
        });
        return view;
    }
}