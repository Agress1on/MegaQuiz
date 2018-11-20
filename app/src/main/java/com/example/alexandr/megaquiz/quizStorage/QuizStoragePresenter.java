package com.example.alexandr.megaquiz.quizStorage;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStoragePresenter implements QuizStorageContract.Presenter {
    private QuizStorageContract.View mView;
    private QuizStorageContract.Interactor mInteractor;

    public QuizStoragePresenter(QuizStorageContract.View view, QuizStorageContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public List<String> getCategoriesNamesForView() {
        return mInteractor.getCategoriesNames();
    }

    @Override
    public void onClick(String key) {
        mView.startActivityQuizView(key);
    }
}
