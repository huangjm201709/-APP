package com.example.plan_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {
    int gap=0;//天数
    String dl="";//返回给DL_SHOW
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView cv_test = (CalendarView) findViewById(R.id.calendarView);
        Button btn=findViewById(R.id.calendar_btn);

        cv_test.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar endc=Calendar.getInstance();
                endc.set(year,month,dayOfMonth);
                gap=DateString.getGapCount(endc)    ;
                dl=String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth);//DL年月日
                Toast.makeText(CalendarActivity.this,"您选择的时间是:"+dl+"   天数"+gap,
                        Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalendarActivity.this,AddActivity.class);
                intent.putExtra("days",gap);
                intent.putExtra("dlshow",dl);
                startActivity(intent);
                finish();
            }
        });
    }
}
