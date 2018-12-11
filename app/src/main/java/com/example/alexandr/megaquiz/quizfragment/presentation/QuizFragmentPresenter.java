package com.example.alexandr.megaquiz.quizfragment.presentation;

import android.support.v4.util.ArrayMap;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.quizfragment.Answer;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizFragmentPresenter implements QuizFragmentContract.Presenter {
    private QuizFragmentContract.View mView;
    private QuizFragmentContract.Interactor mInteractor;
    private List<String> mQuestions;
    private int mCurrentIndex;
    private Map<Integer, Answer> mAnswers;

    public QuizFragmentPresenter(QuizFragmentContract.View view, QuizFragmentContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
        this.mAnswers = new ArrayMap<>(); // почитать подробнее потом
        this.mCurrentIndex = 0;
    }

    @Override
    public void initQuestionList(String keyCategory) {
        mQuestions = mInteractor.getQuestions(keyCategory);
    }

    @Override
    public void prepareViewForFirstQuestion() {
        mView.setQuestionTextView(mQuestions.get(mCurrentIndex));
        countNumberOfQuestion();
    }

    @Override
    public void onNextButton() {
        int newIndex = (mCurrentIndex + 1) % mQuestions.size();
        onButtonByIndex(newIndex);
    }

    @Override
    public void onPrevButton() {
        int newIndex = (mCurrentIndex - 1) % mQuestions.size();
        if (newIndex < 0) newIndex = mQuestions.size() - 1;
        onButtonByIndex(newIndex);
    }

    private void onButtonByIndex(int index) {
        mCurrentIndex = index;
        mView.setQuestionTextView(mQuestions.get(mCurrentIndex));
        countNumberOfQuestion();
        checkAnswerQuestion();
    }

    @Override
    public void onAnswer(Answer answer) {
        mAnswers.put(mCurrentIndex, answer);
        checkAnswerQuestion();
        checkFinalOfQuiz();
    }

    private void countNumberOfQuestion() {
        String text = mCurrentIndex + 1 + "/" + mQuestions.size();
        mView.setQuestionCounter(text);
    }

    private void checkAnswerQuestion() {
        boolean isAnswered = mAnswers.containsKey(mCurrentIndex);
        mView.setButtonsEnabled(!isAnswered);
        int flag = Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS;
        if (isAnswered) {
            boolean answer = mAnswers.get(mCurrentIndex).isResult();
            flag = answer ? Constants.PUSH_TRUE_BUTTON : Constants.PUSH_FALSE_BUTTON;
        }
        mView.setCorrectButtonStyle(flag);
    }

    private void checkFinalOfQuiz() {
        int rightAnswers;
        if (mQuestions.size() == mAnswers.size()) {
            rightAnswers = mInteractor.checkQuestions(mAnswers);
            mView.startQuizResultFragment(mQuestions.size(), rightAnswers);
        }
    }
}
