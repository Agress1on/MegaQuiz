package com.example.alexandr.megaquiz.quiz;

import com.example.alexandr.megaquiz.app.BankQuestionModule;
import com.example.alexandr.megaquiz.app.QuizActivityModelModule;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Component(modules = {QuizActivityModelModule.class, BankQuestionModule.class})
public interface QuizComponent {
    /*
    BankQuestion getBankQuestion();
    QuizInteractor getQuizActivityModel();
    */
    void injectsMainActivity(QuizActivityView quizActivityView);
}
