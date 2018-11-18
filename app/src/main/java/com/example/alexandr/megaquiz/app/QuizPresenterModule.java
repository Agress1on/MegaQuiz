package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.quiz.QuizActivityView;
import com.example.alexandr.megaquiz.quiz.QuizContract;
import com.example.alexandr.megaquiz.quiz.QuizInteractor;
import com.example.alexandr.megaquiz.quiz.QuizPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 17.11.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizPresenterModule {

    @Provides
    QuizPresenter providePresenter(QuizActivityView view, QuizInteractor interactor) {
        return new QuizPresenter(view, interactor);
    }
}
