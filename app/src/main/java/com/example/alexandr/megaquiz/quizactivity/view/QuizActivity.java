package com.example.alexandr.megaquiz.quizactivity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizactivity.inject.DaggerQuizActivityComponent;
import com.example.alexandr.megaquiz.quizactivity.inject.QuizActivityPresenterModule;
import com.example.alexandr.megaquiz.quizactivity.QuizActivityContract;
import com.example.alexandr.megaquiz.quizactivity.presentation.QuizActivityPresenter;
import com.example.alexandr.megaquiz.quizresultactivity.view.QuizResultActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivity extends AppCompatActivity implements QuizActivityContract.View {
    @Inject
    QuizActivityPresenter mPresenter;

    @BindView(R.id.static_category_name)
    TextView mStaticCategoryNameTextView;
    @BindView(R.id.question)
    TextView mQuestionTV;

    @BindView(R.id.btnTrue)
    Button mTrueButton;
    @BindView(R.id.btnFalse)
    Button mFalseButton;
    @BindView(R.id.btnNext)
    Button mNextButton;
    @BindView(R.id.btnPrev)
    Button mPrevButton;

    @BindView(R.id.question_count)
    TextView mQuestionCount;


    private String mCategoryName = "SENYAAAAA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mCategoryName = intent.getStringExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);
        mStaticCategoryNameTextView.setText(mCategoryName.toUpperCase());

        DaggerQuizActivityComponent.builder()
                .quizActivityPresenterModule(new QuizActivityPresenterModule(this))
                .build()
                .injectsQuizActivity(this);

        mPresenter.prepareViewForFirstQuestion();
    }

    @Override
    public String sentToPresenterSelectedCategory() {
        return mCategoryName;
    }

    @Override
    public void setQuestionTextView(String text) {
        mQuestionTV.setText(text);
    }

    @Override
    public void switchButton(boolean result) {
        mTrueButton.setEnabled(result);
        mFalseButton.setEnabled(result);
    }

    @Override
    public void setCorrectButtonStyle(int key) {
        int forTrueButton = 0;
        int forFalseButton = 0;
        switch (key) {
            case Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS :
                forTrueButton = R.drawable.shape_for_true_button;
                forFalseButton = R.drawable.shape_for_false_button;
                break;
            case Constants.PUSH_TRUE_BUTTON :
                forTrueButton = R.drawable.shape_for_true_button_answered;
                forFalseButton = R.drawable.shape_for_false_button_not_answered;
                break;
            case Constants.PUSH_FALSE_BUTTON :
                forTrueButton = R.drawable.shape_for_true_button_not_answered;
                forFalseButton = R.drawable.shape_for_false_button_answered;
                break;
        }
        mTrueButton.setBackground(getResources().getDrawable(forTrueButton));
        mFalseButton.setBackground(getResources().getDrawable(forFalseButton));
    }

    @Override
    public void setQuestionCount(String text) {
        mQuestionCount.setText(text);
    }

    @Override
    public void startQuizResultActivity(int quizSize, int correctAnswers) {
        Intent intent = QuizResultActivity.getIntent(this, quizSize, correctAnswers, mCategoryName);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.btnTrue, R.id.btnFalse, R.id.btnNext, R.id.btnPrev})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTrue:
                mPresenter.onTrueButton();
                break;
            case R.id.btnFalse:
                mPresenter.onFalseButton();
                break;
            case R.id.btnNext:
                mPresenter.onNextButton();
                break;
            case R.id.btnPrev:
                mPresenter.onPrevButton();
                break;
        }
    }

    public static Intent getIntent(Context context, String key) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW, key);
        return intent;
    }
}
