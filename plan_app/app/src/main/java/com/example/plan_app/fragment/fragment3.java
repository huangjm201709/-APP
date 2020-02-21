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

import com.example.plan_app.Adapter.MplanAdapter;
import com.example.plan_app.R;
import com.example.plan_app.SQLDB.SqliteDB;
import com.example.plan_app.f1.dayplan;
import com.example.plan_app.f3.mplan;

import java.util.ArrayList;
import java.util.List;

public class fragment3 extends Fragment {

    private java.util.List<mplan> List = new ArrayList<mplan>();
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg3, container, false);
    }
    public void onStart() {

        super.onStart();
        addmplan();
        MplanAdapter adapter = new MplanAdapter(getContext(), R.layout.item3, List);
        ListView listView = (ListView) getActivity().findViewById(R.id.listview3);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final mplan mplan=List.get(position);
                AlertDialog.Builder editDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Sure to Delete?")
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SqliteDB.getInstance(getContext()).deletemp(mplan.getName());
                                Toast.makeText(getActivity(), "Deleted!" , Toast.LENGTH_SHORT).show();
                            }
                        });
                editDialog.show();
                return true;
            }
        });
    }
    private void addmplan(){
        List= SqliteDB.getInstance(getContext()).loadmp();
    }
}
