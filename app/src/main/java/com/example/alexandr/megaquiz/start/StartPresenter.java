package com.example.alexandr.megaquiz.start;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartPresenter implements StartContract.Presenter {
    private StartContract.View mView;
    private StartContract.Interactor mInteractor;

    public StartPresenter(StartContract.View view, StartContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public String getStringForRandom() {
        return mInteractor.getStringCategoryForRandomStart();
    }
}
