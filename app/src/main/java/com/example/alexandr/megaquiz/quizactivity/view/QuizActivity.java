package com.example.alexandr.megaquiz.quizactivity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.app.App;
import com.example.alexandr.megaquiz.quizactivity.QuizActivityContract;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;

import javax.inject.Inject;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivity extends AppCompatActivity implements QuizActivityContract.View {

    @Inject
    QuizActivityContract.Presenter mPresenter;

    private String mCategoryName = "";
    private QuizFragment mQuizFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        mCategoryName = intent.getStringExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);

        mQuizFragment = QuizFragment.newInstance(mCategoryName);
        mFragmentManager = getSupportFragmentManager();
        App.getApp(this).getComponentsHolder().getQuizActivityComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getApp(this).getComponentsHolder().releaseQuizActivityComponent();
    }

    @Override
    public void addQuizFragment() {
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
