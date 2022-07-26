package com.example.baseproject.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.baseproject.room.model.ToDoModel;

import java.util.List;

@Dao
public interface ToDoDAO {

    @Insert
    void addToDo(ToDoModel toDoModel);

    @Query(value = "SELECT * from todo_table")
    LiveData<List<ToDoModel>> getAllToDo();

    @Query(value = "SELECT * from todo_table")
    List<ToDoModel> getAllNormalToDo();

    @Update()
    void updateData(ToDoModel toDoModel);

    @Query(value = "UPDATE todo_table SET isDone_column = :isDone Where mId = :id")
    void updateWork(Boolean isDone, int id);
}
