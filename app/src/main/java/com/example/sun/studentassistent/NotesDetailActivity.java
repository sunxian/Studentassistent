package com.example.sun.studentassistent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sun.studentassistent.Data.NotesDB;
import com.example.sun.studentassistent.Data.NotesItem;

import java.util.ArrayList;
import java.util.List;

public class NotesDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    private TextView textView;
    private Button b1;
    private Button b2;
    NotesDB notesDB;
    String  i;
SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // i=getIntent().getStringExtra("position");
       // i=getIntent().getIntExtra("position",0);
        i=getIntent().getStringExtra("time");
        setContentView(R.layout.activity_notes_detail);
        init();




    }

    private void init() {
        imageView= (ImageView) findViewById(R.id.detailimg);
        textView= (TextView) findViewById(R.id.detailtext);
        b1= (Button) findViewById(R.id.delete);
        b2=(Button)findViewById(R.id.back);
        textView.setText(getIntent().getStringExtra("title"));
        imageView.setImageURI(Uri.parse(getIntent().getStringExtra("img")));
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
//        int o= selectDB().size();
//        viewHolder.content.setText((CharSequence) selectDB().get(position).getContent());
//        viewHolder.title.setText((CharSequence) selectDB().get(position).getTitle());
//        String url=selectDB().get(position).getImg();
//        viewHolder.imageView.setImageURI(Uri.parse(url));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
             case R.id.delete:
                notesDB=new NotesDB(NotesDetailActivity.this);
                SQLiteDatabase db =notesDB.getWritableDatabase();
//              String sql="delete from notes where content ='春游'";
//              db.execSQL(sql);
                String [] args = {i};
               int y= db.delete(NotesDB.TABLE_NAME, "time = ? ", args);
                 int o= selectDB().size();
                db.close();
                 Log.e("数目",o+"");

                Intent i=new Intent();
                i.setClass(NotesDetailActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.back:
                Intent intent=new Intent();
                intent.setClass(NotesDetailActivity.this, MainActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
    public List<NotesItem> selectDB(){
        //查找数据
        List<NotesItem> data = new ArrayList<>();
      SQLiteDatabase dbReader;

        NotesItem note = null;
        notesDB=new NotesDB(this);
        dbReader=notesDB.getReadableDatabase();
        Cursor c=dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
        if (c != null) {
            while (c.moveToNext()) {
                int contentIndex = c.getColumnIndex(NotesDB.TIME);
                String content=c.getString(contentIndex);
                int dateIndex = c.getColumnIndex(NotesDB.CONTENT);
                String date=c.getString(dateIndex);
                int imgIndex = c.getColumnIndex(NotesDB.IMAGE);
                String url=c.getString(imgIndex);
                note= new NotesItem(null,null,null);
                note.setTitle(date);
                note.setContent(content);
                note.setImg(url);
                data.add(note);
            }
        }
        return data;


}}
