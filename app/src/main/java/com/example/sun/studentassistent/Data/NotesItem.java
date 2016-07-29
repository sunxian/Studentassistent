package com.example.sun.studentassistent.Data;

import android.graphics.Bitmap;

/**
 * Created by sun on 2016/4/11.
 */
public class NotesItem {
    public NotesItem(String title,String content,String img) {
        this.content=content;
        this.title=title;
        this.img=img;
    }
    public String title="title";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String content="content";
    public String img=null;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
