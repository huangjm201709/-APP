package com.example.plan_app.SQLDB;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntegerRes;

import com.example.plan_app.DateString;
import com.example.plan_app.f1.dayplan;
import com.example.plan_app.f2.weekplan;
import com.example.plan_app.f3.mplan;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqliteDB {
    public static final String DB_NAME = "sqlite_dbname";

    public static final int VERSION = 1;

    private static SqliteDB sqliteDB;

    private SQLiteDatabase db;

    private SqliteDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static SqliteDB getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SqliteDB(context);
        }
        return sqliteDB;
    }

    public String s_percent(String a, String b) {
        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);

        Matcher m = pattern.matcher(a);
        a = m.replaceAll("").trim();
        float x = Float.parseFloat(a);

        m = pattern.matcher(b);
        b = m.replaceAll("").trim();
        float y = Float.parseFloat(b);

        DecimalFormat df = new DecimalFormat("0.0%");
        return (df.format(x / y));
    }

    public String s_s(String a, String b) {
        String regEx = "[^0-9]";
        String zf = "(\\d*)";
        Pattern pattern = Pattern.compile(regEx);

        Matcher m = pattern.matcher(a);
        String a_x = m.replaceAll("").trim();
        int x = Integer.parseInt(a_x);

        m = pattern.matcher(b);
        String b_x = m.replaceAll("").trim();
        int y = Integer.parseInt(b_x);

        pattern = Pattern.compile(zf);
        m = pattern.matcher(a);
        String c = m.replaceAll("").trim();

        return ((x / y) + c);


    }

    public List<mplan> loadmp() {
        List<mplan> list = new ArrayList<mplan>();
        Cursor cursor = db
                .query("PLAN", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                mplan p = new mplan();
                p.setName(cursor.getString(cursor.getColumnIndex("Name")));
                p.setdl(cursor.getString(cursor.getColumnIndex("DL")));
                p.setm(cursor.getString(cursor.getColumnIndex("Motivation")));
                String TRW = cursor.getString(cursor.getColumnIndex("TRW"));//总的实际工作量
                String TW = cursor.getString(cursor.getColumnIndex("TW"));//总的计划工作量
                p.sett(TW);
                String pro = s_percent(TRW, TW);
                p.setp(pro);
                list.add(p);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<weekplan> loadwp() throws ParseException {
        List<weekplan> list = new ArrayList<weekplan>();
        Cursor cursor = db
                .query("PLAN", null, null, null, null, null, "Ord");
        if (cursor.moveToFirst()) {
            do {
                weekplan p = new weekplan();
                p.setName(cursor.getString(cursor.getColumnIndex("Name")));
                p.setOrder(cursor.getString(cursor.getColumnIndex("Ord")));//一周里的星期几
                String dl=(cursor.getString(cursor.getColumnIndex("DL")));
                p.setReward(cursor.getString(cursor.getColumnIndex("Reward")));
                int gap=(cursor.getInt(cursor.getColumnIndex("GAP")) );//一开始隔多少天
                String TW = cursor.getString(cursor.getColumnIndex("TW"));//总的计划工作量
                String WRW = cursor.getString(cursor.getColumnIndex("WRW"));//一周的实际工作量
                Calendar c=Calendar.getInstance();
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                Date date =sdf.parse(dl);
                c.setTime(date);
                int nowgap=DateString.getGapCount(c);//现在剩余多少天
                int weeks=0;//现在剩余多少周

                if(nowgap%7>0){
                    weeks=(nowgap/7)+1;
                    p.setTime(String.valueOf(weeks)+"周");//剩余周数
                    if(weeks<=1){
                        String TRW = cursor.getString(cursor.getColumnIndex("TRW"));//总的实际工作量
                        String regEx = "[^0-9]";//纯数字
                        Pattern pattern = Pattern.compile(regEx);
                        Matcher m = pattern.matcher(TW);
                        Matcher n = pattern.matcher(TRW);
                        String TW_int = m.replaceAll("").trim();
                        String TRW_int= n.replaceAll("").trim();
                        int TW_TRW_int =Integer.parseInt(TW_int)-Integer.parseInt(TRW_int);//总的剩余的工作量
                        String TW_TRW=String.valueOf(TW_TRW_int);
                        p.setWork(TW_TRW);//最后一周的剩余的工作量
                        p.setPro(s_percent(WRW,TW_TRW));//一周实际工作量除以最后一周的剩余
                    }
                    else {
                        String WW = s_s(TW, String.valueOf(gap / 7 + 1)); //不是最后一周的  一周计划工作量
                        p.setWork(WW);//不是最后一周的  一周计划工作量
                        String pro = s_percent(WRW, WW);
                        p.setPro(pro);
                    }
                }
                else{
                    weeks=nowgap/7;
                    p.setTime(String.valueOf(weeks)+"周");
                    String WW = s_s(TW, String.valueOf(gap/7)); //一周的计划工作量
                    p.setWork(WW);//一周的计划工作量
                    String pro = s_percent(WRW, WW);
                    p.setPro(pro);
                }
                list.add(p);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<dayplan> loaddp() {
        List<dayplan> list = new ArrayList<dayplan>();
        int x = DateString.intData();//xingqi x
        String select = "Ord regexp ?";
        String regex1 = ".*[0-" + x + "]-[" + x + "-7].*|.*" + x + ".*";
        //String regex2=".*"+x+".*";
        String[] selectA = {regex1};
        Cursor cursor = db
                .query("PLAN", null, select, selectA, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                dayplan p = new dayplan();
                p.setName(cursor.getString(cursor.getColumnIndex("Name")));
                String DayTime = (cursor.getString(cursor.getColumnIndex("DAYTIME")));//一天的需要时间
                String TW = cursor.getString(cursor.getColumnIndex("TW"));//总的计划工作量
                String daytimes = cursor.getString(cursor.getColumnIndex("DTIMES"));//一周多少天次数
                int gap=(cursor.getInt(cursor.getColumnIndex("GAP")) );//一开始隔多少天
                if((gap/7)>0) {
                    String WW = s_s(TW, String.valueOf(gap / 7+1));//一周计划完成工作量
                    String PW = s_s(WW, daytimes);//每一天
                    p.setPlanwork(PW);//一天计划的工作量
                }
                else{
                    String PW=s_s(TW,String.valueOf(gap));
                    p.setPlanwork(PW);
                }
                p.setPlantime(DayTime);//一天计划的时间
                p.setRealtime(cursor.getString(cursor.getColumnIndex("DRT")));//一天实际的时间
                p.setRealwork(cursor.getString(cursor.getColumnIndex("DRW")));//一天实际的工作量
                list.add(p);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void addplan(ContentValues values) {
        db.insert("PLAN", null, values);
    }

    public void updatedp(String name, String drt, String drw) {
        String T = "";
        String W = "";
        db.execSQL("UPDATE PLAN SET DRT=? WHERE Name=?", new String[]{drt,name});
        db.execSQL("UPDATE PLAN SET DRW=? WHERE Name=?", new String[]{drw,name});
        Cursor cursor = db.rawQuery("select * From PLAN where Name=name", null);
        while (cursor.moveToNext()) {
            T = cursor.getString(cursor.getColumnIndex("TRW"));
            W = cursor.getString(cursor.getColumnIndex("WRW"));
        }
        int add1 = Integer.parseInt(drw) + Integer.parseInt(T);
        int add2 = Integer.parseInt(drw) + Integer.parseInt(W);
        T = String.valueOf(add1).toString();
        W = String.valueOf(add2).toString();
        db.execSQL("UPDATE PLAN SET TRW=? WHERE Name=?", new String[]{T,name});
        db.execSQL("UPDATE PLAN SET WRW=? WHERE Name=?", new String[]{W,name});
    }

    public void cleardp() {
        Cursor cursor = db
                .query("PLAN", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            if (DateString.getClean()) {
                do {
                    db.execSQL("update PLAN SET DRT='0'");
                    db.execSQL("update PLAN SET DRW='0'");
                    db.execSQL("update PLAN SET WRW='0'");
                } while (cursor.moveToNext());
            }
            else{
                do {
                    db.execSQL("update PLAN SET DRT='0'");
                    db.execSQL("update PLAN SET DRW='0'");
                } while (cursor.moveToNext());
            }
        }
    }

    public void deletemp(String name){
        db.execSQL("Delete FROM PLAN where Name=?",new String[]{name});
    }
    public void update_reward(String name,String r){
        db.execSQL("UPDATE PLAN SET Reward=? WHERE Name=?",new String[]{r,name});
    }
}
