package com.example.alexandr.megaquiz.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.app.QuizPresenterModule;
import com.example.alexandr.megaquiz.quizResult.QuizResultView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizView extends AppCompatActivity implements QuizContract.View {
    @Inject
    QuizPresenter mPresenter;

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

    @BindView(R.id.category_name)
    TextView mCategoryNameTextView;
    @BindView(R.id.quiz_size)
    TextView mQuizSizeTextView;
    @BindView(R.id.number_of_question)
    TextView mNumberOfQuestionTextView;

    @BindView(R.id.static_category_name)
    TextView mStaticCategoryNameTextView;
    @BindView(R.id.static_quiz_size)
    TextView mStaticQuizSizeTextView;
    @BindView(R.id.static_number_of_question)
    TextView mStaticNumberOfQuestion;

    private String mCategoryName = "SENYAAAAA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mCategoryName = intent.getStringExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);
        mStaticCategoryNameTextView.setText(mCategoryName);

        DaggerQuizComponent.builder()
                .quizPresenterModule(new QuizPresenterModule(this))
                .build()
                .injectsQuizActivity(this);

        mPresenter.prepareViewForFirstQuestion();
    }

    @Override
    public String sentToPresenterChoosenCategory() {
        return mCategoryName;
    }

    @Override
    public void setQuestionTextView(String text) {
        mQuestionTV.setText(text);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchButton(boolean result) {
        /*
        mTrueButton.setEnabled(result);
        mFalseButton.setEnabled(result);
        */
        if (result) {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
            mTrueButton.setBackground(getResources().getDrawable(R.drawable.shape_for_true_button));
            mFalseButton.setBackground(getResources().getDrawable(R.drawable.shape_for_false_button));
        } else {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
            mTrueButton.setBackground(getResources().getDrawable(R.drawable.shape_for_true_button_answered));
            mFalseButton.setBackground(getResources().getDrawable(R.drawable.shape_for_false_button_answered));
        }
    }

    @Override
    public void setStaticQuizSizeTextView(String text) {
        mStaticQuizSizeTextView.setText(text);
    }

    @Override
    public void setStaticNumberOfQuestion(String text) {
        mStaticNumberOfQuestion.setText(text);
    }

    @Override
    public void startQuizResulActivity(int quizSize, int correctAnswers) {
        Intent intent = QuizResultView.getIntent(this, quizSize, correctAnswers, mCategoryName);
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
        Intent intent = new Intent(context, QuizView.class);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW, key);
        return intent;
    }
}
