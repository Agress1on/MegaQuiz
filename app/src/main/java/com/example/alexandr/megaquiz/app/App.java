package com.example.alexandr.megaquiz.app;

import android.app.Application;

import com.example.alexandr.megaquiz.quizActivity.DaggerQuizActivityComponent;
import com.example.alexandr.megaquiz.quizActivity.QuizActivityComponent;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
public class App extends Application {
    private static QuizActivityComponent sComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = DaggerQuizActivityComponent.create();
    }

    public static QuizActivityComponent getComponent() {
        return sComponent;
    }
}
