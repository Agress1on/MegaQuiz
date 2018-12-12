package com.example.alexandr.megaquiz.quizresultfragment.presentation;

import android.util.ArrayMap;

import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultActivityContract;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultActivityPresenter implements QuizResultActivityContract.Presenter {
    private QuizResultActivityContract.View mView;
    private QuizResultActivityContract.Interactor mInteractor;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private List<Question> mQuestions = new ArrayList<>();
    private Map<Integer, Boolean> mRealAnswersMap = new ArrayMap<>();
    private List<QuizResultItem> list = new ArrayList<>();

    public QuizResultActivityPresenter(QuizResultActivityContract.View view, QuizResultActivityContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public List<QuizResultItem> getList() {
        return list;
    }

    @Override
    public void initMapWithRealAnswers(String key) {
        Disposable disposable = mInteractor.getQuestion(key)
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Question>>() {
                    @Override
                    public void accept(List<Question> questions) throws Exception {
                        mQuestions.addAll(questions);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void createItemForRecycler(HashMap<Integer, Boolean> map) {
        for (HashMap.Entry<Integer, Boolean> entry : map.entrySet()) {
            QuizResultItem quizResultItem =
                    new QuizResultItem(entry.getKey(), mQuestions.get(entry.getKey()).getTextQuestion(), mQuestions.get(entry.getKey()).isTrueAnswer(), entry.getValue());
            list.add(quizResultItem);
        }
    }

    public String forResultTextView(int size, int correctAnswers, String categoryName) {
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
        String text = "Вы прошли опрос категории \"" + categoryName + "\". Вы " + level + " владеете знаниями данной в области и дали " + percent + "% верных ответов.\nЧто делать дальше?";
        return text;
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
