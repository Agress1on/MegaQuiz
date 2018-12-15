package com.example.alexandr.megaquiz.app;

import android.app.Application;

import com.example.alexandr.megaquiz.quizfragment.inject.DaggerQuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;


/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
public class Quiz extends Application {

    private static QuizFragmentComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = DaggerQuizFragmentComponent.builder()
                .quizFragmentPresenterModule(new QuizFragmentPresenterModule(new QuizFragment())).build();
    }

    public static QuizFragmentComponent getComponent() {
        return sComponent;
    }
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
