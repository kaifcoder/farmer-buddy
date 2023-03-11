package com.example.farmerbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GovPolicyAdapter extends BaseAdapter {
    Context context;

    List<GovPolicy> govPolicyList;

    public GovPolicyAdapter(Context context, List<GovPolicy> govPolicyList) {
        this.context = context;
        this.govPolicyList = govPolicyList;
    }

    @Override
    public int getCount() {
        return govPolicyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.gov_policy_item,null);
        TextView title = view.findViewById(R.id.policy_title);
        TextView link = view.findViewById(R.id.policy_link);
        GovPolicy govPolicy = govPolicyList.get(position);
        title.setText(govPolicy.getTitle());
        link.setText(govPolicy.getLink());
        return view;
    }
}
