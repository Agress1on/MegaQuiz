package com.example.alexandr.megaquiz.quizresultfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultFragmentContract;
import com.example.alexandr.megaquiz.quizresultfragment.domain.QuizResultFragmentInteractor;
import com.example.alexandr.megaquiz.quizresultfragment.presentation.QuizResultFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizResultFragmentPresenterModule {

    private QuizResultFragmentContract.View mView;

    public QuizResultFragmentPresenterModule(QuizResultFragmentContract.View view) {
        mView = view;
    }

    @QuizResultFragmentScope
    @Provides
    QuizResultFragmentContract.Presenter providePresenter(QuizResultFragmentContract.Interactor interactor) {
        return new QuizResultFragmentPresenter(mView, interactor);
    }

    @QuizResultFragmentScope
    @Provides
    QuizResultFragmentContract.Interactor provideInteractor(BankQuestion bankQuestion) {
        return new QuizResultFragmentInteractor(bankQuestion);
    }
}
