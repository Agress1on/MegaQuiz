package com.example.alexandr.megaquiz.quizActivity;

import com.example.alexandr.megaquiz.app.BankQuestionModule;
import com.example.alexandr.megaquiz.app.QuizActivityModelModule;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Component(modules = {QuizActivityModelModule.class, BankQuestionModule.class})
public interface QuizActivityComponent {
    /*
    BankQuestion getBankQuestion();
    QuizActivityModel getQuizActivityModel();
    */
    void injectsMainActivity(QuizActivityView quizActivityView);
}
