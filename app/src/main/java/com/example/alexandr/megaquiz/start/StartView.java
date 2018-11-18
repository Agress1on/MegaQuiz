package com.example.alexandr.megaquiz.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quizStorage.QuizStorageView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        mPresenter = new StartPresenter(this);

        /*
        mRandomBtn = (Button)findViewById(R.id.btn_randomQuiz);
        mCategoryBtn = (Button) findViewById(R.id.btn_category);
        mTestGeneral = (Button) findViewById(R.id.btn_test_general_questions);
        */

        mCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mPresenter.startCategoryActivity();
                Intent intent = new QuizStorageView().getIntent(StartView.this);
                startActivity(intent);
            }
        });


        mTestGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new QuizView().getIntent(StartView.this);
                startActivity(intent);
            }
        });
    }

}
