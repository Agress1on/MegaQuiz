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

    private boolean isViewReady = false;

    public QuizStoragePresenter(QuizStorageContract.View view, QuizStorageContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;

        mItemListFull = new ArrayList<>();
        mItemListWithoutEmpty = new ArrayList<>();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate() {
        isViewReady = false;
    }

    @Override
    public void onCreateView(boolean isChecked) {
        isViewReady = true;

        if (!mItemListFull.isEmpty()) {
            List<QuizStorageItem> list =  isChecked ?  mItemListFull : mItemListWithoutEmpty;
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
                        mView.hideLoading();
                        mView.addQuizStorageItemListForRecyclerAdapter(mItemListWithoutEmpty);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    // подумать над переносом части метода во View
    @Override
    public void onCheckBoxClick(boolean isChecked) {
        List<QuizStorageItem> newList = isChecked ? mItemListFull : mItemListWithoutEmpty; //true
        mView.updateRecyclerView(newList);
        /*
        List<QuizStorageItem> newList = mItemListWithoutEmpty;
        if (isChecked) {
            newList = mItemListFull;
        }
        mView.updateRecyclerView(newList);
        */
        /*
        String text = "Показать пустые категории";
        List<QuizStorageItem> newList = mItemListWithoutEmpty;
        if (isChecked) {
            newList = mItemListFull;
            text = "Скрыть пустые категории";
        }
        mView.updateRecyclerView(newList, text);
        */
    }

    @Override
    public void onClick(String key) {
        mView.startActivityQuizView(key);
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
        isViewReady = false;
    }
}
