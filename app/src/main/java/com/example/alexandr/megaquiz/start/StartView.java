package com.example.alexandr.megaquiz.start;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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
    @BindView(R.id.doubt_image_view)
    ImageView mImageView;

    boolean visible = true;

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

    @Override
    public void startQuizViewWithRandom(String randomCategory) {
        Intent intent = QuizView.getIntent(StartView.this, randomCategory);
        startActivity(intent);
    }

    @Override
    public void startQuizStorage() {
        Intent intent = QuizStorageView.getIntent(StartView.this);
        startActivity(intent);
    }

    @Override
    public void startTestGeneralQuestions() {
        Intent intent = QuizView.getIntent(StartView.this, Constants.GENERAL_QUESTIONS);
        startActivity(intent);
    }

    @OnClick({R.id.btn_randomQuiz, R.id.btn_category, R.id.btn_test_general_questions})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_randomQuiz:
                mPresenter.onRandomButton();
                break;
            case R.id.btn_category:
                mPresenter.onButtonCategory();
                break;
            case R.id.btn_test_general_questions:
              //  mPresenter.onButtonGeneralQuestionsTest();
                animation();
        }
    }

    private void animation() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.start_constraint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(viewGroup, new Slide(Gravity.RIGHT));
        }
        visible = !visible;
        mImageView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
