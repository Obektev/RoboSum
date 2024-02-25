package com.obektevco.robosum.tournamet_utils;

import static android.content.Intent.getIntent;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.obektevco.robosum.R;
import com.obektevco.robosum.obektev_utils.EZToast;
import com.obektevco.robosum.obektev_utils.RobotInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TournamentsTable {

    static Typeface typeface;
    static TableRow.LayoutParams textParams;
    static TableRow.LayoutParams rowParams;
    public static void setupTournamentsTable(Activity activity) {
        typeface = ResourcesCompat.getFont(activity, R.font.aldrich);

        textParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0, 0, 0, 0);

        rowParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(15, 10, 15, 10);

        LinearLayout parentLayout = activity.findViewById(R.id.contentLayout);
        parentLayout.removeAllViews();

        TextView tournamentTableTextView = new TextView(activity);
        tournamentTableTextView.setText(activity.getString(R.string.tournaments_table));
        tournamentTableTextView.setTypeface(typeface);
        tournamentTableTextView.setLayoutParams(textParams);
        tournamentTableTextView.setTextColor(activity.getColor(R.color.text_main));
        tournamentTableTextView.setTextSize(18);
        tournamentTableTextView.setGravity(Gravity.CENTER);
        tournamentTableTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        parentLayout.addView(tournamentTableTextView);

        AppCompatButton deleteButton = new AppCompatButton(activity);
        deleteButton.setGravity(Gravity.CENTER);
        deleteButton.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        deleteButton.setBackground(AppCompatResources.getDrawable(activity, R.drawable.round_shape));

        deleteButton.setBackgroundTintList(ColorStateList.valueOf(activity.getColor(R.color.button_color)));
        deleteButton.setPadding(30, 30, 30, 30);
        deleteButton.setText(activity.getString(R.string.delete_tournaments));
        deleteButton.setOnClickListener(view -> {
            TournamentsGson.deleteTournamentsInfo(activity);
            activity.recreate();
        });

        parentLayout.addView(deleteButton);

        ///// Table layout /////

        ConstraintLayout.LayoutParams scrollViewParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollViewParams.setMargins(15, 15, 15, 15);
        ScrollView scrollView = new ScrollView(activity);
        scrollView.setLayoutParams(scrollViewParams);

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(activity);

        TableLayout tableLayout = new TableLayout(activity);
        tableLayout.setBackground(AppCompatResources.getDrawable(activity, R.drawable.round_shape));
        tableLayout.setBackgroundColor(activity.getColor(R.color.layout_color));

        // Add head of table
        addTableHead(activity, tableLayout);

        // Add tournament table
        addTableContent(activity, tableLayout);

        horizontalScrollView.addView(tableLayout);
        scrollView.addView(horizontalScrollView);
        parentLayout.addView(scrollView);
    }

    private static void addTableHead(Activity activity, TableLayout tableLayout) {
        List<RobotInfo> robotsInfo = TournamentsGson.getRobotsInfo(activity);

        TableRow tableRow = new TableRow(activity);
        tableRow.setLayoutParams(rowParams);
        tableRow.setBackgroundColor(activity.getColor(R.color.background));
        tableRow.setGravity(Gravity.CENTER);

        for (int i = 0; i < robotsInfo.size() + 1; i++) {
            String cellText = "";

            if (i != 0)
                cellText = robotsInfo.get(i - 1).getName();

            TextView headRowTextView = new TextView(activity);
            headRowTextView.setLayoutParams(textParams);
            headRowTextView.setTextColor(activity.getColor(R.color.semi_text));
            headRowTextView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.cell_shape));
            headRowTextView.setTypeface(typeface);
            headRowTextView.setPadding(20, 20, 20, 20);
            headRowTextView.setTextSize(22);
            headRowTextView.setText(cellText);
            headRowTextView.setGravity(Gravity.CENTER);

            tableRow.addView(headRowTextView);
        }
        List<Integer> stringsResources = new ArrayList<>();
        stringsResources.add(R.string.weight);
        stringsResources.add(R.string.wins);
        stringsResources.add(R.string.draws);
        stringsResources.add(R.string.loses);
        stringsResources.add(R.string.place);

        for (Integer stringId : stringsResources) {
            TextView magicTextView = new TextView(activity);
            magicTextView.setLayoutParams(textParams);
            magicTextView.setTextColor(activity.getColor(R.color.semi_text));
            magicTextView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.cell_shape));
            magicTextView.setTypeface(typeface);
            magicTextView.setPadding(20, 20, 20, 20);
            magicTextView.setTextSize(22);
            magicTextView.setText(activity.getString(stringId));
            magicTextView.setGravity(Gravity.CENTER);

            tableRow.addView(magicTextView);
        }

        tableLayout.addView(tableRow);
    }


    public static void addTableContent(Activity activity, TableLayout tableLayout) {

        List<RobotInfo> robotsInfo = TournamentsGson.getRobotsInfo(activity);

        for (int j = 0; j < robotsInfo.size(); j++) {
            TableRow newTableRow = new TableRow(activity);
            newTableRow.setLayoutParams(rowParams);
            newTableRow.setBackgroundColor(activity.getColor(R.color.table_row));
            newTableRow.setGravity(Gravity.CENTER);

            for (int i = 0; i < robotsInfo.size() + 6; i++) {
                String cellText = "";
                TextView cellTextView = new TextView(activity);
                cellTextView.setLayoutParams(textParams);
                cellTextView.setTextColor(activity.getColor(R.color.semi_text));
                cellTextView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.cell_shape));
                cellTextView.setTypeface(typeface);
                cellTextView.setPadding(15, 15, 15, 15);
                cellTextView.setTextSize(19);
                cellTextView.setGravity(Gravity.CENTER);
                // FRICKING JAVA SWITCH-CASE DOESN'T WORK WITH NON-CONSTANT VALUES
                if (i == 0)
                    cellText = robotsInfo.get(j).getName();
                else
                    if (i == robotsInfo.size() + 1)
                        cellText = robotsInfo.get(j).getWeight();
                    else
                        if (i == robotsInfo.size() + 2)
                            cellText = String.valueOf(robotsInfo.get(j).getWins());
                        else
                            if (i == robotsInfo.size() + 3)
                                cellText = String.valueOf(robotsInfo.get(j).getDraws());
                            else
                                if (i == robotsInfo.size() + 4)
                                    cellText = String.valueOf(robotsInfo.get(j).getLooses());
                                else
                                    if (i == robotsInfo.size() + 5)
                                        cellText = String.valueOf(robotsInfo.get(j).getPlace());
                                    else {
                                        RobotInfo leftRobot = robotsInfo.get(j);
                                        RobotInfo topRobot = robotsInfo.get(i - 1);
                                        List<String> leftWinsTop = robotsInfo.get(j).getWinsOnOtherRobots();
                                        List<String> topWinsLeft = robotsInfo.get(i - 1).getWinsOnOtherRobots();

                                        if (leftWinsTop == null && topWinsLeft == null)
                                            cellText = "?";
                                        else {
                                            if (leftWinsTop != null && leftWinsTop.contains(topRobot.getName()))
                                                cellText = leftRobot.getName();
                                            if (topWinsLeft != null && topWinsLeft.contains(leftRobot.getName()))
                                                cellText = topRobot.getName();
                                        }


                                        cellTextView.setOnClickListener(view -> {
                                            BattleDialog.openBattleDialog(activity, leftRobot.getName(), topRobot.getName());
                                        });

                                    }

                cellTextView.setText(cellText);

                newTableRow.addView(cellTextView);
            }


            tableLayout.addView(newTableRow);
        };
    }

}
