package com.example.plan_app;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateString {

    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;
    //private static String hour;
    //private static String minute;

    public static int intData(){ //判断周几
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        //hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        //minute = String.valueOf(c.get(Calendar.MINUTE));

        if("1".equals(mWay)){
            //mWay ="天";
            mWay="7";
        }else if("2".equals(mWay)){
            //mWay ="一";
            mWay="1";
        }else if("3".equals(mWay)){
            //mWay ="二";
            mWay="2";
        }else if("4".equals(mWay)){
            //mWay ="三";
            mWay="3";
        }else if("5".equals(mWay)){
            //mWay ="四";
            mWay="4";
        }else if("6".equals(mWay)){
            //mWay ="五";
            mWay="5";
        }else if("7".equals(mWay)){
            //mWay ="六";
            mWay="6";
        }
        return Integer.parseInt(mWay);
    }
    public static String StringData(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="Sunday";
        }else if("2".equals(mWay)){
            mWay ="Monday";
        }else if("3".equals(mWay)){
            mWay ="Tuesday";
        }else if("4".equals(mWay)){
            mWay ="Wednesday";
        }else if("5".equals(mWay)){
            mWay ="Thursday";
        }else if("6".equals(mWay)){
            mWay ="Friday";
        }else if("7".equals(mWay)){
            mWay ="Saturday";
        }
        return mWay;
    }
    public static boolean getClean(){
            if(intData()==1){
            return true;
        }
        return false;
    }
    public static int getGapCount(Calendar toCalendar) { //tianshu
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);


        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}
