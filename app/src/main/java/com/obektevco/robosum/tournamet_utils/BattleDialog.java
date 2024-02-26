package com.obektevco.robosum.tournamet_utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.obektevco.robosum.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BattleDialog {
    public static void openBattleDialog(Activity activity, String leftRobot, String topRobot) {
        Typeface typeface = ResourcesCompat.getFont(activity, R.font.aldrich);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        TextView titleTextView = new TextView(activity);
        titleTextView.setTextColor(activity.getColor(R.color.semi_text));
        //titleTextView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.cell_shape));
        titleTextView.setTypeface(typeface);
        titleTextView.setPadding(15, 15, 15, 15);
        titleTextView.setTextSize(19);
        titleTextView.setText(activity.getString(R.string.battle));
        titleTextView.setGravity(Gravity.CENTER);

        builder.setCustomTitle(titleTextView);

        // Dialog body
        LinearLayout dialogLayout = new LinearLayout(activity);
        dialogLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialogLayout.setGravity(Gravity.CENTER);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);

        TextView versusTextView = new TextView(activity);
        versusTextView.setTextColor(activity.getColor(R.color.text_main));
        versusTextView.setTypeface(typeface);
        versusTextView.setPadding(20, 20, 20, 20);
        versusTextView.setTextSize(19);
        versusTextView.setText(String.format("%s vs %s", leftRobot, topRobot));
        versusTextView.setGravity(Gravity.CENTER);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.gravity = Gravity.START;
        textParams.setMargins(20, 20, 20, 20);

        List<Integer> insertText = new ArrayList<>();
        insertText.add(R.string.first_duel);
        insertText.add(R.string.second_duel);
        insertText.add(R.string.third_duel);

        for (Integer stringResource : insertText) {
            LinearLayout duelLayout = new LinearLayout(activity);
            duelLayout.setGravity(Gravity.CENTER);
            duelLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView duelTextView = new TextView(activity);
            duelTextView.setTypeface(typeface);
            duelTextView.setTextSize(18);
            duelTextView.setTextColor(activity.getColor(R.color.semi_text));
            duelTextView.setPadding(10, 10, 10, 10);
            duelTextView.setLayoutParams(textParams);
            duelTextView.setText(activity.getString(stringResource));

            AppCompatButton leftRobotButton = new AppCompatButton(activity);
            leftRobotButton.setTextSize(10);
            leftRobotButton.setText(leftRobot);

            AppCompatButton topRobotButton = new AppCompatButton(activity);
            topRobotButton.setTextSize(10);
            topRobotButton.setText(topRobot);

            duelLayout.addView(duelTextView);
            duelLayout.addView(leftRobotButton);
            duelLayout.addView(topRobotButton);

            dialogLayout.addView(duelLayout);
        }
        ConstraintLayout.LayoutParams buttonParams = new ConstraintLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, activity.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, activity.getResources().getDisplayMetrics()));
        buttonParams.setMargins(20, 20, 20, 20);


        AppCompatButton applyButton = new AppCompatButton(activity);
        applyButton.setPadding(10, 10, 10, 10);
        applyButton.setText(activity.getString(R.string.apply));
        applyButton.setGravity(Gravity.CENTER);
        applyButton.setTypeface(typeface);
        applyButton.setTextSize(22);
        applyButton.setTextColor(activity.getColor(R.color.text_main));
        applyButton.setBackground(AppCompatResources.getDrawable(activity, R.drawable.round_shape));
        applyButton.setBackgroundTintList(ColorStateList.valueOf(activity.getColor(R.color.button_color)));
        applyButton.setLayoutParams(buttonParams);

        dialogLayout.addView(applyButton);

        builder.setView(dialogLayout);

        Dialog dialog = builder.create();
        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.drawable.round_shape);
    }
}
