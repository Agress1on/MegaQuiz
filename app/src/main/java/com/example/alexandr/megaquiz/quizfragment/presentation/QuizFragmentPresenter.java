package com.example.alexandr.megaquiz.quizfragment.presentation;

import android.support.v4.util.ArrayMap;
import android.view.View;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.quizfragment.Answer;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizFragmentPresenter implements QuizFragmentContract.Presenter {

    private QuizFragmentContract.View mView;
    private QuizFragmentContract.Interactor mInteractor;
    private QuizFragmentContract.Router mRouter;
    private String mCategoryName;

    private List<String> mQuestions;
    private int mCurrentIndex;
    private Map<Integer, Answer> mAnswers;
    private CompositeDisposable mCompositeDisposable;
    private LinkedHashMap<Integer, Boolean> mMapAnswers;

    public QuizFragmentPresenter(QuizFragmentContract.View view, QuizFragmentContract.Interactor interactor, QuizFragmentContract.Router router, String categoryName) {
        this.mView = view;
        this.mInteractor = interactor;
        this.mRouter = router;
        this.mCategoryName = categoryName;
        //
        this.mQuestions = new ArrayList<>();
        this.mCompositeDisposable = new CompositeDisposable();
        this.mAnswers = new ArrayMap<>(); // почитать подробнее потом
        this.mCurrentIndex = 0;
        this.mMapAnswers = new LinkedHashMap<>();
    }

    /*
    @Override
    public void initCategory(String key) {
        if (mCategoryName.equals("")) {
            mCategoryName = key;
            initQuestionList();
        } else {
            prepareViewForFirstQuestion();
        }
    }
    */

    @Override
    public void initQuestionList() {
        showProgressBar(true);
        Disposable disposable = mInteractor.getQuestions(mCategoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        for (String string : strings) {
                            mQuestions.add(string);
                        }
                        showProgressBar(false);
                        prepareViewForFirstQuestion();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void showProgressBar(boolean flag) {
        int progressBarState = flag ? View.VISIBLE : View.INVISIBLE;
        int viewState = flag ? View.INVISIBLE : View.VISIBLE;
        mView.showProgressBarAndSetViewVisibility(viewState, progressBarState);
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

    private void initAnswersForResultFragment() {
        for (Map.Entry<Integer, Answer> entry : mAnswers.entrySet()) {
            mMapAnswers.put(entry.getKey(), entry.getValue().isResult());
        }
    }

    private void checkFinalOfQuiz() {
        initAnswersForResultFragment();
        final int size = mQuestions.size();
        if (size == mAnswers.size()) {
            showProgressBar(true);
            Disposable disposable = mInteractor.checkQuestions(mCategoryName, mAnswers)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            showProgressBar(false);
                            mView.startQuizResultFragment(size, integer, mMapAnswers);
                        }
                    });
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
