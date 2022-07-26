package com.example.baseproject.widget;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.core.content.ContextCompat;

import com.example.baseproject.R;
import com.example.baseproject.room.model.ToDoModel;
import com.example.baseproject.room.repo.ToDoRepo;

import java.util.ArrayList;
import java.util.List;

public class WidgetService extends RemoteViewsService {
  
  @Override
  public RemoteViewsFactory onGetViewFactory(Intent intent) {
    return new MyRemoteViewFactory(getApplicationContext());
  }
  
  class MyRemoteViewFactory implements RemoteViewsFactory {
    private final Context context;
    private ToDoRepo toDoRepo;
    private List<ToDoModel> mListData;
    
    public MyRemoteViewFactory(Context context) {
      this.context = context;
      mListData = new ArrayList<>();
      toDoRepo = new ToDoRepo((Application) context);
    }
    
    @Override
    public void onCreate() {
    
    }
    
    @Override
    public void onDataSetChanged() {
      mListData = toDoRepo.getListToDo();
    }
    
    @Override
    public void onDestroy() {
      mListData.clear();
    }
    
    @Override
    public int getCount() {
      return mListData.size();
    }
    
    @Override
    public RemoteViews getViewAt(int position) {
      ToDoModel toDoModel = mListData.get(position);
      RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.item_widget);
      
      Intent fillInIntent = new Intent();
      if (toDoModel.isIsDone()) {
        remoteView.setTextViewText(R.id.tvItem, toDoModel.getToDoTitle());
        remoteView.setTextColor(R.id.tvItem, ContextCompat.getColor(context, R.color.red));
      } else {
        remoteView.setTextViewText(R.id.tvItem, toDoModel.getToDoTitle());
        remoteView.setTextColor(R.id.tvItem, ContextCompat.getColor(context, R.color.purple_200));
      }
      fillInIntent.putExtra(TodoListWidgetProvider.ID, toDoModel.getId());
      fillInIntent.putExtra(TodoListWidgetProvider.IS_DONE, toDoModel.isIsDone());
      remoteView.setTextViewText(R.id.tvItem, toDoModel.getToDoTitle());
      remoteView.setOnClickFillInIntent(R.id.rootView, fillInIntent);
      return remoteView;
    }
    
    @Override
    public RemoteViews getLoadingView() {
      return null;
    }
    
    @Override
    public int getViewTypeCount() {
      return 1;
    }
    
    @Override
    public long getItemId(int position) {
      return mListData.get(position).getId();
    }
    
    @Override
    public boolean hasStableIds() {
      return true;
    }
  }
}
