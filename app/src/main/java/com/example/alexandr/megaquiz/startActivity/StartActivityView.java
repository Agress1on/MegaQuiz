package com.example.alexandr.megaquiz.firstActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizStorageActivity.QuizStorageActivityView;

public class FirstActivityView extends AppCompatActivity implements FirstContract.View {

    private FirstActivityPresenter mPresenter;

    private Button mRandomBtn;
    private Button mCategoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mPresenter = new FirstActivityPresenter(this);

        mRandomBtn = (Button)findViewById(R.id.btn_randomQuiz);
        mCategoryBtn = (Button) findViewById(R.id.btn_category);
        mCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startCategoryActivity();
            }
        });
    }

    @Override
    public void goToCategoryActivity() {
        Intent intent = new Intent(FirstActivityView.this, QuizStorageActivityView.class);
        startActivity(intent);
    }
}
