package com.example.sun.studentassistent;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sun.studentassistent.Data.NotesDB;
import com.example.sun.studentassistent.Data.TimetableDB;

public class AddCourseActivity extends AppCompatActivity {
    private EditText startnum;
    private EditText endnum;
    private EditText starttime;
    private EditText endttime;
    private EditText week;
    private EditText classroom;
    private EditText teacher;
    private EditText weeknum;
    private EditText coursename;
    private Button save;
    private TimetableDB timetableDB;
   private SQLiteDatabase tabledb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        init();

    }

    private void init() {
        startnum= (EditText) findViewById(R.id.startnum);
        endnum= (EditText) findViewById(R.id.endnum);
        starttime= (EditText) findViewById(R.id.starttime);
        endttime= (EditText) findViewById(R.id.endtime);
        week= (EditText) findViewById(R.id.week);
       weeknum= (EditText) findViewById(R.id.weeknum);
        classroom= (EditText) findViewById(R.id.classroom);
        coursename= (EditText) findViewById(R.id.coursename);
        teacher= (EditText) findViewById(R.id.teacher);
        save= (Button) findViewById(R.id.savecourse);


    }
    public void save(View v){

   addcoursetodb();
    }
    public void  addcoursetodb() {

        timetableDB = new TimetableDB(this);
        tabledb = timetableDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimetableDB.STARTNUM, startnum.getText().toString());
        contentValues.put(TimetableDB.ENDNUM, endnum.getText().toString());
        contentValues.put(TimetableDB.STARTTIME, starttime.getText().toString());
        contentValues.put(TimetableDB.ENDTIME, endttime.getText().toString());
        contentValues.put(TimetableDB.WEEK, week.getText().toString());
        contentValues.put(TimetableDB.CLASSROOM, classroom.getText().toString());
        contentValues.put(TimetableDB.TEACHER, teacher.getText().toString());
        contentValues.put(TimetableDB.WEEKNUM, weeknum.getText().toString());
        contentValues.put(TimetableDB.COURSENAME, coursename.getText().toString());
        tabledb.insert(TimetableDB.TABLE_NAME, null, contentValues);
    }


    }
