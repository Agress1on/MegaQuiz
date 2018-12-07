package com.example.alexandr.megaquiz.quizResult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quizStorage.QuizStorageView;
import com.example.alexandr.megaquiz.startactivity.view.StartView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizResultView extends AppCompatActivity implements QuizResultContract.View {
    private QuizResultContract.Presenter mPresenter;

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


        mPresenter = new QuizResultPresenter(this, new QuizResultInteractor());

        setResultTextView(mPresenter.forResultTextView(mQuizSize, mCorrectAnswers, mNameCategory));
    }

    @Override
    public void setResultTextView(String text) {
        mResultTextView.setText(text);
    }

    public static Intent getIntent(Context context, int quizSize, int correctAnswers, String nameCategory) {
        Intent intent = new Intent(context, QuizResultView.class);
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
                intent = QuizView.getIntent(this, mNameCategory);
                break;
            case R.id.go_to_QS_button:
                intent = QuizStorageView.getIntent(this);
                break;
            case R.id.go_to_start_button:
                intent = StartView.getIntent(this);
                break;
        }
        startActivity(intent);
        finish();
    }
}
