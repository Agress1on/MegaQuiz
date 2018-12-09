package com.example.alexandr.megaquiz.quizactivity.inject;

import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Component(modules = {QuizActivityPresenterModule.class})
public interface QuizActivityComponent {
    void injectsQuizActivity(QuizActivity quizActivity);
}
