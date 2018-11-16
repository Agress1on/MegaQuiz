package com.example.alexandr.megaquiz.quizStorageActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alexandr.megaquiz.R;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageActivityView extends AppCompatActivity implements QuizStorageContract.View {
    private QuizStorageContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_storage);

        mPresenter = new QuizStorageActivityPresenter(this);

        List<String> mCat = mPresenter.initCategoriesNames();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this); // XMMMMM
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(mCat);
        mRecyclerView.setAdapter(mAdapter);


    }

    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, QuizStorageActivityView.class);
        return intent;
    }
}
