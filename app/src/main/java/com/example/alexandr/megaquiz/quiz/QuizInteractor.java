package com.example.alexandr.megaquiz.quiz;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.bankQuestion.Question;

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
    private List<Boolean> generalTrueAnswers;
    private int rightAnswers;

    public QuizInteractor(BankQuestion bankQuestion) {
        this.bankQuestions = bankQuestion.getBankQuestion();
        this.listQuestions = bankQuestions.get(Constants.GENERAL_QUESTIONS);
        this.generalTrueAnswers = bankQuestion.getGeneralTrueAnswers();
        this.rightAnswers = 0;
    }

    @Override
    public List<Question> getQuestions() {
        return listQuestions;
    }

    @Override
    public int checkQuestions(Map<Integer, Boolean> answers) {
        for (Map.Entry<Integer, Boolean> entry : answers.entrySet()) {
            if (generalTrueAnswers.get(entry.getKey()) == entry.getValue()) rightAnswers++;
        }
        return rightAnswers;
    }

}
