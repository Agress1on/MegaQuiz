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

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.app.QuizPresenterModule;

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

    /*
    @Inject
    BankQuestion bankQuestion;
    @Inject
    QuizInteractor mModel;

    */
    // private QuizInteractor mQuizActivityModel;

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
    @BindView(R.id.question_counter)
    TextView mQuestionCounter;
    @BindView(R.id.true_question_counter)
    TextView mTrueQuestionCounter;
    @BindView(R.id.number_question_counter)
    TextView mNumberQuestionCounter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        // bankQuestion = Quiz.getComponent().getBankQuestion();
        //  mQuizActivityModel = Quiz.getComponent().getQuizActivityModel();
        //   Quiz.getComponent().injectsQuizActivity(this); // to camoe


        DaggerQuizComponent.builder()
                .quizPresenterModule(new QuizPresenterModule(this))
                .build()
                .injectsQuizActivity(this);


        //  Quiz.getComponent().injectsQuizActivity(this);
        //mPresenter = new QuizPresenter(this, new QuizInteractor(new BankQuestion())); //рабочая штука
        /*
        mQuestionTV = (TextView) findViewById(R.id.question);
        mQuestionCounter = (TextView) findViewById(R.id.question_counter);
        mTrueQuestionCounter = (TextView) findViewById(R.id.true_question_counter);
        mNumberQuestionCounter = (TextView) findViewById(R.id.number_question_counter);
        */

        mPresenter.viewIsReady();
        /*
        mTrueButton = (Button) findViewById(R.id.btnTrue);
        mFalseButton = (Button) findViewById(R.id.btnFalse);
        mNextButton = (Button) findViewById(R.id.btnNext);
        mPrevButton = (Button) findViewById(R.id.btnPrev);
        */
        /*
        mNextButton.setOnClickListener(view -> mPresenter.onNextButton());
        mPrevButton.setOnClickListener(view -> mPresenter.onPrevButton());
        mTrueButton.setOnClickListener(view -> mPresenter.onTrueButton());
        mFalseButton.setOnClickListener(view -> mPresenter.onFalseButton());
        */

    }

    @Override
    public void setQuestionTVText(String text) {
        mQuestionTV.setText(text);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchButton(boolean result) {
        mTrueButton.setEnabled(result);
        mFalseButton.setEnabled(result);
    }

    @Override
    public void setQuestionCounter(String text) {
        mQuestionCounter.setText(text);
    }

    @Override
    public void setNumberQuestionCounter(String text) {
        mNumberQuestionCounter.setText(text);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, QuizView.class);
        return intent;
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
}
