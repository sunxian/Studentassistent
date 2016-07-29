package com.example.sun.studentassistent;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sun.studentassistent.Data.NotesDB;
import com.example.sun.studentassistent.Data.TimetableDB;

public class CourseDetailActivity extends AppCompatActivity {
    private TextView startnum;
    private TextView endnum;
    private TextView starttime;
    private TextView endttime;
    private TextView week;
    private TextView classroom;
    private TextView teacher;
    private TextView weeknum;
    private TextView coursename;
    private Button delete;
    TimetableDB timetableDB;
    String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i=getIntent().getStringExtra("teacher");
        setContentView(R.layout.activity_course_detail);
        init();

    }

    private void init() {
        startnum= (TextView) findViewById(R.id.startnum1);
        endnum= (TextView) findViewById(R.id.endnum1);
        starttime= (TextView) findViewById(R.id.starttime1);
        endttime= (TextView) findViewById(R.id.endtime1);
        week= (TextView) findViewById(R.id.week1);
        weeknum= (TextView) findViewById(R.id.weeknum1);
        classroom= (TextView) findViewById(R.id.classroom1);
        coursename= (TextView) findViewById(R.id.coursename1);
        teacher= (TextView) findViewById(R.id.teacher1);
        startnum.setText(getIntent().getIntExtra("sn", 0)+"");
        endnum.setText(getIntent().getIntExtra("en",0)+"");
        week.setText(getIntent().getIntExtra("week", 0)+"");
        starttime.setText(getIntent().getStringExtra("st"));
        coursename.setText(getIntent().getStringExtra("course"));
        endttime.setText(getIntent().getStringExtra("et"));
        teacher.setText(getIntent().getStringExtra("teacher"));
        classroom.setText(getIntent().getStringExtra("classroom"));
        weeknum.setText(getIntent().getStringExtra("weeknum"));
        delete= (Button) findViewById(R.id.deletecourse);
    }
    public  void delete(View v){
       timetableDB= new TimetableDB(CourseDetailActivity.this);
        SQLiteDatabase db =timetableDB.getWritableDatabase();
        String [] args = {i};
       db.delete(TimetableDB.TABLE_NAME, "teacher = ? ", args);
        db.close();
        Intent i=new Intent();
        i.setClass(CourseDetailActivity.this, MainActivity.class);
        startActivity(i);
    }
}
