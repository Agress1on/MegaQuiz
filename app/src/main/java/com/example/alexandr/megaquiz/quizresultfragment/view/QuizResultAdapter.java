package com.example.alexandr.megaquiz.quizresultfragment.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Mikhalev on 13.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.ViewHolder> {

    private List<QuizResultItem> mQuizResultItemList = new ArrayList<>();

    private final static int VIEW_ITEM_TYPE_FIRST = 0;
    private final static int VIEW_ITEM_TYPE_SECOND = 1;

    public QuizResultAdapter() {
    }

    public void setData(List<QuizResultItem> quizResultItemList) {
        mQuizResultItemList = quizResultItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        boolean flag = mQuizResultItemList.get(position).isRealAnswer() == mQuizResultItemList.get(position).isUserAnswer();
        return flag ? VIEW_ITEM_TYPE_SECOND : VIEW_ITEM_TYPE_FIRST;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_ITEM_TYPE_FIRST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_result_item_first, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_result_item_second, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mQuestion.setText(mQuizResultItemList.get(position).getQuestion());
        holder.mRealAnswer.setText(String.valueOf(mQuizResultItemList.get(position).isRealAnswer()));
        holder.mUserAnswer.setText(String.valueOf(mQuizResultItemList.get(position).isUserAnswer()));
    }

    @Override
    public int getItemCount() {
        return mQuizResultItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.question_tv)
        TextView mQuestion;

        @BindView(R.id.static_tv_for_real_answer)
        TextView mRealAnswer;

        @BindView(R.id.static_tv_for_user_answer)
        TextView mUserAnswer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
