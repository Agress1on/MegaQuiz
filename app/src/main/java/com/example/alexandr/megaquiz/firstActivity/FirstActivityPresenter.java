package com.example.alexandr.megaquiz.firstActivity;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class FirstActivityPresenter implements FirstContract.Presenter {
    private FirstContract.View mView;
    private FirstContract.Model mModel;

    public FirstActivityPresenter(FirstContract.View view) {
        this.mView = view;
        this.mModel = new FirstActivityModel();
    }

    @Override
    public void startCategoryActivity() {
        mView.goToCategoryActivity();
    }
}
