package com.example.plan_app;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plan_app.SQLDB.SqliteDB;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final Intent intent=getIntent();
        final String dlshow=intent.getStringExtra("dlshow");
        Button submit=(Button)findViewById(R.id.add_btn);
        ImageButton cal=(ImageButton) findViewById(R.id.add_calendar);
        final EditText name=(EditText) findViewById(R.id.add_name);
        final EditText TW=(EditText)findViewById(R.id.add_TW);
        final EditText M=(EditText)findViewById(R.id.add_M);
        final TextView dl_show=findViewById(R.id.add_dl_show);
        final EditText Daytime=(EditText)findViewById(R.id.add_Daytime);
        final EditText Ord=(EditText)findViewById(R.id.add_ord);
        final EditText DW=(EditText)findViewById(R.id.add_DW);//days a week
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(AddActivity.this,CalendarActivity.class);
                startActivity(intent2);
                finish();
            }
        });
        dl_show.setText(dlshow);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String tw=TW.getText().toString();
                String m=M.getText().toString();
                String DT=Daytime.getText().toString();
                String ord=Ord.getText().toString();
                String dw=DW.getText().toString();
                int gap=intent.getIntExtra("days",0);
                if(n!=""){
                    ContentValues values = new ContentValues();
                    values.put("Name",n);
                    values.put("Motivation",m);
                    values.put("TW",tw);//total workload
                    values.put("Ord",ord);
                    values.put("DL",dlshow);
                    values.put("GAP",gap);
                    values.put("DTIMES",dw);
                    values.put("DAYTIME",DT);
                    values.put("WRW","0");
                    values.put("DRT","0");
                    values.put("DRW","0");
                    values.put("TRW","0");
                    values.put("Reward","No Reward");
                    SqliteDB.getInstance(getApplicationContext()).addplan(values);
                    Toast.makeText(AddActivity.this,"Succeed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
