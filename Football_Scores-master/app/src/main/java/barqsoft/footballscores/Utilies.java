package barqsoft.footballscores;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies {
    public static final int BUNDESLIGA1 = 394;
    public static final int BUNDESLIGA2 = 395;
    public static final int BUNDESLIGA3 = 403;
    public static final int LIGUE = 396;
    public static final int PREMIER_LEAGUE = 398;
    public static final int PRIMERA_DIVISION = 399;
    public static final int SEGUNDA_DIVISION = 400;
    public static final int SERIE_A = 401;
    public static final int PRIMERA_LIGA = 402;
    public static final int EREDIVISIE = 404;
    public static final int CHAMPIONS_LEAGUE = 405;
   public static final long SECONDS_IN_A_DAY = 86400000;

    public static String getLeague(Context context,int league_num) {
        switch (league_num) {
            case SERIE_A:
                return context.getString(R.string.seriaa);
            case PREMIER_LEAGUE:
                return context.getString(R.string.premierleague);
            case CHAMPIONS_LEAGUE:
                return context.getString(R.string.champions_league);
            case PRIMERA_DIVISION:
                return context.getString(R.string.primera_division);
            case BUNDESLIGA1:
            case BUNDESLIGA2:
            case BUNDESLIGA3:
                return context.getString(R.string.bundesliga);
            case LIGUE:
                return context.getString(R.string.ligue);
            case SEGUNDA_DIVISION:
                return context.getString(R.string.segunda_division);
            case PRIMERA_LIGA:
                return context.getString(R.string.primera_liga);
            case EREDIVISIE:
                return context.getString(R.string.eredivisie);
            default:
                return context.getString(R.string.no_league);
        }
    }

    public static String getMatchDay(Context context,int match_day, int league_num) {
        if (league_num == CHAMPIONS_LEAGUE) {
            if (match_day <= 6) {
                return
                        context.getString(R.string.group_stage_text) + ", " +
                                context.getString(R.string.matchday_number,match_day);
            } else if (match_day == 7 || match_day == 8) {
                return context.getString(R.string.first_knockout_round);
            } else if (match_day == 9 || match_day == 10) {
                return context.getString(R.string.quarter_final);
            } else if (match_day == 11 || match_day == 12) {
                return context.getString(R.string.semi_final);
            } else {
                return context.getString(R.string.final_text);

            }
        } else {
            return context.getString(R.string.matchday_number,match_day);
            //return "Matchday : " + String.valueOf(match_day);
        }
    }

    public static String getScores(Context context,int home_goals, int awaygoals) {
        if (home_goals < 0 || awaygoals < 0) {
            return context.getString(R.string.no_score);
        } else {
            return context.getString(R.string.score_text,home_goals,awaygoals);
        }
    }

    public static int getTeamCrestByTeamName(Context context,String teamname) {
        if (teamname == null) {
            return R.drawable.ic_launcher;
        }
        switch (teamname) { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC":
                return R.drawable.arsenal;
            case "Manchester United FC":
                return R.drawable.manchester_united;
            case "Swansea City":
                return R.drawable.swansea_city_afc;
            case "Leicester City":
                return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC":
                return R.drawable.everton_fc_logo1;
            case "West Ham United FC":
                return R.drawable.west_ham;
            case "Tottenham Hotspur FC":
                return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion":
                return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC":
                return R.drawable.sunderland;
            case "Stoke City FC":
                return R.drawable.stoke_city;
            default:
                return R.drawable.ic_launcher;
        }
    }

    // Returns date in the format "2015-07-18"
    public static String getFragmentDate(int i) {
        Date fragmentdate = new Date(System.currentTimeMillis() + (i * SECONDS_IN_A_DAY));
        SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
        return mformat.format(fragmentdate);
    }

}
