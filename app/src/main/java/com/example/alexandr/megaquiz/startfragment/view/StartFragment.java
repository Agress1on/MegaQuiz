package com.example.alexandr.megaquiz.startfragment.view;

import android.content.Context;
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

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.app.App;
import com.example.alexandr.megaquiz.app.AppModule;
import com.example.alexandr.megaquiz.app.DaggerAppComponent;
import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;
import com.example.alexandr.megaquiz.quizstoragefragment.view.QuizStorageFragment;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentPresenterModule;

import javax.inject.Inject;

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

    //  private StartFragmentContract.Presenter mPresenter;

    @Inject
    StartFragmentContract.Presenter mPresenter;

    @BindView(R.id.btn_randomQuiz)
    Button mRandomButton;

    @BindView(R.id.btn_category)
    Button mCategoryButton;

    @BindView(R.id.btn_test_general_questions)
    Button mTestButton;

    @BindView(R.id.doubt_image_view)
    ImageView mHeaderImageView;

    private Unbinder mUnbinder;
    boolean mVisible = true;

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  mPresenter = new StartFragmentPresenter(this, new StartFragmentInteractor(new BankQuestion()));
        /*
        DaggerAppComponent.builder()
                .appModule(new AppModule(getContext()))
                .build()
                .createStartFragmentComponent(new StartFragmentPresenterModule(this))
                .inject(this);
        */
        App.getApp(mContext).getComponentsHolder().getStartFragmentComponent(this).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, null);
        mPresenter.initRandomCategory();
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.initRandomCategory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        App.getApp(mContext).getComponentsHolder().releaseStartFragmentComponent();
    }

    @Override
    public void startQuizViewWithRandom(String randomCategory) {
        Intent intent = QuizActivity.getIntent(mContext, randomCategory);
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
