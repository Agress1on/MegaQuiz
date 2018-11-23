package com.example.alexandr.megaquiz.quizStorage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandr.megaquiz.R;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 25.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<QuizStorageItem> mCategoriesNames;
    OnItemClickListener mOnItemClickListener;
    private final static int VIEW_ITEM_FIRST = 0;
    private final static int VIEW_ITEM_SECOND = 1;


    public RecyclerAdapter(List<QuizStorageItem> categoriesNames, OnItemClickListener onItemClickListener) {
        mCategoriesNames = categoriesNames;
        mOnItemClickListener = onItemClickListener;
    }

    public List<QuizStorageItem> getData() {
        return mCategoriesNames;
    }

    public void setData(List<QuizStorageItem> categoriesNames) {
        mCategoriesNames = categoriesNames;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? VIEW_ITEM_FIRST : VIEW_ITEM_SECOND;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == VIEW_ITEM_FIRST) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_storage_item_first, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_storage_item_second, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.mTVName.setText(mCategoriesNames.get(position).getNameOfItem());
        holder.mTVPosition.setText(String.valueOf(mCategoriesNames.get(position).getPosition()));
        holder.mTVQuantityQuestionOfQuiz.setText(String.valueOf(mCategoriesNames.get(position).getCategorySize()));
        holder.mCatName = mCategoriesNames.get(position).getNameOfItem();

        /*
        for (Map.Entry<String, Integer> entry : mMapOfNamesAndSize.entrySet()) {
            if (mCategoriesNames.get(position).equals(entry.getKey())) {
                holder.mTVQuantityQuestionOfQuiz.setText(String.valueOf(entry.getValue()));
                holder.mCatName = entry.getKey();
            }
        }
        */

    }

    @Override
    public int getItemCount() {
        return mCategoriesNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //   @BindView(R.id.name_item_recycler)
        TextView mTVName;
        //    @BindView(R.id.position_item_recycler)
        TextView mTVPosition;
        //   @BindView(R.id.quantity_item_recycler)
        TextView mTVQuantity;

        //   @BindView(R.id.number_quantity_item_recycler)

        TextView mTVQuantityQuestionOfQuiz;

        public String mCatName;

        public ViewHolder(View v) {
            super(v);

            mTVName = (TextView) v.findViewById(R.id.name_item_recycler);
            mTVPosition = (TextView) v.findViewById(R.id.position_item_recycler);
            mTVQuantity = (TextView) v.findViewById(R.id.quantity_item_recycler);
            mTVQuantityQuestionOfQuiz = (TextView) v.findViewById(R.id.number_quantity_item_recycler);
            //      ButterKnife.bind(v);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String toastMessage = "КАТЕГОРИЯ ПУСТАЯ! СКОРО БУДЕТ ДОСТУПНА!";
            if (checkEmptyCategory(mCatName)) {
                toastMessage = mCatName + " Clicked!";
                mOnItemClickListener.onClick(mCatName);
            }
            Toast.makeText(view.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }

        private boolean checkEmptyCategory(String categoryName) {
            int categorySize = 0;

            for (QuizStorageItem quizStorageItem : mCategoriesNames) {
                if (quizStorageItem.getNameOfItem().equals(categoryName))
                    categorySize = quizStorageItem.getCategorySize();
            }

            /*
            for (Map.Entry<String, Integer> entry : mMapOfNamesAndSize.entrySet()) {
                if (entry.getKey().equals(categoryName)) categorySize = entry.getValue();
            }
            */

            return categorySize > 0;
        }
    }

    interface OnItemClickListener {
        void onClick(String key);
    }
}
