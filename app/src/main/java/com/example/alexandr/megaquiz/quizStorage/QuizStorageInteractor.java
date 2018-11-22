package com.example.alexandr.megaquiz.quizStorage;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageInteractor implements QuizStorageContract.Interactor {
    private Map<String, List<Question>> mBankQuestions;
    private List<QuizStorageItem> mCategoriesNames;

    public QuizStorageInteractor(BankQuestion bankQuestion) {
        this.mBankQuestions = bankQuestion.getBankQuestion();
        this.mCategoriesNames = initCategoriesNames();
    }

    private List<QuizStorageItem> initCategoriesNames() {
        List<QuizStorageItem> list = new ArrayList<>();
        for (Map.Entry<String, List<Question>> entry : mBankQuestions.entrySet()) {
            QuizStorageItem item = new QuizStorageItem(entry.getKey(), entry.getValue().size());
            list.add(item);
        }
        return list;
    }

    @Override
    public List<QuizStorageItem> getListOfStorageItem() {
        return mCategoriesNames;
    }

    @Override
    public List<QuizStorageItem> getListOfStorageItemWithoutEmpty() {
        List<QuizStorageItem> list = new ArrayList<>();
        for (QuizStorageItem quizStorageItem : mCategoriesNames) {
            if (quizStorageItem.getCategorySize() > 0) {
                list.add(quizStorageItem);
            }
        }
        return list;
    }
}
