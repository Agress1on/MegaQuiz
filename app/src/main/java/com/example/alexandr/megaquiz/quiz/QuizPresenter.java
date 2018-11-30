package com.example.alexandr.megaquiz.quiz;

import com.example.alexandr.megaquiz.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizPresenter implements QuizContract.Presenter {
    private QuizContract.View mView;
    private QuizContract.Interactor mInteractor;
    private List<String> mQuestions;
    private int mCurrentIndex;
    private Map<Integer, Boolean> mAnswers;

    public QuizPresenter(QuizContract.View view, QuizContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
        this.mQuestions = interactor.getQuestions(mView.sentToPresenterSelectedCategory());
        this.mAnswers = new HashMap<>();
        this.mCurrentIndex = 0;
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

    @Override
    public void prepareViewForFirstQuestion() {
        mView.setQuestionTextView(mQuestions.get(mCurrentIndex));
        questionNumberCounter();
    }

    @Override
    public void onNextButton() {
        int newIndex = (getCurrentIndex() + 1) % mQuestions.size();
        setCurrentIndex(newIndex);
        mView.setQuestionTextView(mQuestions.get(mCurrentIndex));
        questionNumberCounter();
        checkAnswerQuestion();
    }

    @Override
    public void onPrevButton() {
        int newIndex = (getCurrentIndex() - 1) % mQuestions.size();
        if (newIndex < 0) newIndex = mQuestions.size() - 1;
        setCurrentIndex(newIndex);
        mView.setQuestionTextView(mQuestions.get(mCurrentIndex));
        questionNumberCounter();
        checkAnswerQuestion();
    }

    @Override
    public void onTrueButton() {
        boolean answer = true;
        mAnswers.put(getCurrentIndex(), answer);
        checkAnswerQuestion();
        checkFinalOfQuiz();
    }

    @Override
    public void onFalseButton() {
        boolean answer = false;
        mAnswers.put(getCurrentIndex(), answer);
        checkAnswerQuestion();
        checkFinalOfQuiz();
    }

    private void questionNumberCounter() {
        String text = getCurrentIndex() + 1 + "/" + mQuestions.size();
        mView.setQuestionCount(text);
    }

    private void checkAnswerQuestion() {
        boolean isAnswered = mAnswers.containsKey(getCurrentIndex());
        mView.switchButton(!isAnswered);
        if (isAnswered) {
            boolean answer = false;
            for (Map.Entry<Integer, Boolean> entry : mAnswers.entrySet()) {
                if (entry.getKey() == getCurrentIndex()) answer = entry.getValue();
            }
            if (answer) {
                mView.setNeedButtonStyle(Constants.PUSH_TRUE_BUTTON);
            } else {
                mView.setNeedButtonStyle(Constants.PUSH_FALSE_BUTTON);
            }
        } else {
            mView.setNeedButtonStyle(Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS);
        }
    }

    private void checkFinalOfQuiz() {
        int rightAnswers = 0;
        if (mQuestions.size() == mAnswers.size()) {
            rightAnswers = mInteractor.checkQuestions(mAnswers);
            mView.startQuizResultActivity(mQuestions.size(), rightAnswers);
        }
    }
}
