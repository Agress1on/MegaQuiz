package com.example.alexandr.megaquiz.quizresultfragment.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Mikhalev on 13.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.ViewHolder> {
    private List<QuizResultItem> mQuizResultItemList;

    public QuizResultAdapter(List<QuizResultItem> quizResultItemList) {
        mQuizResultItemList = quizResultItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_result_item_first,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
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
