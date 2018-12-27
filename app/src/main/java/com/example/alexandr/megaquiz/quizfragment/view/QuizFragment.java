package com.example.alexandr.megaquiz.quizfragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.app.App;
import com.example.alexandr.megaquiz.quizfragment.Answer;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizFragment extends Fragment implements QuizFragmentContract.View {

    @Inject
    QuizFragmentContract.Presenter mPresenter;

    @BindView(R.id.category_name)
    TextView mCategoryNameTextView;

    @BindView(R.id.question)
    TextView mQuestionTextView;

    @BindView(R.id.btnTrue)
    Button mTrueButton;

    @BindView(R.id.btnFalse)
    Button mFalseButton;

    @BindView(R.id.btnNext)
    Button mNextButton;

    @BindView(R.id.btnPrev)
    Button mPrevButton;

    @BindView(R.id.question_count)
    TextView mQuestionCountTextView;

    /*
    @BindView(R.id.progres_bar)
    ProgressBar mProgressBar;
    */

    @BindView(R.id.progress_bar_quiz_fragment)
    GeometricProgressView mProgressBar;

    @BindViews({R.id.category_name, R.id.question, R.id.btnTrue,
            R.id.btnFalse, R.id.btnNext, R.id.btnPrev, R.id.question_count})
    List<View> mViewList;

    private Context mContext;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        String categoryName = getArguments().getString(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);

        QuizFragmentContract.Router router;
        if (getParentFragment() instanceof QuizFragmentContract.Router) {
            router = (QuizFragmentContract.Router) getParentFragment();
        } else if (getActivity() instanceof QuizFragmentContract.Router) {
            router = (QuizFragmentContract.Router) getActivity();
        } else {
            throw new IllegalStateException("Parent container must be StartFragmentContract.Router");
        }

        App.getApp(mContext).getComponentsHolder().getQuizFragmentComponent(this, router, categoryName).inject(this);
        mPresenter.attachView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, null);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.onCreateView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        App.getApp(mContext).getComponentsHolder().releaseQuizFragmentComponent();
    }

    @Override
    public void showLoading() {
        for (View view : mViewList) {
            view.setVisibility(View.INVISIBLE);
        }
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        for (View view : mViewList) {
            view.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setQuestionTextView(String text) {
        mQuestionTextView.setText(text);
    }

    @Override
    public void setButtonsEnabled(boolean result) {
        mTrueButton.setEnabled(result);
        mFalseButton.setEnabled(result);
    }

    @Override
    public void setCorrectButtonStyle(int key) {
        int forTrueButton = 0;
        int forFalseButton = 0;
        switch (key) {
            case Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS:
                forTrueButton = R.drawable.shape_for_true_button;
                forFalseButton = R.drawable.shape_for_false_button;
                break;
            case Constants.PUSH_TRUE_BUTTON:
                forTrueButton = R.drawable.shape_for_true_button_answered;
                forFalseButton = R.drawable.shape_for_false_button_not_answered;
                break;
            case Constants.PUSH_FALSE_BUTTON:
                forTrueButton = R.drawable.shape_for_true_button_not_answered;
                forFalseButton = R.drawable.shape_for_false_button_answered;
                break;
        }
        mTrueButton.setBackground(getResources().getDrawable(forTrueButton));
        mFalseButton.setBackground(getResources().getDrawable(forFalseButton));
    }

    @Override
    public void setQuestionCounter(String text) {
        mQuestionCountTextView.setText(text);
    }

    @OnClick({R.id.btnTrue, R.id.btnFalse})
    void onClickForAnswerButtons(View view) {
        switch (view.getId()) {
            case R.id.btnTrue:
                mPresenter.onAnswer(Answer.TRUE);
                break;
            case R.id.btnFalse:
                mPresenter.onAnswer(Answer.FALSE);
                break;
        }
    }

    @OnClick({R.id.btnNext, R.id.btnPrev})
    void onClickForNavigationButtons(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                mPresenter.onNextButton();
                break;
            case R.id.btnPrev:
                mPresenter.onPrevButton();
                break;
        }
    }

    public static QuizFragment newInstance(String key) {
        Bundle args = new Bundle();
        args.putString(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW, key);
        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
