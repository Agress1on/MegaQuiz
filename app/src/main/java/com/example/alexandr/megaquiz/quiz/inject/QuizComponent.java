package com.example.alexandr.megaquiz.quiz.inject;

import com.example.alexandr.megaquiz.app.QuizPresenterModule;
import com.example.alexandr.megaquiz.quiz.view.QuizView;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Component(modules = {QuizPresenterModule.class})
public interface QuizComponent {
    void injectsQuizActivity(QuizView quizView);
}
