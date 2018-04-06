package com.exe.paradox.util;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {
    public static boolean getFirstRun(Context context) {
        return context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("doki_doki", true);
    }

    public static void setFirstRun(Context context) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("doki_doki", false).apply();
    }

    public static boolean getNotification(Context context) {
        return context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("notify", true);
    }

    public static void setNotification(Context context, boolean bool) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("notify", bool).apply();
    }
}
