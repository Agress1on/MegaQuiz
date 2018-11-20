package com.example.alexandr.megaquiz.quizStorage;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStoragePresenter implements QuizStorageContract.Presenter {
    private QuizStorageView mView;
    private QuizStorageInteractor mModel;

    public QuizStoragePresenter(QuizStorageView view) {
        this.mView = view;
        BankQuestion bankQuestion = new BankQuestion();
        this.mModel = new QuizStorageInteractor(bankQuestion);
    }

    @Override
    public List<String> initCategoriesNames() {
        return mModel.getCategoriesNames();
    }

    @Override
    public void onClick(String key) {
        mView.startActivityQuizView(key);
    }
}
