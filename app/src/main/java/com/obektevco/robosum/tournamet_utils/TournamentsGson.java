package com.obektevco.robosum.tournamet_utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TournamentsGson {

    // saveRobotsInfo: parses info to json and saves as string in shared preferences
    public static void saveRobotsInfo(Activity activity, List<NewTournamentDialog.RobotInfo> summaryInfo) {
        Gson gson = new Gson();
        String jsonParsedInfo = gson.toJson(summaryInfo);

        SharedPreferences sharedPreferences = activity.getSharedPreferences("RobotsInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("RobotsInfo", jsonParsedInfo);
        editor.apply();
    }

    public static List<NewTournamentDialog.RobotInfo> getRobotsInfo(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("RobotsInfo", Context.MODE_PRIVATE);

        String jsonInfo = sharedPreferences.getString("RobotsInfo", null);

        assert jsonInfo != null;

        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<NewTournamentDialog.RobotInfo>>(){}.getType();

        return gson.fromJson(jsonInfo, listType);
    }

}
