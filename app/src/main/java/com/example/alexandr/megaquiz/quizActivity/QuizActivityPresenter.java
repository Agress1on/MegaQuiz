package com.example.alexandr.megaquiz.quizActivity;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityPresenter implements QuizContract.Presenter {
    private QuizContract.View mView;
    private QuizContract.Model mModel;

    public QuizActivityPresenter(QuizContract.View view) {
        this.mView = view;
        this.mModel = new QuizActivityModel();
    }
}
