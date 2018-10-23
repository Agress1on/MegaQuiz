package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 23.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class BankQuestionModule {
    @Provides
    BankQuestion provideBankQuestion() {
        return new BankQuestion();
    }
}
