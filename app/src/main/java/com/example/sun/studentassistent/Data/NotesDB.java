package com.example.sun.studentassistent.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sun on 2016/4/2.
 */
public class NotesDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "notes";
    public static final String  CONTENT= "content";
    public static final String ID = "_id";
    public static final String IMAGE= "image";
    public static final String TIME = "time";
    public NotesDB(Context context) {
        super(context, "notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(" + ID + " integer primary key autoincrement," + CONTENT + " text," + IMAGE + " text," + TIME + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




//    public int Delete(String id){
//        SQLiteDatabase db =getWritableDatabase();
//        String [] args = {NotesDB.ID};
//     int i= db.delete(NotesDB.TABLE_NAME, "_id=?", args);
//        db.close();
//return  i;
//
//}
}
