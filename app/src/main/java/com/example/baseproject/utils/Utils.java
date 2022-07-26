package com.example.baseproject.utils;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;

import com.example.baseproject.R;
import com.example.baseproject.widget.TodoListWidgetProvider;

public class Utils {
  public static void updateData(Context context) {
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
    ComponentName thisAppWidget = new ComponentName(
      context.getApplicationContext().getPackageName(),
      TodoListWidgetProvider.class.getName()
    );
    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listItem);
  }
}
