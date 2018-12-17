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
import com.example.alexandr.megaquiz.quizresultfragment.view.QuizResultFragment;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.LinkedHashMap;
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

    @BindView(R.id.static_category_name)
    TextView mCategoryNameTV;

    @BindView(R.id.question)
    TextView mQuestionTV;

    @BindView(R.id.btnTrue)
    Button mTrueButton;

    @BindView(R.id.btnFalse)
    Button mFalseButton;

    @BindView(R.id.btnNext)
    Button mNextButton;

    @BindView(R.id.btnPrev)
    Button mPrevButton;

    @BindView(R.id.question_count)
    TextView mQuestionCount;

    /*
    @BindView(R.id.progres_bar)
    ProgressBar mProgressBar;
    */

    @BindView(R.id.progress_bar)
    GeometricProgressView mProgressBar;

    @BindViews({R.id.static_category_name, R.id.question, R.id.btnTrue,
            R.id.btnFalse, R.id.btnNext, R.id.btnPrev, R.id.question_count})
    List<View> mViewList;

    private String mCategoryName = "";

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
        mCategoryName = getArguments().getString(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);
        /*
        mPresenter = new QuizFragmentPresenter(this, new QuizFragmentInteractor(new BankQuestion()));
        DaggerAppComponent.builder()
                .appModule(new AppModule(getContext()))
                .build()
                .createQuizFragmentComponent(new QuizFragmentPresenterModule(this))
                .inject(this);
        */
            App.getApp(mContext).getComponentsHolder().getQuizFragmentComponent(this).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, null);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.initQuestionList(mCategoryName);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        App.getApp(mContext).getComponentsHolder().releaseQuizFragmentComponent();
    }

    @Override
    public void showProgressBarAndSetViewVisibility(int viewState, int progressBarState) {
        for (View view : mViewList) {
            view.setVisibility(viewState);
        }
        mProgressBar.setVisibility(progressBarState);
    }

    @Override
    public void setQuestionTextView(String text) {
        mQuestionTV.setText(text);
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
        mQuestionCount.setText(text);
    }

    @Override
    public void startQuizResultFragment(int quizSize, int correctAnswers, LinkedHashMap<Integer, Boolean> mapAnswers) {
        QuizResultFragment quizResultFragment =
                QuizResultFragment.newInstance(quizSize, correctAnswers, mCategoryName, mapAnswers);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_for_quiz, quizResultFragment)
                .commit();
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

    public static QuizFragment newInstance(String key, String extras) {
        Bundle args = new Bundle();
        args.putString(extras, key);
        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
