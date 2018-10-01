package com.example.alexandr.megaquiz.startActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizActivity.QuizActivityView;
import com.example.alexandr.megaquiz.quizStorageActivity.QuizStorageActivityView;

public class StartActivityView extends AppCompatActivity implements StartContract.View {

    private StartActivityPresenter mPresenter;

    private Button mRandomBtn;
    private Button mCategoryBtn;
    private Button mTestGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mPresenter = new StartActivityPresenter(this);

        mRandomBtn = (Button)findViewById(R.id.btn_randomQuiz);
        mCategoryBtn = (Button) findViewById(R.id.btn_category);
        mCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startCategoryActivity();
            }
        });

        mTestGeneral = (Button) findViewById(R.id.btn_test_general_questions);
        mTestGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivityView.this, QuizActivityView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void goToCategoryActivity() {
        Intent intent = new Intent(StartActivityView.this, QuizStorageActivityView.class);
        startActivity(intent);
    }
}
