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
    public List<QuizStorageItem> getCategoriesNamesForView() {
        return mInteractor.getListOfStorageItem();
    }

    @Override
    public List<QuizStorageItem> getCategoriesNamesForViewWithoutEmpty() {
        return mInteractor.getListOfStorageItemWithoutEmpty();
    }

    @Override
    public void onCheckBoxClick(boolean isChecked) {
        List<QuizStorageItem> list = mInteractor.getListOfStorageItemWithoutEmpty();
        String text = "Показать пустые категории";
        if (isChecked) {
            list = mInteractor.getListOfStorageItem();
            text = "Скрыть пустые категории";
        }
        mView.updateUI(list, text);
    }

    @Override
    public void onClick(String key) {
        mView.startActivityQuizView(key);
    }
}
