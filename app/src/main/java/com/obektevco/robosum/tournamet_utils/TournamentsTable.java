package com.obektevco.robosum.tournamet_utils;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.tabs.TabLayout;
import com.obektevco.robosum.R;

import java.util.List;

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

        ///// Table layout /////

        ConstraintLayout.LayoutParams scrollViewParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollViewParams.setMargins(15, 15, 15, 15);
        ScrollView scrollView = new ScrollView(activity);
        scrollView.setLayoutParams(scrollViewParams);

        TableLayout tableLayout = new TableLayout(activity);
        tableLayout.setBackground(AppCompatResources.getDrawable(activity, R.drawable.round_shape));
        tableLayout.setBackgroundColor(activity.getColor(R.color.layout_color));

        // Add head of table
        addTableHead(activity, tableLayout);

        for (int j = 0; j < 30; j++) {
            TableRow newTableRow = new TableRow(activity);
            newTableRow.setLayoutParams(rowParams);
            newTableRow.setBackgroundColor(activity.getColor(R.color.table_row));
            newTableRow.setGravity(Gravity.CENTER);

            for (int i = 0; i < 12; i++) {

                TextView testTextView = new TextView(activity);
                testTextView.setLayoutParams(textParams);
                testTextView.setTextColor(activity.getColor(R.color.semi_text));
                testTextView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.cell_shape));
                testTextView.setTypeface(typeface);
                testTextView.setPadding(10, 10, 10, 10);
                testTextView.setTextSize(22);
                testTextView.setText("test");

                newTableRow.addView(testTextView);
            }


            tableLayout.addView(newTableRow);
        };

        scrollView.addView(tableLayout);
        parentLayout.addView(scrollView);
    }

    private static void addTableHead(Activity activity, TableLayout tableLayout) {
        List<NewTournamentDialog.RobotInfo> robotsInfo = TournamentsGson.getRobotsInfo(activity);

        TableRow tableRow = new TableRow(activity);
        tableRow.setLayoutParams(rowParams);
        tableRow.setBackgroundColor(activity.getColor(R.color.background));
        tableRow.setGravity(Gravity.CENTER);

        for (int i = 0; i < robotsInfo.size(); i++) {

            TextView headRowTextView = new TextView(activity);
            headRowTextView.setLayoutParams(textParams);
            headRowTextView.setTextColor(activity.getColor(R.color.semi_text));
            headRowTextView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.cell_shape));
            headRowTextView.setTypeface(typeface);
            headRowTextView.setPadding(10, 10, 10, 10);
            headRowTextView.setTextSize(25);
            headRowTextView.setText(robotsInfo.get(i).getName());
            headRowTextView.setGravity(Gravity.CENTER);

            tableRow.addView(headRowTextView);
        }
        tableLayout.addView(tableRow);

    }

}
