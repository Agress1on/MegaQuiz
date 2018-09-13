package com.example.alexandr.megaquiz.questionActivity;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuestionActivityPresenter implements QuestionContract.Presenter {
    private QuestionContract.View mView;
    private QuestionContract.Model mModel;

    public QuestionActivityPresenter(QuestionContract.View view) {
        this.mView = view;
        this.mModel = new QuestionActivityModel();
    }
}
