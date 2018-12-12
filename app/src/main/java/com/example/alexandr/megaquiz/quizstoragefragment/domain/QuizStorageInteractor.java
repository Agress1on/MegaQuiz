package com.example.alexandr.megaquiz.quizstoragefragment.domain;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageContract;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageInteractor implements QuizStorageContract.Interactor {
    private Map<String, List<Question>> mBankQuestions;
    private List<QuizStorageItem> mCategoriesNames;

    private BankQuestion mBank;

    public QuizStorageInteractor(BankQuestion bankQuestion) {
        this.mBank = bankQuestion;
    }

    @Override
    public Single<List<QuizStorageItem>> getListOfStorageItem() {
        return Single.just(mBank.getBankQuestion())
                .map(new Function<Map<String, List<Question>>, List<QuizStorageItem>>() {
                    @Override
                    public List<QuizStorageItem> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        List<QuizStorageItem> list = new ArrayList<>();
                        int position = 0;
                        for (Map.Entry<String, List<Question>> entry : stringListMap.entrySet()) {
                            position++;
                            QuizStorageItem item = new QuizStorageItem(position, entry.getKey(), entry.getValue().size());
                            list.add(item);
                        }
                        return list;
                    }
                });
    }

    @Override
    public Single<List<QuizStorageItem>> getListOfStorageItemWithoutEmpty() {
        return Single.just(mBank.getBankQuestion())
                .map(new Function<Map<String, List<Question>>, List<QuizStorageItem>>() {
                    @Override
                    public List<QuizStorageItem> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        List<QuizStorageItem> list = new ArrayList<>();
                        int position = 0;
                        for (Map.Entry<String, List<Question>> entry : stringListMap.entrySet()) {
                            position++;
                            QuizStorageItem item = new QuizStorageItem(position, entry.getKey(), entry.getValue().size());
                            list.add(item);
                        }
                        return list;
                    }
                }).map(new Function<List<QuizStorageItem>, List<QuizStorageItem>>() {
                    @Override
                    public List<QuizStorageItem> apply(List<QuizStorageItem> quizStorageItems) throws Exception {
                        List<QuizStorageItem> list = new ArrayList<>();
                        for (QuizStorageItem quizStorageItem : quizStorageItems) {
                            if (quizStorageItem.getCategorySize() > 0) list.add(quizStorageItem);
                        }
                        return list;
                    }
                });
    }
    /*
    private List<QuizStorageItem> initCategoriesNames() {
        List<QuizStorageItem> list = new ArrayList<>();
        int position = 0;
        for (Map.Entry<String, List<Question>> entry : mBankQuestions.entrySet()) {
            position++;
            QuizStorageItem item = new QuizStorageItem(position, entry.getKey(), entry.getValue().size());
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
        mCategoriesNames = initCategoriesNames();
        List<QuizStorageItem> list = new ArrayList<>();
        for (QuizStorageItem quizStorageItem : mCategoriesNames) {
            if (quizStorageItem.getCategorySize() > 0) {
                list.add(quizStorageItem);
            }
        }
        return list;
    }
    */
}
