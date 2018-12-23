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
        void onCheckBoxClick(boolean isChecked);

        void onClick(String key);

        void onDestroy();
    }

    interface View {
        void addListQuizStorageItemForRecyclerAdapter(List<QuizStorageItem> list);

        void startActivityQuizView(String key);

        void updateRecyclerViewList(List<QuizStorageItem> newList, String text);

        void showLoading();

        void hideLoading();
    }
}
