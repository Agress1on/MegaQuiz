package com.example.alexandr.megaquiz.questionActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.R;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuestionActivityView extends AppCompatActivity implements QuestionContract.View {

    private QuestionContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mPresenter = new QuestionActivityPresenter(this);
    }
}
