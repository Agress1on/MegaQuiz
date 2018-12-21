package com.example.alexandr.megaquiz.quizstoragefragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.app.App;
import com.example.alexandr.megaquiz.quizrouter.QuizRouter;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageContract;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageFragment extends Fragment implements QuizStorageContract.View {

    @Inject
    QuizStorageContract.Presenter mPresenter;

    @BindView(R.id.list_switch)
    Switch mSwitch;

    @BindView(R.id.text_for_switch)
    TextView mTextView;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
 //   private RecyclerView.LayoutManager mLayoutManager;
    private GridLayoutManager mLayoutManager;
    private List<QuizStorageItem> mCat;

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
        /*
        mPresenter = new QuizStoragePresenter(this, new QuizStorageInteractor(new BankQuestion()));
        DaggerAppComponent.builder()
                .appModule(new AppModule(getContext()))
                .build()
                .createQuizStorageFragmentComponent(new QuizStorageFragmentPresenterModule(this))
                .inject(this);
        */
        App.getApp(mContext).getComponentsHolder().getQuizStorageFragmentComponent(this).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_storage, null);
        mUnbinder = ButterKnife.bind(this, view);
        FragmentActivity fragmentActivity = getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.storage_recycler);

     //   mLayoutManager = new LinearLayoutManager(fragmentActivity); // XMMMMM
        mLayoutManager = new GridLayoutManager(fragmentActivity, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(mCat, key -> mPresenter.onClick(key));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        App.getApp(mContext).getComponentsHolder().releaseQuizStorageFragmentComponent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void initListForRecyclerAdapter(List<QuizStorageItem> list) {
        mCat = list;
    }

    @Override
    public void startActivityQuizView(String key) {
        Intent intent = QuizRouter.getIntent(getContext(), key);
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

    @OnCheckedChanged({R.id.list_switch})
    void onSelected(Switch button, boolean checked) {
        mPresenter.onCheckBoxClick(checked);
    }

    public static QuizStorageFragment newInstance() {
        Bundle args = new Bundle();
        QuizStorageFragment fragment = new QuizStorageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}



