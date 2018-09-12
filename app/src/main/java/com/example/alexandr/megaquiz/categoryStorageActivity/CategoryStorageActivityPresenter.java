package com.example.alexandr.megaquiz.categoryStorageActivity;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class CategoryStorageActivityPresenter implements CategoryStorageContract.Presenter {
    private CategoryStorageActivityView mView;
    private CategoryStorageActivityModel mModel;

    public CategoryStorageActivityPresenter(CategoryStorageActivityView view) {
        this.mView = view;
        this.mModel = new CategoryStorageActivityModel();
    }
}
