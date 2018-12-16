package com.example.alexandr.megaquiz.quizfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;
import com.example.alexandr.megaquiz.quizfragment.domain.QuizFragmentInteractor;
import com.example.alexandr.megaquiz.quizfragment.presentation.QuizFragmentPresenter;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizFragmentPresenterModule {
    private QuizFragmentContract.View mView;

    public QuizFragmentPresenterModule(QuizFragmentContract.View quizFragment) {
        mView = quizFragment;
    }

    @QuizFragmentScope
    @Provides
    QuizFragmentContract.Presenter providePresenter(QuizFragmentContract.Interactor quizFragmentInteractor) {
        return new QuizFragmentPresenter(mView, quizFragmentInteractor);
    }

    @QuizFragmentScope
    @Provides
    QuizFragmentContract.Interactor provideInteractor(BankQuestion bankQuestion) {
        return new QuizFragmentInteractor(bankQuestion);
    }
}
