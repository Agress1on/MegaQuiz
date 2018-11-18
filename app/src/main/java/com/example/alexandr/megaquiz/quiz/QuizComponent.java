package com.example.alexandr.megaquiz.quiz;

import com.example.alexandr.megaquiz.app.QuizPresenterModule2;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Component(modules = {QuizPresenterModule2.class})
//@Component(modules = {QuizModule.class})
public interface QuizComponent {
    /*
    BankQuestion getBankQuestion();
    QuizInteractor getQuizActivityModel();
    */
    void injectsQuizActivity(QuizView quizView);
}
