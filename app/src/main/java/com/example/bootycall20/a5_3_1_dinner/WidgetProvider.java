package com.example.bootycall20.a5_3_1_dinner;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        String[] strs = new String[] {context.getString(R.string.mcdonalds), context.getString(R.string.jack_box)
                , context.getString(R.string.taco_bell), context.getString(R.string.subway),
                context.getString(R.string.burger_king), context.getString(R.string.applebees),
                context.getString(R.string.chilis), context.getString(R.string.arbys)};

        int randomIndex = new Random().nextInt(8);

        String randomString = strs[randomIndex];


        CharSequence widgetText1 = randomString;
        CharSequence widgetText2 = context.getString(R.string.appwidget_text2);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_provider);
        views.setTextViewText(R.id.appwidget_text1, widgetText1);
        views.setTextViewText(R.id.appwidget_text2, widgetText2);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        // Create an Intent to launch maps when clicked
        Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="
                + randomString));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, geoIntent, 0);

        // Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.appwidget_text2, pendingIntent);
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

