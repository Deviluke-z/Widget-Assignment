package com.example.baseproject.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.room.model.ToDoModel;
import com.example.baseproject.utils.Utils;
import com.example.baseproject.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements NoteAdapter.CallBack {
  
  private MainViewModel mMainViewModel;
  private ActivityMainBinding mBinding;
  private NoteAdapter mAdapter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    mMainViewModel.getListNoteLiveData().observe(this, notes -> mAdapter.setListData(notes));
    setUpRcv();
    mBinding.btnAdd.setOnClickListener(v -> {
      if (mBinding.edToDoTitle.getText().toString().equals("")) {
        Toast.makeText(this, "Enter text please!!!", Toast.LENGTH_SHORT).show();
      } else {
        mMainViewModel.addData(new ToDoModel(mBinding.edToDoTitle.getText().toString(), false));
      }
      Utils.updateData(getApplicationContext());
    });
  }
  
  private void setUpRcv() {
    mBinding.recycleView.setLayoutManager(new LinearLayoutManager(
      this,
      LinearLayoutManager.VERTICAL, false)
    );
    mAdapter = new NoteAdapter(this, this);
    mBinding.recycleView.setAdapter(mAdapter);
  }
  
  @Override
  public void onClickNote(ToDoModel toDoModel) {
    toDoModel.setIsDone(toDoModel.isIsDone());
    mMainViewModel.updateWork(toDoModel);
  }
  
  @Override
  protected void onDestroy() {
    mBinding = null;
    super.onDestroy();
  }
}