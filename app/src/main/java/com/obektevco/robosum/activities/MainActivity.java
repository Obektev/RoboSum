package com.obektevco.robosum.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.obektevco.robosum.R;
import com.obektevco.robosum.obektev_utils.EZToast;
import com.obektevco.robosum.tournamet_utils.NewTournamentDialog;
import com.obektevco.robosum.tournamet_utils.TournamentsGson;
import com.obektevco.robosum.tournamet_utils.TournamentsTable;

public class MainActivity extends AppCompatActivity {
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);

        typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.aldrich);

        setupTitle();

        if (TournamentsGson.getRobotsInfo(this) == null)
            setupWelcomeMessage();
        else
            TournamentsTable.setupTournamentsTable(this);
    }

    private void setupTitle() {
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setOnClickListener(view -> EZToast.toast(getApplicationContext(), getString(R.string.made_by_obektev)));
    }

    private void setupWelcomeMessage() {

        LinearLayout parentLayout = findViewById(R.id.contentLayout);

        TextView noTournamentsTitle = new TextView(getApplicationContext());
        noTournamentsTitle.setPadding(10, 10, 10, 10);
        noTournamentsTitle.setGravity(Gravity.CENTER);
        noTournamentsTitle.setTypeface(typeface);
        noTournamentsTitle.setTextSize(25);
        noTournamentsTitle.setTextColor(getColor(R.color.text_main));
        noTournamentsTitle.setElevation(8);
        noTournamentsTitle.setTranslationZ(8);
        noTournamentsTitle.setText(getString(R.string.no_tournaments_title));

        TextView noTournamentsDescription = new TextView(getApplicationContext());
        noTournamentsDescription.setPadding(10, 10, 10, 10);
        noTournamentsDescription.setGravity(Gravity.CENTER);
        noTournamentsDescription.setTypeface(typeface);
        noTournamentsDescription.setTextSize(20);
        noTournamentsDescription.setTextColor(getColor(R.color.semi_text));
        noTournamentsDescription.setElevation(8);
        noTournamentsDescription.setTranslationZ(8);
        noTournamentsDescription.setText(getString(R.string.no_tournaments_desc));

        ConstraintLayout.LayoutParams buttonParams = new ConstraintLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()));
        buttonParams.setMargins(0, 40, 0, 0);

        AppCompatButton generateButton = new AppCompatButton(getApplicationContext());
        generateButton.setPadding(10, 10, 10, 10);
        generateButton.setText(getString(R.string.generate_tournaments));
        generateButton.setGravity(Gravity.CENTER);
        generateButton.setTypeface(typeface);
        generateButton.setTextSize(22);
        generateButton.setTextColor(getColor(R.color.text_main));
        generateButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.round_shape));
        generateButton.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.button_color)));
        generateButton.setLayoutParams(buttonParams);

        generateButton.setOnClickListener(view -> {
            NewTournamentDialog.openDialogMenu(this, this::recreate);
        });

        parentLayout.addView(noTournamentsTitle);
        parentLayout.addView(noTournamentsDescription);
        parentLayout.addView(generateButton);
    }

}