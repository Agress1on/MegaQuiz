package com.example.alexandr.megaquiz.quizActivity;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityModel implements QuizContract.Model {
    private Map<String, List<Question>> bankQuestion = new BankQuestion().getBankQuestion();
    private List<Question> mQuestionList = initSelectedQuestionsList(Constants.GENERAL_QUESTIONS); // временная инициализация общими вопросами
    private int mCurrentIndex = 0;
    private Map<Integer, Boolean> resultQuiz = new HashMap<>();
    private int mTrueQuestionsCount = 0;

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

    public List<Question> getQuestionList() {
        return mQuestionList;
    }

    public List<Question> initSelectedQuestionsList(String name) {
        for (Map.Entry<String, List<Question>> entry : bankQuestion.entrySet()) {
            if (entry.getKey().equals(name)) {
                mQuestionList = entry.getValue();
            }
        }
        return mQuestionList;
    }

    public Map<Integer, Boolean> getResultQuiz() {
        return resultQuiz;
    }

    public void setResultQuiz(Map<Integer, Boolean> resultQuiz) {
        this.resultQuiz = resultQuiz;
    }

    public int getTrueQuestionsCount() {
        return mTrueQuestionsCount;
    }

    public void setTrueQuestionsCount(int trueQuestionsCount) {
        mTrueQuestionsCount = trueQuestionsCount;
    }
}
