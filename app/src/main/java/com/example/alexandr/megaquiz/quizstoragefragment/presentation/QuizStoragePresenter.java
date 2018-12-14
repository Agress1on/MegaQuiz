package com.example.alexandr.megaquiz.quizstoragefragment.presentation;

import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageContract;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStoragePresenter implements QuizStorageContract.Presenter {

    private QuizStorageContract.View mView;
    private QuizStorageContract.Interactor mInteractor;

    private List<QuizStorageItem> mItemListFull = new ArrayList<>();
    private List<QuizStorageItem> mItemListWithoutEmpty = new ArrayList<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public QuizStoragePresenter(QuizStorageContract.View view, QuizStorageContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
        initListCategoryNamesWithoutEmpty();
        initListCategoryNameFull();
    }

    private void initListCategoryNamesWithoutEmpty() {
        Disposable disposable = mInteractor.getListOfStorageItemWithoutEmpty()
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuizStorageItem>>() {
                    @Override
                    public void accept(List<QuizStorageItem> quizStorageItems) throws Exception {
                        mItemListWithoutEmpty.addAll(quizStorageItems);
                    }
                });
        mCompositeDisposable.add(disposable);
        mView.initListForRecyclerAdapter(mItemListWithoutEmpty);
    }

    private void initListCategoryNameFull() {
        Disposable disposable = mInteractor.getListOfStorageItem()
                .subscribeOn(Schedulers.io())
                // .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuizStorageItem>>() {
                    @Override
                    public void accept(List<QuizStorageItem> quizStorageItems) throws Exception {
                        mItemListFull.addAll(quizStorageItems);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onCheckBoxClick(boolean isChecked) {
        String text = "Показать пустые категории";
        List<QuizStorageItem> list = mItemListWithoutEmpty;
        if (isChecked) {
            list = mItemListFull;
            text = "Скрыть пустые категории";
        }
        mView.updateUI(list, text);
    }

    @Override
    public void onClick(String key) {
        mView.startActivityQuizView(key);
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
