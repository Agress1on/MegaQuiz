package com.example.alexandr.megaquiz.startactivity.presentation;

import com.example.alexandr.megaquiz.startactivity.StartContract;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartActivityPresenter implements StartContract.Presenter {

    private StartContract.View mView;
    private StartContract.Interactor mInteractor;

    public StartActivityPresenter(StartContract.View view, StartContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }
}
