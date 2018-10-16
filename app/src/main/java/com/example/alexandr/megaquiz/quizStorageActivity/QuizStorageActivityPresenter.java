package com.example.alexandr.megaquiz.quizStorageActivity;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;

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
        BankQuestion bankQuestion = new BankQuestion();
        this.mModel = new QuizStorageActivityModel(bankQuestion);
    }


    @Override
    public List<String> initCategoriesNames() {
        return mModel.getCategoriesNames();
    }
}
