package com.example.farmerbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SuggestionGridAdapter extends BaseAdapter {
    Context context;
    String[] cropName;
    int[] image;

    public SuggestionGridAdapter(Context context, String[] cropName, int[] image) {
        this.context = context;
        this.cropName = cropName;
        this.image = image;
    }


    @Override
    public int getCount() {
        return cropName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.grid_item,null);
        ImageView img = view.findViewById(R.id.crop_image);
        TextView name = view.findViewById(R.id.crop_name);
        img.setImageResource(image[position]);
        name.setText(cropName[position]);
        return view;
    }
}
