package com.example.alexandr.megaquiz.startActivity;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface StartContract {
    interface View {
        void goToCategoryActivity();
    }

    interface Presenter {
        void startCategoryActivity();
    }

    interface Model {

    }
}
