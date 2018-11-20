package com.example.alexandr.megaquiz.quizStorage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizView;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageView extends AppCompatActivity implements QuizStorageContract.View {
    private QuizStorageContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_storage);

        mPresenter = new QuizStoragePresenter(this, new QuizStorageInteractor(new BankQuestion()));

        List<String> mCat = mPresenter.initCategoriesNames();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this); // XMMMMM
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(mCat, key -> mPresenter.onClick(key));
        mRecyclerView.setAdapter(mAdapter);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, QuizStorageView.class);
        return intent;
    }

    @Override
    public void startActivityQuizView(String key) {
        Intent intent = QuizView.getIntent(QuizStorageView.this, key);
        startActivity(intent);
    }
}
