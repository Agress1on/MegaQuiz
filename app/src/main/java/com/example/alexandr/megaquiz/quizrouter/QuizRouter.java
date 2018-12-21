package com.example.alexandr.megaquiz.quizrouter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;
import com.example.alexandr.megaquiz.quizresultfragment.view.QuizResultFragment;

import java.util.HashMap;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizRouter extends AppCompatActivity implements QuizFragmentContract.Router {

    private String mCategoryName = "";

    private QuizFragment mQuizFragment;
    private QuizResultFragment mQuizResultFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        mCategoryName = intent.getStringExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);

        mFragmentManager = getSupportFragmentManager();
        if (mFragmentManager.getFragments().size() == 0) {
            setQuizFragment();
        }
    }

    @Override
    public void setQuizFragment() {
        mQuizFragment = QuizFragment.newInstance(mCategoryName);
        mFragmentManager.beginTransaction()
                .add(R.id.frame_for_quiz, mQuizFragment)
                .commit();
    }

    @Override
    public void goToQuizResult(int quizSize, int correctAnswers, String categoryName, HashMap<Integer, Boolean> map) {
        mQuizResultFragment = QuizResultFragment.newInstance(quizSize, correctAnswers, categoryName, map);
        mFragmentManager.beginTransaction()
                .replace(R.id.frame_for_quiz, mQuizFragment)
                .commit();
    }

    public static Intent getIntent(Context context, String key) {
        Intent intent = new Intent(context, QuizRouter.class);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW, key);
        return intent;
    }
}
