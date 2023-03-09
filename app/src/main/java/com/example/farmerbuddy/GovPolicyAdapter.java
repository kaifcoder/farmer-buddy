package com.example.farmerbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GovPolicyAdapter extends BaseAdapter {
    Context context;
    String[] _title;
    String[] _link;

    public GovPolicyAdapter(Context context, String[] title, String[] link) {
        this.context = context;
        this._title = title;
        this._link = link;
    }

    @Override
    public int getCount() {
        return _title.length;
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
        View view = layoutInflater.inflate(R.layout.gov_policy_item,null);
        TextView title = view.findViewById(R.id.policy_title);
        TextView link = view.findViewById(R.id.policy_link);
        title.setText(_title[position]);
        link.setText(_link[position]);
        return view;
    }
}
