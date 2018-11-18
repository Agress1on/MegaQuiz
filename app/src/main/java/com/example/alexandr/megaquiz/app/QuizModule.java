package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizActivityView;
import com.example.alexandr.megaquiz.quiz.QuizContract;
import com.example.alexandr.megaquiz.quiz.QuizInteractor;
import com.example.alexandr.megaquiz.quiz.QuizPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizModule {

    private QuizContract.View mView;


    public QuizModule(QuizContract.View view) {
        mView = view;
    }

    @Provides
    BankQuestion provideBankQuestion() {
        return new BankQuestion();
    }

    @Provides
    QuizContract.Interactor provideQuizActivityInteractor(BankQuestion bankQuestion) {
        return new QuizInteractor(bankQuestion);
    }


    @Provides
    QuizContract.View provideQuizActivityView() {
        return new QuizActivityView();
    }


    @Provides
    QuizContract.Presenter providePresenter(QuizContract.View view, QuizContract.Interactor interactor) {
        return new QuizPresenter(view, interactor);
    }
}
