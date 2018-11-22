package com.example.alexandr.megaquiz.quizStorage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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

    private CheckBox mCheckBox;
    private TextView mTextView;
    private TextView mQuantityTV;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_storage);

        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        mTextView = (TextView) findViewById(R.id.text_for_checkbox);
        mQuantityTV = (TextView) findViewById(R.id.quantity_empty);


        mPresenter = new QuizStoragePresenter(this, new QuizStorageInteractor(new BankQuestion()));
        mQuantityTV.setText(String.valueOf(mPresenter.getCategoriesNamesForViewWithoutEmpty().size()));
        List<QuizStorageItem> mCat = mPresenter.getCategoriesNamesForView();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        mLayoutManager = new LinearLayoutManager(this); // XMMMMM
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(mCat, key -> mPresenter.onClick(key));
        mRecyclerView.setAdapter(mAdapter);

        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "CLAC", Toast.LENGTH_SHORT).show();

                    RecyclerAdapterDiffUtilCallback recyclerAdapterDiffUtilCallback = new RecyclerAdapterDiffUtilCallback(mPresenter.getCategoriesNamesForView(), mPresenter.getCategoriesNamesForViewWithoutEmpty());
                    DiffUtil.DiffResult recyclerDiffResult = DiffUtil.calculateDiff(recyclerAdapterDiffUtilCallback);

                    recyclerDiffResult.dispatchUpdatesTo(mAdapter);

            }
        });
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
