package com.example.plan_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plan_app.R;
import com.example.plan_app.f1.dayplan;

import java.util.List;

public class DayplanAdapter extends ArrayAdapter<dayplan> {
    private int resourceId;
    public DayplanAdapter(Context context, int textViewResourceId,
                         List<dayplan> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
        public View getView(int position, View convertView, ViewGroup parent) {
            dayplan dayplan  = getItem(position);
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById (R.id.i1_name);
                viewHolder.realtime=(TextView)view.findViewById(R.id.i1_realtime);
                viewHolder.realwork = (TextView) view.findViewById (R.id.i1_realwork);
                viewHolder.plantime=(TextView) view.findViewById(R.id.i1_plantime);
                viewHolder.planwork=(TextView) view.findViewById(R.id.i1_planwork);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.name.setText(dayplan.getName());
            viewHolder.realwork.setText(dayplan.getRealwrok());
            viewHolder.realtime.setText(dayplan.getRealtime());
            viewHolder.plantime.setText(dayplan.getPlantime());
            viewHolder.planwork.setText(dayplan.getPlanwork());
            return view;
        }

        class ViewHolder {
            TextView name;
            TextView realwork;
            TextView realtime;
            TextView plantime;
            TextView planwork;
        }
}
