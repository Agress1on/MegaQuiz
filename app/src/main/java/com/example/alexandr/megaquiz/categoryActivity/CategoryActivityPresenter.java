package com.example.alexandr.megaquiz.categoryActivity;

/**
 * Created by Alexandr Mikhalev on 12.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class CategoryActivityPresenter implements CategoryContract.Presenter {
    private CategoryContract.View mView;
    private CategoryContract.Model mModel;

    public CategoryActivityPresenter(CategoryContract.View view) {
        this.mView = view;
        this.mModel = new CategoryActivityModel();
    }
}
