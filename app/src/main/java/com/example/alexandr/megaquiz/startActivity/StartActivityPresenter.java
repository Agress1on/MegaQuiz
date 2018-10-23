package com.example.alexandr.megaquiz.startActivity;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartActivityPresenter implements StartContract.Presenter {
    private StartContract.View mView;
    private StartContract.Model mModel;

    public StartActivityPresenter(StartContract.View view) {
        this.mView = view;
        this.mModel = new StartActivityModel();
    }

}
