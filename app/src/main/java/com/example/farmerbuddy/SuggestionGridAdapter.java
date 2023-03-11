package com.example.farmerbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SuggestionGridAdapter extends BaseAdapter {
    Context context;
    ArrayList<Crops> cropsList;

    public SuggestionGridAdapter(Context context, ArrayList<Crops> cropsList) {
        this.context = context;
        this.cropsList = cropsList;
    }

    @Override
    public int getCount() {
        return cropsList.size();
    }

    @Override
    public Object getItem(int position) {
        return cropsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.grid_item,null);
        ImageView img = view.findViewById(R.id.crop_image);
        TextView name = view.findViewById(R.id.crop_name);
        Crops crop = cropsList.get(position);
        Picasso.get().load(crop.getImage()).into(img);
        name.setText(crop.getCrop_name());
        return view;
    }
}
