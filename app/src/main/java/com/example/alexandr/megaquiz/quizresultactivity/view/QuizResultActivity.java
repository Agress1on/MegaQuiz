package com.example.alexandr.megaquiz.quizresultactivity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;
import com.example.alexandr.megaquiz.quizresultactivity.QuizResultActivityContract;
import com.example.alexandr.megaquiz.quizresultactivity.interactor.QuizResultActivityInteractor;
import com.example.alexandr.megaquiz.quizresultactivity.presentation.QuizResultActivityPresenter;
import com.example.alexandr.megaquiz.startactivity.view.StartActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizResultActivity extends AppCompatActivity implements QuizResultActivityContract.View {
    private QuizResultActivityContract.Presenter mPresenter;

    @BindView(R.id.result_text)
    TextView mResultTextView;
    @BindView(R.id.restart_button)
    Button mRestartButton;
    @BindView(R.id.go_to_QS_button)
    Button mGoToQuizStorageButton;
    @BindView(R.id.go_to_start_button)
    Button mGoToStartButton;

    private int mQuizSize;
    private int mCorrectAnswers;
    private String mNameCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mQuizSize = intent.getIntExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_QUIZ_SIZE, 0);
        mCorrectAnswers = intent.getIntExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_CORRECT_ANSWERS, 0);
        mNameCategory = intent.getStringExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_NAME_CATEGORY);


        mPresenter = new QuizResultActivityPresenter(this, new QuizResultActivityInteractor());

        setResultTextView(mPresenter.forResultTextView(mQuizSize, mCorrectAnswers, mNameCategory));
    }

    @Override
    public void setResultTextView(String text) {
        mResultTextView.setText(text);
    }

    public static Intent getIntent(Context context, int quizSize, int correctAnswers, String nameCategory) {
        Intent intent = new Intent(context, QuizResultActivity.class);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_QUIZ_SIZE, quizSize);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_NAME_CATEGORY, nameCategory);
        return intent;
    }

    @OnClick({R.id.restart_button, R.id.go_to_QS_button, R.id.go_to_start_button})
    void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.restart_button:
                intent = QuizActivity.getIntent(this, mNameCategory);
                break;
            case R.id.go_to_QS_button:
             //   intent = QuizStorageFragment.getIntent(this);
                break;
            case R.id.go_to_start_button:
                intent = StartActivity.getIntent(this);
                break;
        }
        startActivity(intent);
        finish();
    }
}
