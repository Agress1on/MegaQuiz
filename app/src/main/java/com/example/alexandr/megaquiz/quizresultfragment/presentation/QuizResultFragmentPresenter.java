package com.example.alexandr.megaquiz.quizresultfragment.presentation;

import android.view.View;

import com.example.alexandr.megaquiz.quizresultfragment.QuizResultFragmentContract;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultFragmentPresenter implements QuizResultFragmentContract.Presenter {

    private QuizResultFragmentContract.View mView;
    private QuizResultFragmentContract.Interactor mInteractor;
    private String mCategoryName;
    private HashMap<Integer, Boolean> mUserAnswersMap;
    private int mCorrectAnswers;

    private CompositeDisposable mCompositeDisposable;
    private List<QuizResultItem> mListForRecyclerView;

    public QuizResultFragmentPresenter(QuizResultFragmentContract.Interactor interactor,
                                       String categoryName, HashMap<Integer, Boolean> userAnswersMap, int correctAnswers) {
        this.mInteractor = interactor;
        this.mCategoryName = categoryName;
        this.mUserAnswersMap = userAnswersMap;
        this.mCorrectAnswers = correctAnswers;
        //
        this.mCompositeDisposable = new CompositeDisposable();
        this.mListForRecyclerView = new ArrayList<>();
    }

    @Override
    public void attachView(QuizResultFragmentContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onStartView() {
        if (!mListForRecyclerView.isEmpty()) {
            mView.addListQuizResultItemForRecyclerView(mListForRecyclerView);
            mView.setTextOfResultTextView(mUserAnswersMap.size(), mCorrectAnswers, mCategoryName);
            return;
        }
        mView.showLoading();
        Disposable disposable = mInteractor.getQuizResultItems(mCategoryName, mUserAnswersMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuizResultItem>>() {
                    @Override
                    public void accept(List<QuizResultItem> list) throws Exception {
                        mListForRecyclerView.addAll(list);
                        mView.hideLoading();
                    }
                });
        mCompositeDisposable.add(disposable);
        mView.addListQuizResultItemForRecyclerView(mListForRecyclerView);
        mView.setTextOfResultTextView(mUserAnswersMap.size(), mCorrectAnswers, mCategoryName);
    }

    @Override
    public void onCheckBoxClick(boolean tap) {

    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
