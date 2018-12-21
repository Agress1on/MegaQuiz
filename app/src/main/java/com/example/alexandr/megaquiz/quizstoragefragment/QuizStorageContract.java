package com.example.alexandr.megaquiz.quizstoragefragment;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizStorageContract {

    interface Interactor {
        Single<List<QuizStorageItem>> getListOfStorageItem();

        Single<List<QuizStorageItem>> getListOfStorageItemWithoutEmpty();
    }

    interface Presenter {
        void onCheckBoxClick(boolean isChecked);

        void onClick(String key);

        void onDestroy();
    }

    interface View {
        void initListForRecyclerAdapter(List<QuizStorageItem> list);

        void startActivityQuizView(String key);

        void updateUI(List<QuizStorageItem> list, String text);

        void showLoading();

        void hideLoading();
    }
}
