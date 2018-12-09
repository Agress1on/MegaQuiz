package com.example.alexandr.megaquiz.quiz.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.interactor.QuizInteractor;
import com.example.alexandr.megaquiz.quiz.presentation.QuizPresenter;
import com.example.alexandr.megaquiz.quiz.view.QuizView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 18.11.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizPresenterModule {
    private QuizView mQuizView;

    public QuizPresenterModule(QuizView quizView) {
        mQuizView = quizView;
    }

    @Provides
    QuizPresenter providePresenter(QuizInteractor interactor) {
        return new QuizPresenter(mQuizView, interactor);
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
