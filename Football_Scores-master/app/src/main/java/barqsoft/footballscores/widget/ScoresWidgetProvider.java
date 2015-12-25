package barqsoft.footballscores.widget;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;
import barqsoft.footballscores.service.ScoreFetchService;

/**
 * Created by Vidya on 12/7/2015.
 */
public class ScoresWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, ScoresWidgetIntentService.class));

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        // Is the intent an UPDATE_VIEW intent? Then update the widget
        if (action != null && action.equals(ScoreFetchService.ACTION_UPDATE_DATA)) {
            context.startService(new Intent(context, ScoresWidgetIntentService.class));
        }
        super.onReceive(context, intent);
    }


    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {

        context.startService(new Intent(context, ScoresWidgetIntentService.class));
    }
}


