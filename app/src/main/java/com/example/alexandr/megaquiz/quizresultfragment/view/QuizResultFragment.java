package com.example.alexandr.megaquiz.quizresultfragment.view;

import android.content.Intent;
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
import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultActivityContract;
import com.example.alexandr.megaquiz.quizresultfragment.domain.QuizResultActivityInteractor;
import com.example.alexandr.megaquiz.quizresultfragment.presentation.QuizResultActivityPresenter;
import com.example.alexandr.megaquiz.startactivity.view.StartActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.restart_button)
    Button mRestartButton;
    @BindView(R.id.go_to_QS_button)
    Button mGoToQuizStorageButton;
    @BindView(R.id.go_to_start_button)
    Button mGoToStartButton;

    private int mQuizSize;
    private int mCorrectAnswers;
    private String mNameCategory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuizSize = getArguments().getInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_QUIZ_SIZE, 10);
        mCorrectAnswers = getArguments().getInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_CORRECT_ANSWERS, 5);
        mNameCategory = getArguments().getString(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_NAME_CATEGORY, "Error");
        mPresenter = new QuizResultActivityPresenter(this, new QuizResultActivityInteractor());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_quiz_result, null);
        ButterKnife.bind(this, view);
        setResultTextView(mPresenter.forResultTextView(mQuizSize, mCorrectAnswers, mNameCategory));
        return view;
    }

    @Override
    public void setResultTextView(String text) {
        mResultTextView.setText(text);
    }

    @OnClick({R.id.restart_button, R.id.go_to_QS_button, R.id.go_to_start_button})
    void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.restart_button:
                intent = QuizActivity.getIntent(getContext(), mNameCategory);
                break;
            case R.id.go_to_QS_button:
                //   intent = QuizStorageFragment.getIntent(this);
                break;
            case R.id.go_to_start_button:
                intent = StartActivity.getIntent(getContext());
                break;
        }
        startActivity(intent);
    }

    public static QuizResultFragment newInstance(int quizSize, int correctAnswers, String nameCategory) {
        Bundle args = new Bundle();
        args.putInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_QUIZ_SIZE, quizSize);
        args.putInt(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_CORRECT_ANSWERS, correctAnswers);
        args.putString(Constants.EXTRAS_FOR_INTENT_QUIZ_RESULT_NAME_CATEGORY, nameCategory);
        QuizResultFragment fragment = new QuizResultFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
