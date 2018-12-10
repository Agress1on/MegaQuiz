package com.example.alexandr.megaquiz.quizactivity.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizactivity.domain.QuizActivityInteractor;
import com.example.alexandr.megaquiz.quizactivity.presentation.QuizActivityPresenter;
import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 18.11.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizActivityPresenterModule {
    /*
    private QuizActivity mQuizActivity;

    public QuizActivityPresenterModule(QuizActivity quizActivity) {
        mQuizActivity = quizActivity;
    }

    @Provides
    QuizActivityPresenter providePresenter(QuizActivityInteractor interactor) {
        return new QuizActivityPresenter(mQuizActivity, interactor);
    }

    @Provides
    QuizActivityInteractor provideInteractor(BankQuestion bankQuestion) {
        return new QuizActivityInteractor(bankQuestion);
    }

    @Provides
    BankQuestion provideBankQuestion() {
        return new BankQuestion();
    }
    */
}
