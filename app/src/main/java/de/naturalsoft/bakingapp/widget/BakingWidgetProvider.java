package de.naturalsoft.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.ui.list.ListActivity;
import de.naturalsoft.bakingapp.utils.AppConfig;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);


        Intent intent = new Intent(context, ListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, AppConfig.WIDGET_REQUEST_CODE,intent,0);
        views.setPendingIntentTemplate(R.id.appwidget_rootView, pendingIntent);

        Intent listIntent = new Intent(context, WidgetRemoteViewService.class);
        views.setRemoteAdapter(R.id.appwidget_listview, listIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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
}

