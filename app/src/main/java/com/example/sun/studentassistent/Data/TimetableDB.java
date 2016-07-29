package com.example.sun.studentassistent.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sun on 2016/5/7.
 */
public class TimetableDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "timetable";
    public static final String  STARTNUM= "startnum";
    public static final String ENDNUM= "endnum";
    public static final String STARTTIME= "starttime";
    public static final String ENDTIME= "endtime";
    public static final String ID= "_id";
    public static final String WEEK= "week";
    public static final String CLASSROOM= "classromm";
     public static final String TEACHER = "teacher";
    public static final String COURSENAME= "coursename";
    public static final String WEEKNUM = "weeknum";
    public TimetableDB(Context context) {
        super(context, "timetable.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + ID + " integer," + STARTNUM + " integer," + ENDNUM + " integer," + WEEK + " integer,"
        +STARTTIME+ " text," +ENDTIME + " text," + COURSENAME + " text,"+ TEACHER+ " text," + CLASSROOM + " text," + WEEKNUM+ " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
