package com.example.alexandr.megaquiz.quizresultfragment;

import com.example.alexandr.megaquiz.bankquestion.Question;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizResultFragmentContract {

    interface Interactor {
        Single<List<Question>> getQuestion(String key);
    }

    interface View {
        void initListForRecyclerView(List<QuizResultItem> list);

        void setResultTextView(String text);

        void setVisibilityOfRecycler(int state, String text);

        void showLoading();

        void hideLoading();

        void showToast(String text);
    }

    interface Presenter {
        void onStartView();

        void createItemForRecycler(HashMap<Integer, Boolean> map);

        void createTextForResultTextView(int size, int correctAnswers, String categoryName);

        void onCheckBoxClick(boolean tap);

        void onDestroy();
    }
}
