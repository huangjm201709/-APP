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
import com.example.plan_app.DateString;
import com.example.plan_app.R;
import com.example.plan_app.SQLDB.SqliteDB;
import com.example.plan_app.f1.dayplan;

import java.util.ArrayList;
import java.util.List;

public class fragment1 extends Fragment {

    private List<dayplan> List = new ArrayList<dayplan>();

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view=this.getLayoutInflater().inflate((R.layout.fg1),null);
        TextView textView=(TextView) view.findViewById(R.id.fg1_date);
        String today= DateString.StringData();
        textView.setText(today);
        addDayplan();
        DayplanAdapter adapter = new DayplanAdapter(getActivity(), R.layout.item1, List);
        ListView listView = (ListView) view.findViewById(R.id.listview1);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final dayplan dayplan=List.get(position);
                final View D_view = getLayoutInflater().inflate(R.layout.dialog_view, null);
                final EditText edt=(EditText)D_view.findViewById(R.id.dlg_rt);
                final EditText edw=(EditText)D_view.findViewById(R.id.dlg_rw);
                final TextView tt=(TextView)D_view.findViewById(R.id.dlg_tt);
                final TextView tw=(TextView)D_view.findViewById(R.id.dlg_tw);
                AlertDialog.Builder editDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Input Your Date")
                        .setView(D_view)
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SqliteDB.getInstance(getContext()).updatedp(dayplan.getName(),
                                        edt.getText().toString(),edw.getText().toString());
                                Toast.makeText(getActivity(), "Done!" , Toast.LENGTH_SHORT).show();
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
    private void addDayplan(){
        List= SqliteDB.getInstance(getContext()).loaddp();
    }
}
