package com.example.alexandr.megaquiz.quizactivity.presentation;

import com.example.alexandr.megaquiz.quizactivity.QuizActivityContract;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityPresenter implements QuizActivityContract.Presenter {
    private QuizActivityContract.View mView;
    private QuizActivityContract.Interactor mInteractor;

    public QuizActivityPresenter(QuizActivityContract.View view, QuizActivityContract.Interactor interactor) {
        mView = view;
        mInteractor = interactor;
        mView.addQuizFragment();
    }
}
