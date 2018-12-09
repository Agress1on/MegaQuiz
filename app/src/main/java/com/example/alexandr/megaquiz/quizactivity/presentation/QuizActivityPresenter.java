package com.example.alexandr.megaquiz.quizactivity.presentation;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.quizactivity.QuizActivityContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityPresenter implements QuizActivityContract.Presenter {
    private QuizActivityContract.View mView;
    private QuizActivityContract.Interactor mInteractor;
    private List<String> mQuestions;
    private int mCurrentIndex;
    private Map<Integer, Boolean> mAnswers;

    public QuizActivityPresenter(QuizActivityContract.View view, QuizActivityContract.Interactor interactor) {
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
        int flag = Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS;
        if (isAnswered) {
            boolean answer = false;
            flag = Constants.PUSH_FALSE_BUTTON;
            for (Map.Entry<Integer, Boolean> entry : mAnswers.entrySet()) {
                if (entry.getKey() == getCurrentIndex()) answer = entry.getValue();
            }
            if (answer) {
                flag = Constants.PUSH_TRUE_BUTTON;
            }
        }
        mView.setCorrectButtonStyle(flag);
        /*
        boolean isAnswered = mAnswers.containsKey(getCurrentIndex());
        mView.switchButton(!isAnswered);
        if (isAnswered) {
            boolean answer = false;
            for (Map.Entry<Integer, Boolean> entry : mAnswers.entrySet()) {
                if (entry.getKey() == getCurrentIndex()) answer = entry.getValue();
            }
            if (answer) {
                mView.setCorrectButtonStyle(Constants.PUSH_TRUE_BUTTON);
            } else {
                mView.setCorrectButtonStyle(Constants.PUSH_FALSE_BUTTON);
            }
        } else {
            mView.setCorrectButtonStyle(Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS);
        }
        */
    }

    private void checkFinalOfQuiz() {
        int rightAnswers;
        if (mQuestions.size() == mAnswers.size()) {
            rightAnswers = mInteractor.checkQuestions(mAnswers);
            mView.startQuizResultActivity(mQuestions.size(), rightAnswers);
        }
    }
}
