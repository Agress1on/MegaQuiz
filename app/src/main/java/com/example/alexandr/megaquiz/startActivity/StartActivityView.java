package com.example.alexandr.megaquiz.startActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quizStorageActivity.QuizStorageActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivityView extends AppCompatActivity implements StartContract.View {

    private StartActivityPresenter mPresenter;

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
        mPresenter = new StartActivityPresenter(this);

        /*
        mRandomBtn = (Button)findViewById(R.id.btn_randomQuiz);
        mCategoryBtn = (Button) findViewById(R.id.btn_category);
        mTestGeneral = (Button) findViewById(R.id.btn_test_general_questions);
        */

        mCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mPresenter.startCategoryActivity();
                Intent intent = new QuizStorageActivityView().getIntent(StartActivityView.this);
                startActivity(intent);
            }
        });


        mTestGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new QuizView().getIntent(StartActivityView.this);
                startActivity(intent);
            }
        });
    }

}
