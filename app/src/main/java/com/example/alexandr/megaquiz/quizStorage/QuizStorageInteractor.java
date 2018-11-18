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
public class QuizStorageInteractor implements QuizStorageContract.Model {
    private Map<String, List<Question>> bankQuestions;
    private List<String> categoriesNames;

    public QuizStorageInteractor(BankQuestion bankQuestion) {
        this.bankQuestions = bankQuestion.getBankQuestion();
        this.categoriesNames = new ArrayList<>();
        initCategoriesNames();
    }

    @Override
    public Map<String, List<Question>> getBankQuestion() {
        return bankQuestions;
    }

    private void initCategoriesNames() {
        for (Map.Entry<String, List<Question>> entry : bankQuestions.entrySet()) {
            categoriesNames.add(entry.getKey());
        }
    }

    @Override
    public List<String> getCategoriesNames() {
        return categoriesNames;
    }
}
