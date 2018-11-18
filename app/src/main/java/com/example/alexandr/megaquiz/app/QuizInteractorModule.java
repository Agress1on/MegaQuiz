package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizContract;
import com.example.alexandr.megaquiz.quiz.QuizInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 17.11.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizInteractorModule {

    @Provides
    BankQuestion provideBankQuestion() {
        return new BankQuestion();
    }

    @Provides
    QuizInteractor provideQuizActivityInteractor(BankQuestion bankQuestion) {
        return new QuizInteractor(bankQuestion);
    }


}
