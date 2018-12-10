package com.example.alexandr.megaquiz.quizactivity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizactivity.QuizActivityContract;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivity extends AppCompatActivity implements QuizActivityContract.View {
    private String mCategoryName = "SENYAAAAA";

    private QuizFragment mQuizFragment;
    private FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        mCategoryName = intent.getStringExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);

        mQuizFragment = QuizFragment.newInstance(mCategoryName, Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);
        mFragmentManager.beginTransaction()
                .add(R.id.frame_for_quiz, mQuizFragment)
                .commit();

    }
    public static Intent getIntent(Context context, String key) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW, key);
        return intent;
    }

}
