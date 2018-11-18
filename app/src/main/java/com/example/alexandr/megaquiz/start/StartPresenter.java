package com.example.alexandr.megaquiz.start;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartPresenter implements StartContract.Presenter {
    private StartContract.View mView;
    private StartContract.Model mModel;

    public StartPresenter(StartContract.View view) {
        this.mView = view;
        this.mModel = new StartInteractor();
    }

}
