package com.example.plan_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.plan_app.R;
import com.example.plan_app.f1.dayplan;
import com.example.plan_app.f2.weekplan;

import java.util.List;

public class WeekplanAdapter extends ArrayAdapter<weekplan> {
    private int resourceId;
    public WeekplanAdapter(Context context, int textViewResourceId,
                          List<weekplan> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        weekplan weekplan  = getItem(position);
        View view;
        WeekplanAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new WeekplanAdapter.ViewHolder();
            viewHolder.name = (TextView) view.findViewById (R.id.i2_name);
            viewHolder.time=(TextView)view.findViewById(R.id.i2_time);
            viewHolder.pro = (TextView) view.findViewById (R.id.i2_p);
            viewHolder.reward=(TextView) view.findViewById(R.id.i2_reward);
            viewHolder.work=(TextView) view.findViewById(R.id.i2_work);
            viewHolder.order=(TextView) view.findViewById(R.id.i2_order);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (WeekplanAdapter.ViewHolder) view.getTag();
        }
        viewHolder.name.setText(weekplan.getName());
        viewHolder.time.setText(weekplan.getTime());
        viewHolder.pro.setText(weekplan.getPro());
        viewHolder.reward.setText(weekplan.getReward());
        viewHolder.order.setText(weekplan.getOrder());
        viewHolder.work.setText(weekplan.getWork());
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView time;
        TextView pro;
        TextView t;
        TextView reward;
        TextView order;
        TextView work;
    }
}
