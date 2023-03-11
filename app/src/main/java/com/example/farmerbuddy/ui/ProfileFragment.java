package com.example.farmerbuddy.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmerbuddy.HomeActivity;
import com.example.farmerbuddy.MainActivity;
import com.example.farmerbuddy.ProfileRegistration;
import com.example.farmerbuddy.R;
import com.example.farmerbuddy.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        MaterialButton deleteacc = view.findViewById(R.id.deleteAcc);
        TextView nametv,addtv,contacttv;
        nametv =view.findViewById(R.id.name_value);
        addtv = view.findViewById(R.id.address_value);
        contacttv = view.findViewById(R.id.contact_info_value);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
        String phone_number = sharedPreferences.getString("phone_number",null);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://farmer-buddy-fca75-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("users/"+phone_number);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                assert user != null;
                nametv.setText(user.getName());
                addtv.setText(user.getAddress());
                contacttv.setText(user.getPhone_number());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                
            }
        });
        deleteacc.setOnClickListener(v -> {
            databaseRef.removeValue().addOnCompleteListener(task -> Toast.makeText(getContext(), "profile deleted from database", Toast.LENGTH_SHORT).show());
            Toast.makeText(getContext(),"account deleted", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isUserLoggedIn", false);
            editor.putBoolean("isAdmin",false);
            editor.putString("phone_number",null);
            editor.putString("phoneNo",null);
            editor.apply();
            Intent loginIntent = new Intent(getContext(), MainActivity.class);
            loginIntent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
            );
            startActivity(loginIntent);
        });
        return view;
    }


}