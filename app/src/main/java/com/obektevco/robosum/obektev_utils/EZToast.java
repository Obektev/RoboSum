package com.obektevco.robosum.obektev_utils;

import android.content.Context;
import android.widget.Toast;

public class EZToast {
    public static void toast(Context context, String message) {
        Toast toast = new Toast(context);
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
