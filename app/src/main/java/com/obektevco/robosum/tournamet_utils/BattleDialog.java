package com.obektevco.robosum.tournamet_utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import com.obektevco.robosum.R;

public class BattleDialog {
    public static void openBattleDialog(Activity activity, String leftRobot, String topRobot) {
        Typeface typeface = ResourcesCompat.getFont(activity, R.font.aldrich);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        TextView titleTextView = new TextView(activity);
        titleTextView.setTextColor(activity.getColor(R.color.semi_text));
        titleTextView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.cell_shape));
        titleTextView.setTypeface(typeface);
        titleTextView.setPadding(15, 15, 15, 15);
        titleTextView.setTextSize(19);
        titleTextView.setText(activity.getString(R.string.battle));
        titleTextView.setGravity(Gravity.CENTER);

        builder.setCustomTitle(titleTextView);

        Dialog dialog = builder.create();
        dialog.show();
    }
}
