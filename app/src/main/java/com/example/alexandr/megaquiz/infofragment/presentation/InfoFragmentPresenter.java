package com.example.alexandr.megaquiz.infofragment.presentation;

import com.example.alexandr.megaquiz.infofragment.InfoFragmentContract;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class InfoFragmentPresenter {
    private InfoFragmentContract.View mView;
    private InfoFragmentContract.Interactor mInteractor;

    public InfoFragmentPresenter(InfoFragmentContract.View view, InfoFragmentContract.Interactor interactor) {
        mView = view;
        mInteractor = interactor;
    }
}
