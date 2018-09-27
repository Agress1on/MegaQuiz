package com.example.alexandr.megaquiz.firstActivity;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface FirstContract {
    interface View {
        void goToCategoryActivity();
    }

    interface Presenter {
        void startCategoryActivity();
    }

    interface Model {

    }
}
