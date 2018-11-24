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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        ButterKnife.bind(this);

        mPresenter = new QuizResultPresenter(this, new QuizResultInteractor());
    }

    public static Intent getIntent(Context context, int quizSize, int correctAnswers) {
        Intent intent = new Intent(context, QuizResultView.class);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT, quizSize);
        intent.putExtra(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT, correctAnswers);
        return intent;
    }

    @OnClick({R.id.restart_button, R.id.go_to_QS_button, R.id.go_to_start_button})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.restart_button:
                break;
            case R.id.go_to_QS_button:
                break;
            case R.id.go_to_start_button:
                break;
        }
    }
}
