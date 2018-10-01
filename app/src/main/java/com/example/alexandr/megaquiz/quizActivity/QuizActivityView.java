package com.example.alexandr.megaquiz.quizActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandr.megaquiz.R;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityView extends AppCompatActivity implements QuizContract.View {
    private QuizContract.Presenter mPresenter;

    private TextView mQuestionTV;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mPresenter = new QuizActivityPresenter(this);

        mQuestionTV = (TextView) findViewById(R.id.question);
        mQuestionTV.setText(mPresenter.getFirstQuestion().getQuestion());

        mTrueButton = (Button) findViewById(R.id.btnTrue);
        mFalseButton = (Button) findViewById(R.id.btnFalse);
        mNextButton = (Button) findViewById(R.id.btnNext);
        mPrevButton = (Button) findViewById(R.id.btnPrev);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionTV.setText(mPresenter.getNextQuestion().getQuestion());
                pressChecker();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionTV.setText(mPresenter.getPrevQuestion().getQuestion());
                pressChecker();
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSwitcher(false);
                mPresenter.pressTrueButton();
                questionAnswerResult();
                quizResult();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSwitcher(false);
                mPresenter.pressFalseButton();
                questionAnswerResult();
                quizResult();
            }
        });
    }

    @Override
    public void questionAnswerResult() {
        Toast toast;
        if (mPresenter.resultQuestion()) {
            toast = Toast.makeText(getApplicationContext(), "Правильный ответ", Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(getApplicationContext(), "Неправильный ответ", Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    public void buttonSwitcher(boolean b) {
        mTrueButton.setEnabled(b);
        mFalseButton.setEnabled(b);
    }

    @Override
    public void pressChecker() {
        buttonSwitcher(!mPresenter.pressCheck());
    }

    @Override
    public void quizResult() {
        if (mPresenter.isQuizFinish()) {
            Toast toast = Toast.makeText(getApplicationContext(), mPresenter.quizResult(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
