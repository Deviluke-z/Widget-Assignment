package com.example.baseproject.room.repo;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import com.example.baseproject.room.ToDoDatabase;
import com.example.baseproject.room.dao.ToDoDAO;
import com.example.baseproject.room.model.ToDoModel;
import com.example.baseproject.utils.Utils;

import java.util.List;

public class ToDoRepo {
  private ToDoDAO mToDoDAO;
  
  // handler
  private HandlerThread mHandlerThread;
  private Handler mHandler;
  
  private Application application;
  
  public ToDoRepo(Application application) {
    this.application = application;
    ToDoDatabase toDoDatabase = ToDoDatabase.getInstance(application);
    mToDoDAO = toDoDatabase.noteDAO();
    
    // handler
    mHandlerThread = new HandlerThread("new thread");
    mHandlerThread.start();
    mHandler = new Handler(mHandlerThread.getLooper());
  }
  
  public LiveData<List<ToDoModel>> getAllToDo() {
    return mToDoDAO.getAllToDo();
  }
  
  public void insertData(ToDoModel toDoModel) {
    new Thread(() -> mToDoDAO.addToDo(toDoModel)).start();
  }
  
  public List<ToDoModel> getListToDo() {
    return mToDoDAO.getAllNormalToDo();
  }
  
  public void updateDataWork(int id, Boolean isCheck) {
    mHandler.post(() -> mToDoDAO.updateWork(isCheck, id));
    Utils.updateData(application);
  }
}
