package com.example.alexandr.megaquiz.quizfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;
import com.example.alexandr.megaquiz.quizfragment.domain.QuizFragmentInteractor;
import com.example.alexandr.megaquiz.quizfragment.presentation.QuizFragmentPresenter;

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
    private QuizFragmentContract.Router mRouter;

    public QuizFragmentPresenterModule(QuizFragmentContract.View quizFragment, QuizFragmentContract.Router router) {
        mView = quizFragment;
        mRouter = router;
    }

    @QuizFragmentScope
    @Provides
    QuizFragmentContract.Presenter providePresenter(QuizFragmentContract.Interactor quizFragmentInteractor) {
        return new QuizFragmentPresenter(mView, quizFragmentInteractor, mRouter);
    }

    @QuizFragmentScope
    @Provides
    QuizFragmentContract.Interactor provideInteractor(BankQuestion bankQuestion) {
        return new QuizFragmentInteractor(bankQuestion);
    }
}
