package com.example.plan_app.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plan_app.Adapter.DayplanAdapter;
import com.example.plan_app.Adapter.WeekplanAdapter;
import com.example.plan_app.DateString;
import com.example.plan_app.R;
import com.example.plan_app.SQLDB.SqliteDB;
import com.example.plan_app.f1.dayplan;
import com.example.plan_app.f2.weekplan;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class fragment2 extends Fragment {
    private java.util.List<weekplan> List = new ArrayList<weekplan>();

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=this.getLayoutInflater().inflate((R.layout.fg2),null);
        try {
            addweekplan();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        WeekplanAdapter adapter = new WeekplanAdapter(getContext(), R.layout.item2, List);
        ListView listView = (ListView)view.findViewById(R.id.listview2);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final weekplan weekplan=List.get(position);
                final EditText editText=new EditText(getActivity());
                AlertDialog.Builder editDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Reward")
                        .setView(editText)
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SqliteDB.getInstance(getContext()).update_reward(weekplan.getName(),
                                        editText.getText().toString());
                                Toast.makeText(getActivity(), "GO GO GO!" , Toast.LENGTH_SHORT).show();
                            }
                        });
                editDialog.show();
                return true;
            }
        });

        return view;
    }

    public void onStart() {
        super.onStart();
    }
    private void addweekplan() throws ParseException {
        List= SqliteDB.getInstance(getContext()).loadwp();
    }
}
