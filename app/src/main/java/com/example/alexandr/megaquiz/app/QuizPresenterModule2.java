package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizActivityView;
import com.example.alexandr.megaquiz.quiz.QuizInteractor;
import com.example.alexandr.megaquiz.quiz.QuizPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 18.11.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizPresenterModule2 {
    private QuizActivityView mQuizActivityView;

    public QuizPresenterModule2(QuizActivityView quizActivityView) {
        mQuizActivityView = quizActivityView;
    }

    @Provides
    QuizPresenter providePresenter(QuizInteractor interactor) {
        return new QuizPresenter(mQuizActivityView, interactor);
    }

    @Provides
    QuizInteractor provideInteractor(BankQuestion bankQuestion) {
        return new QuizInteractor(bankQuestion);
    }

    @Provides
    BankQuestion provideBankQuestion() {
        return new BankQuestion();
    }
}
