package com.example.alexandr.megaquiz.quizfragment.presentation;

import android.support.v4.util.ArrayMap;
import android.util.Pair;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.quizfragment.Answer;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;

import java.util.ArrayList;
import java.util.HashMap;
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

    private List<String> mQuestionsList;
    private int mCurrentIndex;
    private Map<Integer, Answer> mUserAnswers;
    private CompositeDisposable mCompositeDisposable;
    private HashMap<Integer, Boolean> mMapUserAnswersForResultFragment;

    public QuizFragmentPresenter(QuizFragmentContract.Interactor interactor, String categoryName) {
        this.mInteractor = interactor;
        this.mCategoryName = categoryName;
        //
        this.mQuestionsList = new ArrayList<>();
        this.mCompositeDisposable = new CompositeDisposable();
        this.mUserAnswers = new ArrayMap<>(); // почитать подробнее потом
        this.mCurrentIndex = 0;
        this.mMapUserAnswersForResultFragment = new HashMap<>();
    }

    @Override
    public void attachView(QuizFragmentContract.View view, QuizFragmentContract.Router router) {
        mView = view;
        mRouter = router;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onStart() {
        if (!mQuestionsList.isEmpty()) {
            prepareViewForFirstQuestion();
            checkAnswerQuestion();
            return;
        }
        initQuestionList(mCategoryName);
    }

    private void initQuestionList(String categoryName) {
        mView.showLoading();
        Disposable disposable;
        if (categoryName.equals("")) {
            disposable = mInteractor.getQuestionsForRandom()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Pair<String, List<String>>>() {
                        @Override
                        public void accept(Pair<String, List<String>> stringListPair) throws Exception {
                            mCategoryName = stringListPair.first;
                            mQuestionsList = stringListPair.second;
                            mView.hideLoading();
                            prepareViewForFirstQuestion();
                        }
                    });
        } else {
            disposable = mInteractor.getQuestions(mCategoryName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<String>>() {
                        @Override
                        public void accept(List<String> strings) throws Exception {
                            mQuestionsList.addAll(strings);
                            mView.hideLoading();
                            prepareViewForFirstQuestion();
                        }
                    });
        }
        mCompositeDisposable.add(disposable);
    }

    private void prepareViewForFirstQuestion() {
        mView.setQuestionTextView(mQuestionsList.get(mCurrentIndex));
        countNumberOfQuestion();
    }

    @Override
    public void onNextButton() {
        int newIndex = (mCurrentIndex + 1) % mQuestionsList.size();
        onButtonByIndex(newIndex);
    }

    @Override
    public void onPrevButton() {
        int newIndex = (mCurrentIndex - 1) % mQuestionsList.size();
        if (newIndex < 0) newIndex = mQuestionsList.size() - 1;
        onButtonByIndex(newIndex);
    }

    private void onButtonByIndex(int index) {
        mCurrentIndex = index;
        mView.setQuestionTextView(mQuestionsList.get(mCurrentIndex));
        countNumberOfQuestion();
        checkAnswerQuestion();
    }

    @Override
    public void onAnswer(Answer answer) {
        mUserAnswers.put(mCurrentIndex, answer);
        checkAnswerQuestion();
        checkFinalOfQuiz();
    }

    private void countNumberOfQuestion() {
        String text = mCurrentIndex + 1 + "/" + mQuestionsList.size();
        mView.setQuestionCounter(text);
    }

    private void checkAnswerQuestion() {
        boolean isAnswered = mUserAnswers.containsKey(mCurrentIndex);
        mView.setButtonsEnabled(!isAnswered);
        int flag = Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS;
        if (isAnswered) {
            boolean answer = mUserAnswers.get(mCurrentIndex).isResult();
            flag = answer ? Constants.PUSH_TRUE_BUTTON : Constants.PUSH_FALSE_BUTTON;
        }
        mView.setCorrectButtonStyle(flag);
    }

    private void initAnswersForResultFragment() {
        for (Map.Entry<Integer, Answer> entry : mUserAnswers.entrySet()) {
            mMapUserAnswersForResultFragment.put(entry.getKey(), entry.getValue().isResult());
        }
    }

    private void checkFinalOfQuiz() {
        initAnswersForResultFragment();
        final int size = mQuestionsList.size();
        if (size == mUserAnswers.size()) {
            mView.showLoading();
            Disposable disposable = mInteractor.checkQuestions(mCategoryName, mUserAnswers)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            mView.hideLoading();
                            mRouter.goToQuizResultFragment(integer, mCategoryName, mMapUserAnswersForResultFragment);
                        }
                    });
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }
}
