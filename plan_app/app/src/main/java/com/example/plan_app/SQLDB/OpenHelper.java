package com.example.plan_app.SQLDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
    public static  final String CREATE_PLAN="create table PLAN ("
            + "Name text, "
            + "Motivation text,"
            + "TW text,"  //总的计划工作量
            + "DL text,"
            + "Reward text,"
            + "Ord text," //一周里的星期几
            + "GAP int," //隔了多少天
            + "DTIMES text,"      //一周多少天次数
            + "DAYTIME text,"      //一天所需时间
            + "WRW text,"    //一周实际工作量
            + "DRT text,"    //一天实际的时间
            + "DRW text,"    //一天实际的工作量
            + "TRW text)";  //总的实际工作量


    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_PLAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
