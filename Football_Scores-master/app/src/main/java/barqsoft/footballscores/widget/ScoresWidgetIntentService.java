package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilies;
import barqsoft.footballscores.ScoresAdapter;

/**
 * Created by Vidya on 12/8/2015.
 */
public class ScoresWidgetIntentService extends IntentService {

    public ScoresWidgetIntentService() {
                super("TodayWidgetIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // Retrieve all of the Score widget ids: these are the widgets we need to update
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, ScoresWidgetProvider.class));

        // Execute a query to obtain the info for the widget
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String[] selectionArgs = new String[]{currentDate};

        Cursor scoreCursor=getContentResolver().query(
                DatabaseContract.scores_table.buildScoreWithDate(),
                null,
                null,
                selectionArgs,
                null);

        if (scoreCursor == null) {

            return;
        }

        if (!scoreCursor.moveToFirst()) {
            scoreCursor.close();
            return;
        }

        String homeName = scoreCursor.getString(ScoresAdapter.COL_HOME);
        String awayName = scoreCursor.getString(ScoresAdapter.COL_AWAY);
        String scoreTextView = Utilies.getScores(this,scoreCursor.getInt(ScoresAdapter.COL_HOME_GOALS),
                scoreCursor.getInt(ScoresAdapter.COL_AWAY_GOALS));
        String matchTime=scoreCursor.getString(ScoresAdapter.COL_MATCHTIME);

        // Perform this loop procedure for each widget that belongs to this provider
        for (int appWidgetId : appWidgetIds) {

            // Create an Intent to launch MainActivity
            Intent mainIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_scores_small);
            remoteViews.setOnClickPendingIntent(R.id.widget, pendingIntent);

            // Update the view info
            remoteViews.setImageViewResource(R.id.home_crest,
                    Utilies.getTeamCrestByTeamName(this,homeName));
            remoteViews.setImageViewResource(R.id.away_crest,
                    Utilies.getTeamCrestByTeamName(this, awayName));
            remoteViews.setTextViewText(R.id.home_name, homeName);
            remoteViews.setTextViewText(R.id.away_name, awayName);
            remoteViews.setTextViewText(R.id.score_textview, scoreTextView);
            remoteViews.setTextViewText(R.id.date_textview, matchTime);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                remoteViews.setEmptyView(R.id.widget, R.id.widget_empty);
            }

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        scoreCursor.close();
    }

}

