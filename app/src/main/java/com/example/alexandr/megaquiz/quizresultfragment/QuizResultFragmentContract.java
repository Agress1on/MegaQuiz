package com.example.alexandr.megaquiz.quizresultfragment;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizResultFragmentContract {

    interface Interactor {
        Single<List<QuizResultItem>> getQuizResultItems(String key, HashMap<Integer, Boolean> userAnswersMap);
    }

    interface View {
        void addListQuizResultItemForRecyclerView(List<QuizResultItem> list);

        void setVisibilityOfRecycler(int state, String text);

        void showLoading();

        void hideLoading();

        void setTextOfResultTextView(int size, int correctAnswers, String categoryName);
    }

    interface Presenter {
        void onStartView();

        void onCheckBoxClick(boolean tap);

        void onDestroy();
    }
}
