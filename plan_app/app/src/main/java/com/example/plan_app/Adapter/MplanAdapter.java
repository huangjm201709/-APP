package com.example.plan_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.plan_app.R;
import com.example.plan_app.f3.mplan;

import java.util.List;

public class MplanAdapter extends ArrayAdapter<mplan> {
    private int resourceId;
    public MplanAdapter(Context context, int textViewResourceId,
                          List<mplan> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        mplan mplan  = getItem(position);
        View view;
        MplanAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new MplanAdapter.ViewHolder();
            viewHolder.name = (TextView) view.findViewById (R.id.i3_name);
            viewHolder.p=(TextView)view.findViewById(R.id.i3_p);
            viewHolder.t = (TextView) view.findViewById (R.id.i3_t);
            viewHolder.m=(TextView) view.findViewById(R.id.i3_m);
            viewHolder.dl=(TextView) view.findViewById(R.id.i3_dl);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (MplanAdapter.ViewHolder) view.getTag();
        }
        viewHolder.name.setText(mplan.getName());
        viewHolder.p.setText(mplan.getp());
        viewHolder.t.setText(mplan.gett());
        viewHolder.m.setText(mplan.getm());
        viewHolder.dl.setText(mplan.getdl());
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView p;
        TextView t;
        TextView m;
        TextView dl;
    }
}
