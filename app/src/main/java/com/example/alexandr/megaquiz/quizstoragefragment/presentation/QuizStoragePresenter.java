package com.example.alexandr.megaquiz.quizstoragefragment.presentation;

import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageContract;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
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

    private List<QuizStorageItem> mItemListFull;
    private List<QuizStorageItem> mItemListWithoutEmpty;
    private CompositeDisposable mCompositeDisposable;

    private boolean mIsChecked;

    public QuizStoragePresenter(QuizStorageContract.Interactor interactor) {
        this.mInteractor = interactor;
        mIsChecked = false;
        mItemListFull = new ArrayList<>();
        mItemListWithoutEmpty = new ArrayList<>();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(QuizStorageContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onStart() {
        if (!mItemListFull.isEmpty()) {
            List<QuizStorageItem> list = mIsChecked ? mItemListFull : mItemListWithoutEmpty;
            mView.addQuizStorageItemListForRecyclerAdapter(list);
            return;
        }
        mView.showLoading();
        Disposable disposable = mInteractor.getListsOfStorageItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuizStorageItem>>() {
                    @Override
                    public void accept(List<QuizStorageItem> quizStorageItems) throws Exception {
                        mItemListFull.addAll(quizStorageItems);
                        for (QuizStorageItem quizStorageItem : quizStorageItems) {
                            if (quizStorageItem.getCategorySize() > 0)
                                mItemListWithoutEmpty.add(quizStorageItem);
                        }
                        mView.addQuizStorageItemListForRecyclerAdapter(mItemListWithoutEmpty);
                        mView.hideLoading();

                    }
                });

        mCompositeDisposable.add(disposable);
    }

    // подумать над переносом части метода во View
    @Override
    public void onCheckBoxClick(boolean isChecked) {
        mIsChecked = isChecked;
        List<QuizStorageItem> newList = mIsChecked ? mItemListFull : mItemListWithoutEmpty; //true
        mView.updateRecyclerView(newList);
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
