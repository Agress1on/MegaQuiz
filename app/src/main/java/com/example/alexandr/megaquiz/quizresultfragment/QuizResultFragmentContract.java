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
        void setResultTextView(String text);

        void setVisibleRecycler(int state, String text);
    }

    interface Presenter {
        String forResultTextView(int size, int correctAnswers, String categoryName);

        void initMapWithRealAnswers(String key);

        void onDestroy();

        void createItemForRecycler(LinkedHashMap<Integer, Boolean> map);

        List<QuizResultItem> getListForRecyclerView();

        void onCheckBoxClick(boolean tap);
    }
}
