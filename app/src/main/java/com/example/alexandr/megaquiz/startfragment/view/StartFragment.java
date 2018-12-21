package com.example.alexandr.megaquiz.startfragment.view;

import android.content.Context;
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
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;

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
    private Context mContext;
    private StartFragmentContract.Router mRouter;

    boolean mVisible = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getParentFragment() instanceof StartFragmentContract.Router) {
            mRouter = (StartFragmentContract.Router) getParentFragment();
        } else if (getActivity() instanceof StartFragmentContract.Router) {
            mRouter = (StartFragmentContract.Router) getActivity();
        } else {
            throw new IllegalStateException("Parent container must be StartFragmentContract.Router");
        }
        App.getApp(mContext).getComponentsHolder().getStartFragmentComponent(this, mRouter).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, null);
        mUnbinder = ButterKnife.bind(this, view);
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
        App.getApp(mContext).getComponentsHolder().releaseStartFragmentComponent();
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
                mPresenter.onTestButton();
        }
    }

    public static StartFragment newInstance() {
        Bundle args = new Bundle();
        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
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
}
