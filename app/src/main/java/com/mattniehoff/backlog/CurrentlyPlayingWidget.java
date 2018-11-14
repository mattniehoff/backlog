package com.mattniehoff.backlog;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.mattniehoff.backlog.utils.SharedPreferencesUtils;

/**
 * Implementation of App Widget functionality.
 */
public class CurrentlyPlayingWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Get ingredients from SharedPreferences and update.
        SharedPreferences preferences = context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREFERENCES_FILE, 0);
        int gameId = preferences.getInt(SharedPreferencesUtils.GAME_ID_KEY, 0);
        String gameTitle = preferences.getString(SharedPreferencesUtils.GAME_TITLE_KEY, "");


        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.WIDGET_INTENT_ID, gameId);
        // https://stackoverflow.com/q/18037991/2107568
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.currently_playing_widget);
        views.setOnClickPendingIntent(R.id.appwidget_linear_layout, pendingIntent);

        if (gameTitle != null && gameTitle.length() > 0) {
            views.setTextViewText(R.id.appwidget_currently_playing_title, gameTitle);
        }

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
}

