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
import com.example.alexandr.megaquiz.app.App;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityView extends AppCompatActivity implements QuizContract.View {
    private QuizContract.Presenter mPresenter;

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
       // bankQuestion = App.getComponent().getBankQuestion();
      //  mQuizActivityModel = App.getComponent().getQuizActivityModel();
     //   App.getComponent().injectsMainActivity(this); // to camoe


        mPresenter = new QuizPresenter(this, new QuizInteractor(new BankQuestion()));
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

        mNextButton.setOnClickListener(view -> mPresenter.onNextButton());
        mPrevButton.setOnClickListener(view -> mPresenter.onPrevButton());
        mTrueButton.setOnClickListener(view -> mPresenter.onTrueButton());
        mFalseButton.setOnClickListener(view -> mPresenter.onFalseButton());
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
    public void buttonSwitcher(boolean result) {
        mTrueButton.setEnabled(result);
        mFalseButton.setEnabled(result);
    }

    @Override
    public void setQuestionCounter(String text) {
        mQuestionCounter.setText(text);
    }

    @Override
    public void setTrueQuestionCounter(String text) {
        mTrueQuestionCounter.setText(text);
    }

    @Override
    public void setNumberQuestionCounter(String text) {
        mNumberQuestionCounter.setText(text);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, QuizActivityView.class);
        return intent;
    }
}
