package com.example.alexandr.megaquiz.quizStorageActivity;

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
public class QuizStorageActivityModel implements QuizStorageContract.Model {
    private Map<String, List<Question>> bankQuestion = new BankQuestion().getBankQuestion();
    private List<String> mCategoriesNames;

    @Override
    public Map<String, List<Question>> getBankQuestion() {
        return bankQuestion;
    }

    @Override
    public List<String> getCategoriesNames() {
        List<String> mCat = new ArrayList<>();
        for (Map.Entry<String, List<Question>> entry : bankQuestion.entrySet()) {
            mCat.add(entry.getKey());
        }
        return mCat;
    }
}
