package com.example.alexandr.megaquiz.app;

import android.app.Application;

import com.example.alexandr.megaquiz.quiz.DaggerQuizComponent;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quiz.QuizComponent;


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
