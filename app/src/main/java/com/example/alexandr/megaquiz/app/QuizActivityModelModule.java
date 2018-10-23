package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quizActivity.QuizActivityModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizActivityModelModule {
    @Provides
    QuizActivityModel provideQuizActivityModel(BankQuestion bankQuestion) {
        return new QuizActivityModel(bankQuestion);
    }
}
