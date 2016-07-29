package com.example.sun.studentassistent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sun.studentassistent.Data.NotesDB;
import com.example.sun.studentassistent.Data.NotesItem;
import com.example.sun.studentassistent.Data.TimetableDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2016/5/7.
 */
public class TimetableAdapter {
    private TimetableDB timetableDB;
    private SQLiteDatabase dbReader;
    private Cursor cursor;
    private Context context;
   private List<TimeTableModel> mListTimeTable;
    public TimetableAdapter( List<TimeTableModel> mListTimeTable,Context context){
        this.mListTimeTable=mListTimeTable;
        this.context=context;

    }
    public List<TimeTableModel> selectDB(){
        //查找数据
        List<TimeTableModel> data = new ArrayList<>();
        TimeTableModel tableModel=null;
        timetableDB=new TimetableDB(context);
        dbReader=timetableDB.getReadableDatabase();
        Cursor c=dbReader.query(TimetableDB.TABLE_NAME,null,null,null,null,null,null);
        if (c != null) {
            while (c.moveToNext()) {
                int idindex=c.getColumnIndex(TimetableDB.ID);
                int id=c.getInt(idindex);
                int startnumIndex = c.getColumnIndex(TimetableDB.STARTNUM);
                int startnum=c.getInt(startnumIndex);
                int endnumIndex = c.getColumnIndex(TimetableDB.ENDNUM);
                int endnum=c.getInt(endnumIndex);
                int starttimeIndex = c.getColumnIndex(TimetableDB.STARTTIME);
                String starttime=c.getString(starttimeIndex);
                int endtimeIndex = c.getColumnIndex(TimetableDB.ENDTIME);
                String endtime=c.getString(endtimeIndex);
                int weekIndex = c.getColumnIndex(TimetableDB.WEEK);
                int week=c.getInt(weekIndex);
                int classroomIndex = c.getColumnIndex(TimetableDB.CLASSROOM);
                String classroom=c.getString(classroomIndex);
                int courseIndex = c.getColumnIndex(TimetableDB.COURSENAME);
                String course=c.getString(courseIndex);
                int teacherIndex = c.getColumnIndex(TimetableDB.TEACHER);
                String teacher=c.getString(teacherIndex);
                int weeknumIndex = c.getColumnIndex(TimetableDB.WEEKNUM);
                String weeknum=c.getString(weeknumIndex);
                tableModel= new TimeTableModel(0,0 ,0,0,null,null,null,null,null,null);
                tableModel.setId(id);
                tableModel.setStartnum(startnum);
                tableModel.setEndnum(endnum);
                tableModel.setClassroom(classroom);
                tableModel.setStarttime(starttime);
                tableModel.setName(course);
                tableModel.setEndtime(endtime);
                tableModel.setWeek(week);
                tableModel.setTeacher(teacher);
                tableModel.setWeeknum(weeknum);
data.add(tableModel);
//                note.setContent(content);
//                note.setImg(url);
//                data.add(note);
            }
        }
        return data;

    }
}
