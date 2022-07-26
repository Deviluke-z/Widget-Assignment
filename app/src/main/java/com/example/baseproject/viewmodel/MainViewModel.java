package com.example.baseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.baseproject.room.model.ToDoModel;
import com.example.baseproject.room.repo.ToDoRepo;
import com.example.baseproject.utils.Utils;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
  
  private LiveData<List<ToDoModel>> mListNoteLiveData;
  private ToDoRepo mToDoRepo;
  
  public MainViewModel(@NonNull Application application) {
    super(application);
    mToDoRepo = new ToDoRepo(application);
    mListNoteLiveData = mToDoRepo.getAllToDo();
  }
  
  public LiveData<List<ToDoModel>> getListNoteLiveData() {
    return mListNoteLiveData;
  }
  
  public void addData(ToDoModel toDoModel) {
    mToDoRepo.insertData(toDoModel);
    Utils.updateData(getApplication());
  }
  
  public void updateWork(ToDoModel toDoModel) {
    mToDoRepo.updateDataWork(toDoModel.getId(), !toDoModel.isIsDone());
    Utils.updateData(getApplication());
  }
}
