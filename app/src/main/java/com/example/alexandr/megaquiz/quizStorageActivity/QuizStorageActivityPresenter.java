package com.example.alexandr.megaquiz.quizStorageActivity;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageActivityPresenter implements QuizStorageContract.Presenter {
    private QuizStorageActivityView mView;
    private QuizStorageActivityModel mModel;

    public QuizStorageActivityPresenter(QuizStorageActivityView view) {
        this.mView = view;
        this.mModel = new QuizStorageActivityModel();
    }

    @Override
    public List<String> getCategoriesNames() {
        return mModel.getCategoriesNames();
    }
}
