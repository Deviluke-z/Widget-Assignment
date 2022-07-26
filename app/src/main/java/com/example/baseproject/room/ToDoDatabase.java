package com.example.baseproject.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.baseproject.room.dao.ToDoDAO;
import com.example.baseproject.room.model.ToDoModel;

@Database(entities = {ToDoModel.class}, version = 1, exportSchema = false)
public abstract class ToDoDatabase extends RoomDatabase {
  
  private static ToDoDatabase instance;
  public abstract ToDoDAO noteDAO();
  
  public ToDoDatabase() {
  }
  
  public static ToDoDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context, ToDoDatabase.class, "ToDo Database").build();
    }
    return instance;
  }
}
