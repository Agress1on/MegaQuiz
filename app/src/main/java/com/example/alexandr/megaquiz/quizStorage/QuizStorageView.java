package com.example.alexandr.megaquiz.quizStorage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageView extends AppCompatActivity implements QuizStorageContract.View {
    private QuizStorageContract.Presenter mPresenter;

    @BindView(R.id.checkbox)
    CheckBox mCheckBox;
    @BindView(R.id.text_for_checkbox)
    TextView mTextView;

    private RecyclerView mRecyclerView;
    // private RecyclerView.Adapter mAdapter;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_storage);
        ButterKnife.bind(this);

        mPresenter = new QuizStoragePresenter(this, new QuizStorageInteractor(new BankQuestion()));
        List<QuizStorageItem> mCat = mPresenter.getCategoriesNamesForViewWithoutEmpty();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler);
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

    @Override
    public void updateUI(List<QuizStorageItem> list, String text) {
        RecyclerAdapterDiffUtilCallback recyclerAdapterDiffUtilCallback =
                new RecyclerAdapterDiffUtilCallback(mAdapter.getData(), list);
        DiffUtil.DiffResult recyclerDiffResult = DiffUtil.calculateDiff(recyclerAdapterDiffUtilCallback);
        mAdapter.setData(list);
        recyclerDiffResult.dispatchUpdatesTo(mAdapter);
        mTextView.setText(text);
    }

    @OnCheckedChanged({R.id.checkbox})
    void onSelected(CompoundButton button, boolean checked) {
        mPresenter.onCheckBoxClick(checked);
    }
}
