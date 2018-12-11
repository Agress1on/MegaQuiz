package com.example.alexandr.megaquiz.quizfragment.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;
import com.example.alexandr.megaquiz.quizfragment.domain.QuizFragmentInteractor;
import com.example.alexandr.megaquiz.quizfragment.presentation.QuizFragmentPresenter;
import com.example.alexandr.megaquiz.quizresultactivity.view.QuizResultFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizFragment extends Fragment implements QuizFragmentContract.View {

    QuizFragmentContract.Presenter mPresenter;


    @BindView(R.id.static_category_name)
    TextView mStaticCategoryNameTextView;
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


    private String mCategoryName = "SENYAAAAA";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_quiz, null);
            ButterKnife.bind(this, view);
            //достать активатор стринга с категорией
            mCategoryName = getArguments().getString(Constants.EXTRAS_FOR_INTENT_QUIZ_VIEW);
            mPresenter = new QuizFragmentPresenter(this, new QuizFragmentInteractor(new BankQuestion()));
            mPresenter.prepareViewForFirstQuestion();
            return view;
    }

    @Override
    public String sentToPresenterSelectedCategory() {
        return mCategoryName;
    }

    @Override
    public void setQuestionTextView(String text) {
        mQuestionTV.setText(text);
    }

    @Override
    public void switchButton(boolean result) {
        mTrueButton.setEnabled(result);
        mFalseButton.setEnabled(result);
    }

    @Override
    public void setCorrectButtonStyle(int key) {
        int forTrueButton = 0;
        int forFalseButton = 0;
        switch (key) {
            case Constants.NOT_PUSH_TRUE_AND_FALSE_BUTTONS :
                forTrueButton = R.drawable.shape_for_true_button;
                forFalseButton = R.drawable.shape_for_false_button;
                break;
            case Constants.PUSH_TRUE_BUTTON :
                forTrueButton = R.drawable.shape_for_true_button_answered;
                forFalseButton = R.drawable.shape_for_false_button_not_answered;
                break;
            case Constants.PUSH_FALSE_BUTTON :
                forTrueButton = R.drawable.shape_for_true_button_not_answered;
                forFalseButton = R.drawable.shape_for_false_button_answered;
                break;
        }
        mTrueButton.setBackground(getResources().getDrawable(forTrueButton));
        mFalseButton.setBackground(getResources().getDrawable(forFalseButton));
    }

    @Override
    public void setQuestionCount(String text) {
        mQuestionCount.setText(text);
    }

    @Override
    public void startQuizResultActivity(int quizSize, int correctAnswers) {
        /*
        Intent intent = QuizResultActivity.getIntent(getContext(), quizSize, correctAnswers, mCategoryName);
        startActivity(intent);
        */
        QuizResultFragment quizResultFragment = QuizResultFragment.newInstance(quizSize, correctAnswers, mCategoryName);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_for_quiz, quizResultFragment)
                .commit();
    }

    @OnClick({R.id.btnTrue, R.id.btnFalse, R.id.btnNext, R.id.btnPrev})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTrue:
                mPresenter.onTrueButton();
                break;
            case R.id.btnFalse:
                mPresenter.onFalseButton();
                break;
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
