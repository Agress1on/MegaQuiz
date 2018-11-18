package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quiz.QuizInteractor;
import com.example.alexandr.megaquiz.quiz.QuizPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 18.11.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizPresenterModule2 {
    private QuizView mQuizView;

    public QuizPresenterModule2(QuizView quizView) {
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
