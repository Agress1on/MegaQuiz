package com.example.alexandr.megaquiz;

import android.util.Log;

/**
 * Created by Alexandr Mikhalev on 20.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class Logger {
    public void d(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }
}
