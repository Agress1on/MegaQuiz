package com.example.alexandr.megaquiz.quizfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
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
    private QuizFragment mQuizFragment;

    public QuizFragmentPresenterModule(QuizFragment quizFragment) {
        mQuizFragment = quizFragment;
    }

    @Provides
    QuizFragmentPresenter providePresenter(QuizFragmentInteractor quizFragmentInteractor) {
        return new QuizFragmentPresenter(mQuizFragment, quizFragmentInteractor);
    }

    @Provides
    QuizFragmentInteractor provideInteractor(BankQuestion bankQuestion) {
        return new QuizFragmentInteractor(bankQuestion);
    }

    @Provides
    BankQuestion provideBankQuestion() {
        return new BankQuestion();
    }
}
