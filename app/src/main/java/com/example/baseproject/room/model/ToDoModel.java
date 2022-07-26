package com.example.baseproject.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "todo_table")
public class ToDoModel {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "title_column")
    private String mToDoTitle;
    
    @ColumnInfo(name = "isDone_column")
    private boolean mIsDone;
    
    public ToDoModel(String mToDoTitle, boolean mIsDone) {
        this.mToDoTitle = mToDoTitle;
        this.mIsDone = mIsDone;
    }
    
    public int getId() {
        return mId;
    }
    
    public void setId(int mId) {
        this.mId = mId;
    }
    
    public String getToDoTitle() {
        return mToDoTitle;
    }
    
    public void setToDoTitle(String mToDoTitle) {
        this.mToDoTitle = mToDoTitle;
    }
    
    public boolean isIsDone() {
        return mIsDone;
    }
    
    public void setIsDone(boolean mIsDone) {
        this.mIsDone = mIsDone;
    }
    
    @Override
    public String toString() {
        return "ToDoModel{" +
          "mId=" + mId +
          ", mToDoTitle='" + mToDoTitle + '\'' +
          ", mIsDone=" + mIsDone +
          '}';
    }
}
