package com.example.alexandr.megaquiz.app;

import android.app.Application;

import com.example.alexandr.megaquiz.quizactivity.inject.DaggerQuizActivityComponent;
import com.example.alexandr.megaquiz.quizactivity.inject.QuizActivityComponent;
import com.example.alexandr.megaquiz.quizactivity.inject.QuizActivityPresenterModule;
import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;


/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
public class AppModule extends Application {
    /*
    private static QuizActivityComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = DaggerQuizActivityComponent.builder().quizActivityPresenterModule(new QuizActivityPresenterModule(new QuizActivity())).build();
    }

    public static QuizActivityComponent getComponent() {
        return sComponent;
    }
    */
}
