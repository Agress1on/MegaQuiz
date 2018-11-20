package com.example.alexandr.megaquiz.quiz;

import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizInteractor implements QuizContract.Interactor {
    private Map<String, List<Question>> bankQuestions;
    private List<Question> listQuestions;

    private List<String> stringQuestion;
    private List<Boolean> trueAnswers;
    private int rightAnswers;

    public QuizInteractor(BankQuestion bankQuestion) {
        this.bankQuestions = bankQuestion.getBankQuestion();
        this.listQuestions = new ArrayList<>();
        this.stringQuestion = new ArrayList<>();
        this.trueAnswers = new ArrayList<>();
        this.rightAnswers = 0;
    }

    private void initStringListAndTrueAnswers(String key) {
        listQuestions = bankQuestions.get(key);
        for (Question question : listQuestions) {
            stringQuestion.add(question.getTextQuestion());
            trueAnswers.add(question.isTrueAnswer());
        }
    }

    @Override
    public List<String> getQuestions(String key) {
        initStringListAndTrueAnswers(key);
        return stringQuestion;
    }

    @Override
    public int checkQuestions(Map<Integer, Boolean> answers) {
        for (Map.Entry<Integer, Boolean> entry : answers.entrySet()) {
            if (trueAnswers.get(entry.getKey()) == entry.getValue()) rightAnswers++;
        }
        return rightAnswers;
    }

}
