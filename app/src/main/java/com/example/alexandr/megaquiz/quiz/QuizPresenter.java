package com.example.alexandr.megaquiz.quiz;

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
        this.mQuestions = interactor.getQuestions(mView.sentToPresenterChoosenCategory());
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
        mView.setQuestionTVText(mQuestions.get(mCurrentIndex));
        mView.setQuestionCounter("Всего вопросов: " + mQuestions.size());
        questionNumberCounter();
    }

    @Override
    public void onNextButton() {
        int newIndex = (getCurrentIndex() + 1) % mQuestions.size();
        setCurrentIndex(newIndex);
        mView.setQuestionTVText(mQuestions.get(mCurrentIndex));
        questionNumberCounter();
        checkAnswerQuestion();
    }

    @Override
    public void onPrevButton() {
        int newIndex = (getCurrentIndex() - 1) % mQuestions.size();
        if (newIndex < 0) newIndex = 0;
        setCurrentIndex(newIndex);
        mView.setQuestionTVText(mQuestions.get(mCurrentIndex));
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
        mView.setNumberQuestionCounter("Номер вопроса: " + (getCurrentIndex() + 1));
    }

    private void checkFinalOfQuiz() {
        int rightAnswers = 0;
        if (mQuestions.size() == mAnswers.size()) {
            rightAnswers = mInteractor.checkQuestions(mAnswers);
            mView.showToast("Опрос закончен. Всего вопросов: " + mQuestions.size() + ". Правильных ответов: " + rightAnswers);
        }

    }

    private void checkAnswerQuestion() {
        boolean isAnswered = mAnswers.containsKey(getCurrentIndex());
        mView.switchButton(!isAnswered);
    }
}
