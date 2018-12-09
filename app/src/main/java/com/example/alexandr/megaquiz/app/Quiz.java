package com.example.alexandr.megaquiz.app;

import android.app.Application;

import com.example.alexandr.megaquiz.quiz.inject.DaggerQuizComponent;
import com.example.alexandr.megaquiz.quiz.inject.QuizComponent;
import com.example.alexandr.megaquiz.quiz.inject.QuizPresenterModule;
import com.example.alexandr.megaquiz.quiz.view.QuizView;


/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
public class Quiz extends Application {
    private static QuizComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = DaggerQuizComponent.builder().quizPresenterModule(new QuizPresenterModule(new QuizView())).build();
    }

    public static QuizComponent getComponent() {
        return sComponent;
    }
}
