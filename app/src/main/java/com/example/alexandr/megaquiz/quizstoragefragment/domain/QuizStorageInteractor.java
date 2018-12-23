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

    private BankQuestion mBankQuestion;

    public QuizStorageInteractor(BankQuestion bankQuestion) {
        this.mBankQuestion = bankQuestion;
    }

    @Override
    public Single<List<QuizStorageItem>> getListsOfStorageItem() {
        return mBankQuestion.getBankQuestionsAndAnswers()
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
}
