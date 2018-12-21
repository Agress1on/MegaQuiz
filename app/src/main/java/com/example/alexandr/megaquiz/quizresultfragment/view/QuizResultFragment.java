package com.example.alexandr.megaquiz.quizresultfragment.view;

import android.content.Context;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.app.App;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultFragmentContract;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultItem;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;

/**
 * Created by Alexandr Mikhalev on 11.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultFragment extends Fragment implements QuizResultFragmentContract.View {

    @Inject
    QuizResultFragmentContract.Presenter mPresenter;

    @BindView(R.id.result_text)
    TextView mResultTextView;

    @BindView(R.id.for_recycler_tv)
    TextView mTextView;

    @BindView(R.id.result_switch)
    Switch mSwitch;

    @BindView(R.id.result_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar_quiz_result)
    GeometricProgressView mProgressBar;

    @BindViews({R.id.result_text, R.id.for_recycler_tv, R.id.result_switch, R.id.result_recycler})
    List<View> mViewList;

    private int mQuizSize;
    private int mCorrectAnswers;
    private String mNameCategory;
    private HashMap<Integer, Boolean> mUserAnswersMap;

    private List<QuizResultItem> mCat;
    //private RecyclerView mRecyclerView;
    private QuizResultAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context mContext;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuizSize = getArguments().getInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_QUIZ_SIZE, 10);
        mCorrectAnswers = getArguments().getInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_CORRECT_ANSWERS, 5);
        mNameCategory = getArguments().getString(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_NAME_CATEGORY, "Error");
        mUserAnswersMap = (HashMap<Integer, Boolean>) getArguments().getSerializable(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_MAP_USER_ANSWERS);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_result, null);
        mUnbinder = ButterKnife.bind(this, view);

        App.getApp(mContext).getComponentsHolder()
                .getQuizResultFragmentComponent(this, mNameCategory, mUserAnswersMap).inject(this);

        mPresenter.onStartView();

        mPresenter.createTextForResultTextView(mQuizSize, mCorrectAnswers, mNameCategory);

        FragmentActivity fragmentActivity = getActivity();
        mLayoutManager = new LinearLayoutManager(fragmentActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new QuizResultAdapter(mCat);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        App.getApp(mContext).getComponentsHolder().releaseQuizResultFragmentComponent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), "text", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showProgressBar();
        hideView();
    }

    @Override
    public void hideLoading() {
        hideProgressBar();
        showView();
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void showView() {
        for (View view : mViewList) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void hideView() {
        for (View view : mViewList) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initListForRecyclerView(List<QuizResultItem> list) {
        mCat = list;
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
    public void setVisibilityOfRecycler(int state, String text) {
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
}
