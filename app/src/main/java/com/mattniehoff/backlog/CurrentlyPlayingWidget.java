package com.mattniehoff.backlog;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class CurrentlyPlayingWidget extends AppWidgetProvider {

    private static final String SHARED_PREFERENCES_FILE = "com.mattniehoff.backlog";
    public static final String GAME_ID_KEY = "game_id";
    public static final String GAME_TITLE_KEY = "game_title";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Get ingredients from SharedPreferences and update.
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
        int gameId = preferences.getInt(GAME_ID_KEY, 0);
        String gameTitle = preferences.getString(GAME_TITLE_KEY, "");


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.currently_playing_widget);

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

