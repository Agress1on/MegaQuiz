package com.example.alexandr.megaquiz.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quizStorage.QuizStorageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartView extends AppCompatActivity implements StartContract.View {

    private StartPresenter mPresenter;

    @BindView(R.id.btn_randomQuiz)
    Button mRandomBtn;
    @BindView(R.id.btn_category)
    Button mCategoryBtn;
    @BindView(R.id.btn_test_general_questions)
    Button mTestGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        mPresenter = new StartPresenter(this, new StartInteractor(new BankQuestion()));
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, StartView.class);
        return intent;
    }

    @OnClick({R.id.btn_randomQuiz, R.id.btn_category, R.id.btn_test_general_questions})
    void onClick(android.view.View view) {
        switch (view.getId()) {
            case R.id.btn_randomQuiz:
                Intent intent = QuizView.getIntent(StartView.this, mPresenter.getStringForRandom());
                startActivity(intent);
                break;
            case R.id.btn_category:
                Intent intent1 = QuizStorageView.getIntent(StartView.this);
                startActivity(intent1);
                break;
            case R.id.btn_test_general_questions:
                Intent intent2 = QuizView.getIntent(StartView.this, Constants.GENERAL_QUESTIONS);
                startActivity(intent2);
                break;
        }
    }
}
