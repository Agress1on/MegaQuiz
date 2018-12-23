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
    private StartFragmentContract.Router mRouter;

    public StartFragmentPresenter(StartFragmentContract.View view, StartFragmentContract.Router router) {
        mView = view;
        mRouter = router;
    }

    @Override
    public void onRandomButton() {
        mRouter.goToRandomQuestion();
    }

    @Override
    public void onButtonCategory() {
       mRouter.goToQuizStorage();
    }

    @Override
    public void onTestButton() {

    }
}
