package com.example.alexandr.megaquiz.startfragment.presentation;

import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartFragmentPresenter implements StartFragmentContract.Presenter {
    private StartFragmentContract.View mView;
    private StartFragmentContract.Interactor mInteractor;

    public StartFragmentPresenter(StartFragmentContract.View view, StartFragmentContract.Interactor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onRandomButton() {
        mView.startQuizViewWithRandom(mInteractor.getStringCategoryForRandomStart());
    }

    @Override
    public void onButtonCategory() {
        mView.startQuizStorage();
    }
}
