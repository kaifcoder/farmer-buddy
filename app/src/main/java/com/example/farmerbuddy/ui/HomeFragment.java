package com.example.farmerbuddy.ui;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.farmerbuddy.CropDetailsActivity;
import com.example.farmerbuddy.Crops;
import com.example.farmerbuddy.R;
import com.example.farmerbuddy.SuggestionGridAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class HomeFragment extends Fragment {
    ArrayList<Crops> cropsList;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }


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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        String phno = sharedPreferences.getString("phone_number",null);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://farmer-buddy-fca75-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("users/"+phno+"/city");

        String[] crop_name = {"mango","tomato","potato"};
        int[] crop_image= {R.drawable.bg,R.drawable.bg,R.drawable.bg};
        TextView citytv,temptv,desctv;
        ImageView weatherIv;
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        citytv = view.findViewById(R.id.city_name);
        temptv =view.findViewById(R.id.weather_temp);
        desctv= view.findViewById(R.id.weather_description);
        weatherIv = view.findViewById(R.id.weather_icon);
        final String[] city = new String[1];
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                city[0] = snapshot.getValue(String.class);
                citytv.setText(city[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        RequestQueue requestQueue;
        String API_KEY = "INSERT YOUR WEATHER API KEY HERE";
        String city_new = citytv.getText().toString().toLowerCase();
        String API_URL = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q="+city_new;

        requestQueue = Volley.newRequestQueue(getContext());

       progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching weather data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null, response -> {
            try {
                progressDialog.dismiss();
                JSONObject currentObject = response.getJSONObject("current");
                String temperature = currentObject.getString("temp_c");
                String description = currentObject.getJSONObject("condition").getString("text");
                String iconUrl = "https:" + currentObject.getJSONObject("condition").getString("icon");
                temptv.setText(temperature+ "°C");
                desctv.setText(description);
                Picasso.get().load(iconUrl).into(weatherIv);
                GridView gridView = view.findViewById(R.id.suggestion_grid);
                cropsList = new ArrayList<>();
                SuggestionGridAdapter SuggestionGridAdapter = new SuggestionGridAdapter(getContext(),cropsList);
                gridView.setAdapter(SuggestionGridAdapter);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference("crops");
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                        Crops crop = dataSnapshot.getValue(Crops.class);
                        String range = crop.getTemp();
                        String[] parts = range.split("-");
                        int start = Integer.parseInt(parts[0]);
                        int end = Integer.parseInt(parts[1]);
                        double temp = Double.parseDouble(temperature);
                        if(temp>=start && temp<=end){
                            cropsList.add(crop);
                        }
                        SuggestionGridAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                        Crops crop = dataSnapshot.getValue(Crops.class);
                        String Trange = crop.getTemp();
                        String[] parts = Trange.split("-");
                        int Tstart = Integer.parseInt(parts[0]);
                        int Tend = Integer.parseInt(parts[1]);
                        double temp = Double.parseDouble(temperature);
                        if(temp>=Tstart && temp<=Tend){
                            cropsList.add(crop);
                        }
                        SuggestionGridAdapter.notifyDataSetChanged();
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            progressDialog.dismiss();
            error.printStackTrace();
        });
        requestQueue.add(request);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        progressDialog.dismiss();
    }
}
