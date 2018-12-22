package com.example.alexandr.megaquiz.quizresultfragment.presentation;

import android.view.View;

import com.example.alexandr.megaquiz.bankquestion.Question;
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

    private CompositeDisposable mCompositeDisposable;
    private List<Question> mQuestions;
    private List<QuizResultItem> mListForRecyclerView;

    private String mTextResult;

    public QuizResultFragmentPresenter(QuizResultFragmentContract.View view, QuizResultFragmentContract.Interactor interactor, String categoryName, HashMap<Integer, Boolean> userAnswersMap) {
        this.mView = view;
        this.mInteractor = interactor;
        this.mCategoryName = categoryName;
        this.mUserAnswersMap = userAnswersMap;

        //
        this.mQuestions = new ArrayList<>();
        this.mCompositeDisposable = new CompositeDisposable();
        this.mListForRecyclerView = new ArrayList<>();
    }

    @Override
    public void onStartView() {
        mView.showLoading();
        Disposable disposable = mInteractor.getQuestion(mCategoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Question>>() {
                    @Override
                    public void accept(List<Question> questions) throws Exception {
                        mQuestions.addAll(questions);

                        createItemForRecycler(mUserAnswersMap);
                        mView.hideLoading();
                    }
                });
        mCompositeDisposable.add(disposable);
        mView.initListForRecyclerView(mListForRecyclerView);
    }

    @Override
    public void createItemForRecycler(HashMap<Integer, Boolean> map) {
        for (HashMap.Entry<Integer, Boolean> entry : map.entrySet()) {
            QuizResultItem quizResultItem =
                    new QuizResultItem(entry.getKey(), mQuestions.get(entry.getKey()).getTextQuestion(),
                            mQuestions.get(entry.getKey()).isTrueAnswer(), entry.getValue());
            // второе место
            mListForRecyclerView.add(quizResultItem);
        }
    }

    @Override
    public void createTextForResultTextView(int size, int correctAnswers, String categoryName) {
        String level;
        int percent = ((correctAnswers * 100) / size);
        if (percent < 50) {
            level = "ужасно";
        } else if (percent < 75) {
            level = "удовлетворительно";
        } else if (percent <= 89) {
            level = "хорошо";
        } else {
            level = "отлично";
        }
        mTextResult = "Вы прошли опрос категории \"" + categoryName + "\". Вы " + level + " владеете знаниями данной в области и дали " + percent + "% верных ответов.\nЧто делать дальше?";
        mView.setResultTextView(mTextResult);
    }

    @Override
    public void onCheckBoxClick(boolean tap) {
        String text = tap ? "Скрыть подробности" : "Показать подробности";
        int flagRecycler = tap ? View.VISIBLE : View.INVISIBLE;
        mView.setVisibilityOfRecycler(flagRecycler, text);
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
