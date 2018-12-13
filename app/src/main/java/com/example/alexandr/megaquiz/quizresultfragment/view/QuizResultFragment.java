package com.example.alexandr.megaquiz.quizresultfragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;
import com.example.alexandr.megaquiz.quizfragment.Answer;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultActivityContract;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultItem;
import com.example.alexandr.megaquiz.quizresultfragment.domain.QuizResultFragmentInteractor;
import com.example.alexandr.megaquiz.quizresultfragment.presentation.QuizResultActivityPresenter;
import com.example.alexandr.megaquiz.startactivity.view.StartActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Alexandr Mikhalev on 11.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultFragment extends Fragment implements QuizResultActivityContract.View {
    private QuizResultActivityContract.Presenter mPresenter;

    @BindView(R.id.result_text)
    TextView mResultTextView;
    @BindView(R.id.for_recycler_tv)
    TextView mTextView;
    @BindView(R.id.result_switch)
    Switch mSwitch;

    private int mQuizSize;
    private int mCorrectAnswers;
    private String mNameCategory;
    private HashMap<Integer, Boolean> mUserAnswersMap;

    private RecyclerView mRecyclerView;
    private QuizResultAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuizSize = getArguments().getInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_QUIZ_SIZE, 10);
        mCorrectAnswers = getArguments().getInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_CORRECT_ANSWERS, 5);
        mNameCategory = getArguments().getString(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_NAME_CATEGORY, "Error");
        mUserAnswersMap = (HashMap<Integer, Boolean>) getArguments().getSerializable(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_MAP_USER_ANSWERS);
        mPresenter = new QuizResultActivityPresenter(this, new QuizResultFragmentInteractor(new BankQuestion()));
        mPresenter.initMapWithRealAnswers(mNameCategory);
        mPresenter.createItemForRecycler(mUserAnswersMap);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_result, null);
        ButterKnife.bind(this, view);
        setResultTextView(mPresenter.forResultTextView(mQuizSize, mCorrectAnswers, mNameCategory));

        FragmentActivity fragmentActivity = getActivity();
        List<QuizResultItem> mCat = mPresenter.getList();
        mRecyclerView = view.findViewById(R.id.result_recycler);
        mLayoutManager = new LinearLayoutManager(fragmentActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new QuizResultAdapter(mCat);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void setResultTextView(String text) {
        mResultTextView.setText(text);
    }

    @OnCheckedChanged({R.id.result_switch})
    void onSelected(Switch button, boolean checked) {
        mPresenter.onCheckBoxClick(checked);
    }

    @Override
    public void setVisibleRecycler(int state, String text) {
        mRecyclerView.setVisibility(state);
        mTextView.setText(text);
    }

    public static QuizResultFragment newInstance(int quizSize, int correctAnswers, String nameCategory, HashMap<Integer, Boolean> map) {
        Bundle args = new Bundle();
        args.putInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_QUIZ_SIZE, quizSize);
        args.putInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_CORRECT_ANSWERS, correctAnswers);
        args.putString(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_NAME_CATEGORY, nameCategory);
        args.putSerializable(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_MAP_USER_ANSWERS, map);
        QuizResultFragment fragment = new QuizResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
