package com.example.alexandr.megaquiz.startfragment.presentation;

import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartFragmentPresenter implements StartFragmentContract.Presenter {

    private StartFragmentContract.View mView;
    private StartFragmentContract.Interactor mInteractor;

    private String mRandomCategory;
    private CompositeDisposable mCompositeDisposable;

    public StartFragmentPresenter(StartFragmentContract.View view, StartFragmentContract.Interactor interactor) {
        mView = view;
        mInteractor = interactor;
        mCompositeDisposable  = new CompositeDisposable();
    }

    @Override
    public void initRandomCategory() {
        Disposable disposable = mInteractor.getStringCategoryForRandomStart()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mRandomCategory = s;
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onRandomButton() {
        mView.startQuizViewWithRandom(mRandomCategory);
    }

    @Override
    public void onButtonCategory() {
        mView.startQuizStorage();
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
