package com.example.sun.studentassistent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sun.studentassistent.Data.NotesDB;
import com.example.sun.studentassistent.Data.NotesItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2016/4/11.
 */
class NotesAdapter extends RecyclerView.Adapter {
    public NotesAdapter( List<NotesItem> notesItemList,Context context) {
         this.notesItemList=notesItemList;
        this.context=context;

        this.notesItemList=selectDB();


    }

    private Cursor cursor;
    private Context context;
    private List<NotesItem> notesItemList;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
    private ItemClickListener ItemListener;
   // private int Index;

    public NotesAdapter() {


    }

    public List<NotesItem> selectDB(){
        //查找数据
        List<NotesItem> data = new ArrayList<>();
        NotesItem note = null;
        notesDB=new NotesDB(context);
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

    }






    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem,null);
       ViewHolder vh= new ViewHolder(itemview);
        return  vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        if(Index==position){
//
//        }
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.content.setText((CharSequence)notesItemList.get(position).getContent());
        viewHolder.title.setText((CharSequence)notesItemList.get(position).getTitle());
        String url=notesItemList.get(position).getImg();
        viewHolder.imageView.setImageURI(Uri.parse(url));
       viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent();

               i.putExtra("title", notesItemList.get(position).getTitle());
               i.putExtra("img",notesItemList.get(position).getImg());
             // i.putExtra("position", String.valueOf(position));
               i.putExtra("time",notesItemList.get(position).getContent());
               //i.putExtra("position", position);
               i.setClass(context, NotesDetailActivity.class);
               context.startActivity(i);
            ((Activity)(context)).finish();
           }
       });
      

    }

    @Override
    public int getItemCount() {
        //获得item个数
        return notesItemList.size();

    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private TextView title;
        private ImageView imageView;
        private RelativeLayout relativeLayout;



        public ItemClickListener getItemListener() {
            return ItemListener;
        }

        private ItemClickListener ItemListener;
        private TextView content;


        public TextView getTitle() {
            return title;
        }

        public TextView getContent() {
            return content;
        }
        public  ImageView getImageView(){return  imageView;}




        //,ItemClickListener itemListener

        public ViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.textView2);
            content= (TextView) itemView.findViewById(R.id.textView3);
            imageView=(ImageView)itemView.findViewById(R.id.imageView2);
            relativeLayout= (RelativeLayout) itemView.findViewById(R.id.notecell);


        }


//        @Override
//        public void onItemClick(View view, int postion) {
//            if(ItemListener!=null){
//                ItemListener.onItemClick(view,getPosition());
//
//            }
//        }
    }

}
