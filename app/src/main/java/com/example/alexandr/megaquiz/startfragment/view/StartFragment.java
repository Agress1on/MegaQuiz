package com.example.alexandr.megaquiz.startfragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;
import com.example.alexandr.megaquiz.quizstoragefragment.view.QuizStorageFragment;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;
import com.example.alexandr.megaquiz.startfragment.domain.StartFragmentInteractor;
import com.example.alexandr.megaquiz.startfragment.presentation.StartFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartFragment extends Fragment implements StartFragmentContract.View {

    private StartFragmentContract.Presenter mPresenter;
    private Unbinder mUnbinder;
    boolean mVisible = true;

    @BindView(R.id.btn_randomQuiz)
    Button mRandomButton;

    @BindView(R.id.btn_category)
    Button mCategoryButton;

    @BindView(R.id.btn_test_general_questions)
    Button mTestButton;

    @BindView(R.id.doubt_image_view)
    ImageView mHeaderImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, null);
        mPresenter = new StartFragmentPresenter(this, new StartFragmentInteractor(new BankQuestion()));
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
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
    }

    @Override
    public void startQuizViewWithRandom(String randomCategory) {
        Intent intent = QuizActivity.getIntent(getContext(), randomCategory);
        startActivity(intent);
    }

    @Override
    public void startQuizStorage() {
        QuizStorageFragment fragment = QuizStorageFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.linear_for_edit_for_nd, fragment)
                .commit();
    }

    private void animation() {
        /*
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.start_constraint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(viewGroup, new Slide(Gravity.RIGHT));
        }
        mVisible = !mVisible;
        mHeaderImageView.setVisibility(mVisible ? View.VISIBLE : View.GONE);
        */
    }

    @OnClick({R.id.btn_randomQuiz, R.id.btn_category, R.id.btn_test_general_questions})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_randomQuiz:
                mPresenter.onRandomButton();
                break;
            case R.id.btn_category:
                mPresenter.onButtonCategory();
                break;
            case R.id.btn_test_general_questions:
                animation();
        }
    }

    public static StartFragment newInstance() {
        Bundle args = new Bundle();
        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
