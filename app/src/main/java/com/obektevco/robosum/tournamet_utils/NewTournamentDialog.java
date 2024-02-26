package com.obektevco.robosum.tournamet_utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import com.obektevco.robosum.R;
import com.obektevco.robosum.obektev_utils.EZToast;
import com.obektevco.robosum.obektev_utils.RobotInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class NewTournamentDialog {
    private static Typeface typeface;

    public interface onRobotsInfoGeneratedListener {
        void infoGenerated();
    }
    public static void openDialogMenu(Activity activity, onRobotsInfoGeneratedListener listener) {
        typeface = ResourcesCompat.getFont(activity, R.font.aldrich);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(activity);
        title.setTextSize(23);
        title.setTypeface(typeface);
        title.setGravity(Gravity.CENTER);
        title.setText(activity.getText(R.string.create_new_partisapatements));

        TextView seekBarTextView = new TextView(activity);
        seekBarTextView.setText(activity.getText(R.string.choose_number_of_teams));
        seekBarTextView.setTextSize(15);
        seekBarTextView.setTypeface(typeface);
        seekBarTextView.setGravity(Gravity.CENTER);

        ActionBar.LayoutParams seekBarParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
        seekBarParams.leftMargin = 15;
        seekBarParams.rightMargin = 15;
        seekBarParams.topMargin = 15;

        SeekBar seekBar = new SeekBar(activity);
        seekBar.setMax(10);
        seekBar.setLayoutParams(seekBarParams);
        seekBar.getThumb().setTint(activity.getColor(R.color.text_main));
        seekBar.getProgressDrawable().setColorFilter(activity.getColor(R.color.text_main), PorterDuff.Mode.MULTIPLY);

        TextView currentNumberTextView = new TextView(activity);
        currentNumberTextView.setTextSize(15);
        currentNumberTextView.setTypeface(typeface);
        currentNumberTextView.setGravity(Gravity.CENTER);
        currentNumberTextView.setText(String.format("%s: %s", activity.getString(R.string.current_number), 0));

        LinearLayout buttonLayout = new LinearLayout(activity);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);

        AppCompatButton cancelButton = new AppCompatButton(activity);
        cancelButton.setTypeface(typeface);
        cancelButton.setText(activity.getString(R.string.cancel));
        cancelButton.setTextSize(15);
        cancelButton.setTranslationZ(3);
        cancelButton.setElevation(3);

        AppCompatButton createButton = new AppCompatButton(activity);
        createButton.setTypeface(typeface);
        createButton.setText(activity.getString(R.string.create));
        createButton.setTextSize(15);
        createButton.setElevation(3);
        createButton.setTranslationZ(3);

        buttonLayout.addView(cancelButton);
        buttonLayout.addView(createButton);
        // TODO: REMOVE THIS
        EditText testEditText = new EditText(activity);
        buttonLayout.addView(testEditText);
        testEditText.setVisibility(View.GONE);

        ScrollView scrollView = new ScrollView(activity);
        ViewGroup.LayoutParams scrollViewParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280);
        scrollView.setLayoutParams(scrollViewParams);

        LinearLayout.LayoutParams nameStackParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout namesStackLayout = new LinearLayout(activity);
        namesStackLayout.setOrientation(LinearLayout.VERTICAL);
        namesStackLayout.setGravity(Gravity.CENTER);
        namesStackLayout.setElevation(5);
        namesStackLayout.setTranslationZ(5);
        namesStackLayout.setLayoutParams(nameStackParams);

        scrollView.addView(namesStackLayout);

        layout.addView(seekBarTextView);
        layout.addView(currentNumberTextView);
        layout.addView(seekBar);
        layout.addView(scrollView);
        layout.addView(buttonLayout);

        builder.setCustomTitle(title);
        builder.setView(layout);

        Dialog dialog = builder.create();
        dialog.show();

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.drawable.round_shape);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentNumberTextView.setText(String.format("%s: %s", activity.getString(R.string.current_number), i));
                addInputs(namesStackLayout, i, activity);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        cancelButton.setOnClickListener(view -> dialog.cancel());
        createButton.setOnClickListener(view -> {
            // Summary all info →
            // Save info into gson
            // Generate calendar on MainActivity

            summaryRobotsInfo(activity, summaryInfo -> {
                TournamentsGson.saveRobotsInfo(activity, summaryInfo);

                dialog.cancel();

                listener.infoGenerated(); // Exit to MainActivity
            });

        });
    }

    private static List<EditText> namesInputs = new ArrayList<>();
    private static List<EditText> weightsInputs = new ArrayList<>();

    private static void addInputs(LinearLayout namesStackLayout, int count_, Activity activity) {
        namesStackLayout.removeAllViews();
        namesInputs.clear();
        weightsInputs.clear();
        for (int i = 1; i <= count_; i++) {
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardParams.leftMargin = 10;
            cardParams.rightMargin = 10;
            cardParams.topMargin = 4;
            cardParams.bottomMargin = 4;
            cardParams.gravity = Gravity.CENTER;

            CardView cardView = new CardView(activity);
            cardView.setForegroundGravity(Gravity.CENTER);
            cardView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            cardView.setBackground(AppCompatResources.getDrawable(activity, R.drawable.round_shape));
            cardView.setPadding(5, 5, 5, 5);
            cardView.setLayoutParams(cardParams);
            cardView.setElevation(3);
            cardView.setTranslationZ(3);

            LinearLayout inputLayout = new LinearLayout(activity);
            inputLayout.setOrientation(LinearLayout.HORIZONTAL);
            inputLayout.setGravity(Gravity.CENTER);

            TextView nameNumber = new TextView(activity);
            nameNumber.setTypeface(typeface);
            nameNumber.setTextSize(13);
            nameNumber.setText(String.format("%s: ", i));

            EditText robotNameInput = new EditText(activity);
            robotNameInput.setHint(activity.getString(R.string.robot_name));
            robotNameInput.setTypeface(typeface);
            robotNameInput.setFocusable(true);
            robotNameInput.setFocusableInTouchMode(true);
            robotNameInput.clearFocus();
            //robotNameInput.setMaxLines(1);
            //robotNameInput.setImeOptions(EditorInfo.IME_ACTION_DONE); // TODO: REMOVE THIS ON RELEASE

            EditText robotWeightInput = new EditText(activity);
            robotWeightInput.setHint(activity.getString(R.string.robot_weight));
            robotWeightInput.setFocusable(true);
            robotWeightInput.setFocusableInTouchMode(true);
            robotWeightInput.setTypeface(typeface);
            robotWeightInput.setMaxLines(1);
            robotWeightInput.clearFocus();
            robotWeightInput.setInputType(InputType.TYPE_CLASS_NUMBER);
            //robotWeightInput.setImeOptions(EditorInfo.IME_ACTION_DONE); // TODO: REMOVE THIS ON RELEASE

            inputLayout.addView(nameNumber);
            inputLayout.addView(robotNameInput);
            inputLayout.addView(robotWeightInput);

            cardView.addView(inputLayout);

            namesInputs.add(robotNameInput);
            weightsInputs.add(robotWeightInput);
            namesStackLayout.addView(cardView);
        }
    }

    interface onSumRobotsInfoGotListener {
        void onInfoGot(List<RobotInfo> summaryInfo);
    }
    private static void summaryRobotsInfo(Activity activity, onSumRobotsInfoGotListener listener) {
        List<RobotInfo> summaryInfo = new ArrayList<>();

        // Not enough robots to continue
        if (namesInputs.size() < 2) {
            EZToast.toast(activity, activity.getString(R.string.null_commands));
            return;
        }
        for (int i = 0; i < namesInputs.size(); i++) {
            String name = namesInputs.get(i).getText().toString();
            String weight = weightsInputs.get(i).getText().toString();

            if (name.isEmpty() || weight.isEmpty()) {
                EZToast.toast(activity, activity.getString(R.string.empty_name_or_weight));
                return;
            }

            if (Integer.parseInt(weight) > 1000) {
                EZToast.toast(activity, activity.getString(R.string.too_big_weight));
                return;
            }

            summaryInfo.add(new RobotInfo(name, weight));
        }
        listener.onInfoGot(summaryInfo);
    }

}
