package com.example.alexandr.megaquiz.quizactivity.inject;

import com.example.alexandr.megaquiz.quizactivity.QuizActivityContract;
import com.example.alexandr.megaquiz.quizactivity.domain.QuizActivityInteractor;
import com.example.alexandr.megaquiz.quizactivity.presentation.QuizActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 17.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizActivityPresenterModule {

    private QuizActivityContract.View mView;

    public QuizActivityPresenterModule(QuizActivityContract.View view) {
        mView = view;
    }

    @QuizActivityScope
    @Provides
    QuizActivityContract.Presenter providePresenter(QuizActivityContract.Interactor interactor) {
        return new QuizActivityPresenter(mView, interactor);
    }

    @QuizActivityScope
    @Provides
    QuizActivityContract.Interactor provideInteractor() {
        return new QuizActivityInteractor();
    }
}
