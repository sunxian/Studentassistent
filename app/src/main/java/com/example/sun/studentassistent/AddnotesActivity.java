package com.example.sun.studentassistent;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sun.studentassistent.Data.NotesDB;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddnotesActivity extends AppCompatActivity  {
    @Override
    protected void onResume() {
        super.onResume();

    }

    private AppCompatImageView imageView;
    ImageView imgnote;
    EditText editText;
private PopupWindow popupWindow;
    FloatingActionButton fab;
    private NotesDB notesDB;
    private SQLiteDatabase dbwriter;
    private File imgfile;
    String f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("便签");
        setSupportActionBar(toolbar);

        imageView= (AppCompatImageView) findViewById(R.id.iamge);
        imgnote= (ImageView) findViewById(R.id.imgnote);
        editText= (EditText) findViewById(R.id.edit);
        LayoutInflater inflater= (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.popupwindow,null);
        TextView pop1= (TextView)layout.findViewById(R.id.pop1);
        TextView pop2= (TextView) layout.findViewById(R.id.pop2);



        pop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addnotestodb();
                finish();
            }
        });
        pop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddnotesActivity.this,"delete",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        popupWindow=new PopupWindow(layout,300,300);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.back));
        popupWindow.setOutsideTouchable(true);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgnote.setVisibility(View.VISIBLE);
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 100);
            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow.isShowing()) {

                    popupWindow.dismiss();// 关闭
                } else {
                    int[] location = new int[2];
                    fab.getLocationOnScreen(location);
                    popupWindow.showAtLocation(fab,Gravity.NO_GRAVITY,location[0]-popupWindow.getWidth(),location[1]);;
                    // 显示
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==100){
            if (data!=null){
                imgnote.setImageURI(data.getData());
                //存储图片uri
                imgfile=new File(String.valueOf(data.getData()));
                f=data.getDataString();

            }}

    }
    private String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        String str=format.format(date);
        //Toast.makeText(this,"ss"++"  ??? "+str,Toast.LENGTH_SHORT).show();
        return  str;
    }
    //添加到数据库
public void  addnotestodb(){
    notesDB=new NotesDB(this);
    dbwriter=notesDB.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
    contentValues.put(notesDB.CONTENT, editText.getText().toString());
    contentValues.put(notesDB.TIME, getTime());
    contentValues.put(notesDB.IMAGE, f);
dbwriter.insert(notesDB.TABLE_NAME, null, contentValues);
//    Toast.makeText(this,">>>>  "+flag,Toast.LENGTH_SHORT).show();
//System.out.println("charu >>>> " + flag);

}



}
