package com.obektevco.robosum.obektev_utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.obektevco.robosum.R;

import java.util.Objects;

public class WinnerDialog {
    private static Typeface typeface;
    public static void openWinnerDialog(Activity activity, String winnerName) {
        typeface = ResourcesCompat.getFont(activity, R.font.aldrich);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(20, 20, 20, 20);

        TextView title = new TextView(activity);
        title.setTextSize(23);
        title.setTypeface(typeface);
        title.setGravity(Gravity.CENTER);
        title.setText(activity.getText(R.string.winner));

        builder.setCustomTitle(title);

        TextView winnerTextView = new TextView(activity);
        winnerTextView.setTextSize(24);
        winnerTextView.setTypeface(typeface);
        winnerTextView.setLineSpacing(1, 2);
        winnerTextView.setGravity(Gravity.CENTER);
        winnerTextView.setText(activity.getText(R.string.competition_ended) + winnerName);

        ConstraintLayout.LayoutParams buttonParams = new ConstraintLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, activity.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, activity.getResources().getDisplayMetrics()));
        buttonParams.setMargins(0, 40, 0, 0);


        AppCompatButton okButton = new AppCompatButton(activity);
        okButton.setPadding(10, 10, 10, 10);
        okButton.setText(activity.getString(R.string.cool));
        okButton.setGravity(Gravity.CENTER);
        okButton.setTypeface(typeface);
        okButton.setTextSize(22);
        okButton.setTextColor(activity.getColor(R.color.text_main));
        okButton.setBackground(AppCompatResources.getDrawable(activity, R.drawable.round_shape));
        okButton.setBackgroundTintList(ColorStateList.valueOf(activity.getColor(R.color.button_color)));
        okButton.setLayoutParams(buttonParams);

        layout.addView(winnerTextView);
        layout.addView(okButton);

        builder.setView(layout);

        Dialog dialog = builder.create();
        dialog.show();

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.drawable.round_shape);

        okButton.setOnClickListener(view -> dialog.cancel());
    }
}
