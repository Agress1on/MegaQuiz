package com.example.alexandr.megaquiz.quizResult;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultPresenter implements QuizResultContract.Presenter {
    private QuizResultContract.View mView;
    private QuizResultContract.Interactor mInteractor;

    public QuizResultPresenter(QuizResultContract.View view, QuizResultContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }
}
