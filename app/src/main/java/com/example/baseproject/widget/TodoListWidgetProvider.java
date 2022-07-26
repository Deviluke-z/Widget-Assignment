package com.example.baseproject.widget;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import com.example.baseproject.R;
import com.example.baseproject.room.repo.ToDoRepo;

public class TodoListWidgetProvider extends AppWidgetProvider {
  public static final String ACTION_CLICK = "ACTION_CLICK";
  public static final String ID = "ID";
  public static final String IS_DONE = "IS_DONE";
  
  @SuppressLint("UnspecifiedImmutableFlag")
  public PendingIntent getPendingSelfIntent(Context context, String action) {
    Intent intent = new Intent(context, getClass());
    intent.setAction(action);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      return PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
    } else {
      return PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT);
    }
    
  }
  
  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    for (int appWidgetId : appWidgetIds) {
      Intent intent = new Intent(context, WidgetService.class);
      
      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
      intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.todo_list_widget);
      views.setRemoteAdapter(R.id.listItem, intent);
      views.setEmptyView(R.id.listItem, R.id.appwidget_text);
      views.setPendingIntentTemplate(R.id.listItem, getPendingSelfIntent(context, ACTION_CLICK));
      
      appWidgetManager.updateAppWidget(appWidgetId, views);
    }
  }
  
  @Override
  public void onEnabled(Context context) {
    // Enter relevant functionality for when the first widget is created
  }
  
  @Override
  public void onDisabled(Context context) {
    // Enter relevant functionality for when the last widget is disabled
  }
  
  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals(ACTION_CLICK)) {
      int id = intent.getIntExtra(ID, 0);
      boolean isCheck = intent.getBooleanExtra(IS_DONE, false);
      new ToDoRepo((Application) context.getApplicationContext()).updateDataWork(id, !isCheck);
    }
    super.onReceive(context, intent);
  }
  
}