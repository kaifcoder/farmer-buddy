package com.example.farmerbuddy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return null;
    }
}
