package com.example.alexandr.megaquiz.quizstoragefragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageContract;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageItem;
import com.example.alexandr.megaquiz.quizstoragefragment.interactor.QuizStorageInteractor;
import com.example.alexandr.megaquiz.quizstoragefragment.presentation.QuizStoragePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageFragment extends Fragment implements QuizStorageContract.View {

    private QuizStorageContract.Presenter mPresenter;

    @BindView(R.id.list_switch)
    Switch mSwitch;
    @BindView(R.id.text_for_switch)
    TextView mTextView;

    private RecyclerView mRecyclerView;
    // private RecyclerView.Adapter mAdapter;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_quiz_storage, null);
        final FragmentActivity c = getActivity();
        ButterKnife.bind(this, view);
        mPresenter = new QuizStoragePresenter(this, new QuizStorageInteractor(new BankQuestion()));

        FragmentActivity fragmentActivity = getActivity();
        List<QuizStorageItem> mCat = mPresenter.getCategoriesNamesForViewWithoutEmpty();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler);
        mLayoutManager = new LinearLayoutManager(fragmentActivity); // XMMMMM
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(mCat, key -> mPresenter.onClick(key));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }


    @Override
    public void startActivityQuizView(String key) {
        Intent intent = QuizView.getIntent(getContext(), key);
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

    public static QuizStorageFragment newInstance() {
        Bundle args = new Bundle();
        QuizStorageFragment fragment = new QuizStorageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnCheckedChanged({R.id.list_switch})
    void onSelected(Switch button, boolean checked) {
        mPresenter.onCheckBoxClick(checked);
    }
}
/*
public class QuizStorageFragment extends AppCompatActivity implements QuizStorageContract.View {
    private QuizStorageContract.Presenter mPresenter;

    @BindView(R.id.list_switch)
    Switch mSwitch;
    @BindView(R.id.text_for_switch)
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
        Intent intent = new Intent(context, QuizStorageFragment.class);
        return intent;
    }

    @Override
    public void startActivityQuizView(String key) {
        Intent intent = QuizView.getIntent(QuizStorageFragment.this, key);
        startActivity(intent);
        finish();
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

    @OnCheckedChanged({R.id.list_switch})
    void onSelected(Switch button, boolean checked) {
        mPresenter.onCheckBoxClick(checked);
    }
}

*/


