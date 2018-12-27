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
        Single<List<QuizStorageItem>> getListsOfStorageItem();
    }

    interface Presenter {

        void onStart();

        void onCheckBoxClick(boolean isChecked);

        void onClick(String key);

        void onDestroy();

        void attachView(QuizStorageContract.View view);

        void detachView();
    }

    interface View {
        void addQuizStorageItemListForRecyclerAdapter(List<QuizStorageItem> list);

        void startActivityQuizView(String key);

        void updateRecyclerView(List<QuizStorageItem> newList);

        void showLoading();

        void hideLoading();
    }
}
