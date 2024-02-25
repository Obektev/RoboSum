package com.obektevco.robosum.tournamet_utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.obektevco.robosum.R;
import com.obektevco.robosum.obektev_utils.EZToast;
import com.obektevco.robosum.obektev_utils.RobotInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TournamentsGson {

    // saveRobotsInfo: parses info to json and saves as string in shared preferences
    public static void saveRobotsInfo(Activity activity, List<RobotInfo> summaryInfo) {
        Gson gson = new Gson();
        String jsonParsedInfo = gson.toJson(summaryInfo);

        SharedPreferences sharedPreferences = activity.getSharedPreferences("RobotsInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("RobotsInfo", jsonParsedInfo);
        editor.apply();
    }

    public static List<RobotInfo> getRobotsInfo(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("RobotsInfo", Context.MODE_PRIVATE);

        String jsonInfo = sharedPreferences.getString("RobotsInfo", null);

        if (jsonInfo == null)
            return null;

        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<RobotInfo>>(){}.getType();

        return gson.fromJson(jsonInfo, listType);
    }

    public static void deleteTournamentsInfo(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("RobotsInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("RobotsInfo", null);
        editor.apply();

        EZToast.toast(activity, activity.getString(R.string.tournament_table_deleted));
    }

}
