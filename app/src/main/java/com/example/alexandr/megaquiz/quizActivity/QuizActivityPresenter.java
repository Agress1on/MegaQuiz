package com.example.alexandr.megaquiz.quizActivity;

import com.example.alexandr.megaquiz.app.App;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityPresenter implements QuizActivityContract.Presenter {
    private QuizActivityContract.View mView;
    private QuizActivityContract.Model mModel;

    public QuizActivityPresenter(QuizActivityContract.View view, BankQuestion bankQuestion, QuizActivityContract.Model model) {
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void viewIsReady() {
        mView.setQuestionTVText(mModel.getQuestion(mModel.getCurrentIndex()).getTextQuestion());
        mView.setQuestionCounter("Всего вопросов: " + mModel.getQuizSize());
        mView.setTrueQuestionCounter("Правильных ответов: " + mModel.getTrueQuestionsCount());
        questionNumberCounter();
    }

    @Override
    public void onNextButton() {
        int currentIndex = (mModel.getCurrentIndex() + 1) % mModel.getQuizSize();
        mModel.setCurrentIndex(currentIndex);
        mView.setQuestionTVText(mModel.getQuestion(mModel.getCurrentIndex()).getTextQuestion());
        questionNumberCounter();
        checkAnswerQuestion();
    }

    @Override
    public void onPrevButton() {
        int currentIndex = (mModel.getCurrentIndex() - 1) % mModel.getQuizSize();
        if (currentIndex < 0) currentIndex = 0;
        mModel.setCurrentIndex(currentIndex);
        mView.setQuestionTVText(mModel.getQuestion(mModel.getCurrentIndex()).getTextQuestion());
        questionNumberCounter();
        checkAnswerQuestion();
    }

    @Override
    public void onTrueButton() {
        boolean answer = true;
        boolean realAnswer = mModel.getQuestion(mModel.getCurrentIndex()).isTrueAnswer();
        mModel.putAnswerInStorage(mModel.getCurrentIndex(), answer);
        checkCorrectAnswer(answer, realAnswer);
        checkAnswerQuestion();
        checkFinalOfQuiz();
    }

    @Override
    public void onFalseButton() {
        boolean answer = false;
        boolean realAnswer = mModel.getQuestion(mModel.getCurrentIndex()).isTrueAnswer();
        mModel.putAnswerInStorage(mModel.getCurrentIndex(), answer);
        checkCorrectAnswer(answer, realAnswer);
        checkAnswerQuestion();
        checkFinalOfQuiz();
    }

    private void checkCorrectAnswer(boolean answer, boolean realAnswer) {
        if (answer == realAnswer) {
            showResultAnswerQuestion(true);
            mModel.setTrueQuestionsCount(mModel.getTrueQuestionsCount() + 1);
            mView.setTrueQuestionCounter("Правильных ответов: " + mModel.getTrueQuestionsCount());
        } else {
            showResultAnswerQuestion(false);
        }
    }

    private void showResultAnswerQuestion(boolean answer) {
        if (answer) {
            mView.showToast("Верно!");
        } else {
            mView.showToast("Неверно!");
        }
    }

    private void checkAnswerQuestion() {
        mView.buttonSwitcher(!mModel.isAnsweredThisQuestion(mModel.getCurrentIndex()));
    }

    private void checkFinalOfQuiz() {
        if (mModel.getQuizSize() == mModel.getStorageOfResponseSize())
            mView.showToast("Опрос закончен. Всего вопросов: " + mModel.getQuizSize() + ". Правильных ответов: " + mModel.getTrueQuestionsCount());
    }

    private void questionNumberCounter() {
        mView.setNumberQuestionCounter("Номер вопроса: " + (mModel.getCurrentIndex() + 1));
    }
}
