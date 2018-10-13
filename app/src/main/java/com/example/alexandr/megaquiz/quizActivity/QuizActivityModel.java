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
public class QuizActivityModel implements QuizActivityContract.Model {
    //  private Map<String, List<Question>> bankQuestions = new BankQuestion().getBankQuestion();
    private Map<String, List<Question>> bankQuestions;
    private List<Question> mQuestionList;
    private int mCurrentIndex = 0;
    private Map<Integer, Boolean> storageOfResponse = new HashMap<>();
    private int mTrueQuestionsCount = 0;

    public QuizActivityModel(BankQuestion bankQuestion) {
        this.bankQuestions = bankQuestion.getBankQuestion();
        this.mQuestionList = initSelectedQuestionsList(Constants.GENERAL_QUESTIONS); // временная инициализация общими вопросами
    }

    @Override
    public Question getQuestion(int index) {
        return mQuestionList.get(index);
    }

    @Override
    public int getQuizSize() {
        return mQuestionList.size();
    }

    @Override
    public List<Question> initSelectedQuestionsList(String name) {
        for (Map.Entry<String, List<Question>> entry : bankQuestions.entrySet()) {
            if (entry.getKey().equals(name)) {
                mQuestionList = entry.getValue();
            }
        }
        return mQuestionList;
    }

    @Override
    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    @Override
    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

    @Override
    public void putAnswerInStorage(int index, boolean answer) {
        storageOfResponse.put(index, answer);
    }

    @Override
    public boolean isAnsweredThisQuestion(int index) {
        return storageOfResponse.containsKey(index);
    }

    @Override
    public int getStorageOfResponseSize() {
        return storageOfResponse.size();
    }

    @Override
    public int getTrueQuestionsCount() {
        return mTrueQuestionsCount;
    }

    @Override
    public void setTrueQuestionsCount(int trueQuestionsCount) {
        mTrueQuestionsCount = trueQuestionsCount;
    }
}
