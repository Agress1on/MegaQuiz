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
    private Map<String, List<Question>> bankQuestions;
    private List<String> categoriesNames;

    public QuizStorageActivityModel(BankQuestion bankQuestion) {
        this.bankQuestions = bankQuestion.getBankQuestion();
        this.categoriesNames = new ArrayList<>();
        initCategoriesNames();
    }

    @Override
    public Map<String, List<Question>> getBankQuestion() {
        return bankQuestions;
    }

    private void initCategoriesNames() {
        List<String> mCat = new ArrayList<>();
        for (Map.Entry<String, List<Question>> entry : bankQuestions.entrySet()) {
            categoriesNames.add(entry.getKey());
        }
    }

    @Override
    public List<String> getCategoriesNames() {
        return categoriesNames;
    }
}
