package com.example.alexandr.megaquiz.quizfragment.domain;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizFragmentInteractor implements QuizFragmentContract.Interactor {
    private Map<String, List<Question>> mBankQuestions;
    private List<Question> mListQuestions;
    private List<String> mStringQuestion;
    private List<Boolean> mTrueAnswers;
    private int mRightAnswersCounter;

    public QuizFragmentInteractor(BankQuestion bankQuestion) {
        this.mBankQuestions = bankQuestion.getBankQuestion();
        this.mListQuestions = new ArrayList<>();
        this.mStringQuestion = new ArrayList<>();
        this.mTrueAnswers = new ArrayList<>();
        this.mRightAnswersCounter = 0;
    }

    private void initStringListAndTrueAnswers(String key) {
        mListQuestions = mBankQuestions.get(key);
        for (Question question : mListQuestions) {
            mStringQuestion.add(question.getTextQuestion());
            mTrueAnswers.add(question.isTrueAnswer());
        }
    }

    @Override
    public List<String> getQuestions(String key) {
        initStringListAndTrueAnswers(key);
        return mStringQuestion;
    }

    @Override
    public int checkQuestions(Map<Integer, Boolean> answers) {
        for (Map.Entry<Integer, Boolean> entry : answers.entrySet()) {
            if (mTrueAnswers.get(entry.getKey()) == entry.getValue()) mRightAnswersCounter++;
        }
        return mRightAnswersCounter;
    }
}
