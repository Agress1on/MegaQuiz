package com.example.alexandr.megaquiz.quizStorageActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 25.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<String> mCategoriesNames;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTVName;
        public TextView mTVPosition;
        public TextView mTVQuantity;
        public TextView mTVQuantityNumber;
        public String mCatName;


        public ViewHolder(View v) {
            super(v);
            mTVName = (TextView) v.findViewById(R.id.name_item_recycler);
            mTVPosition = (TextView) v.findViewById(R.id.position_item_recycler);
            mTVQuantity = (TextView) v.findViewById(R.id.quantity_item_recycler);
            mTVQuantityNumber = (TextView) v.findViewById(R.id.number_quantity_item_recycler);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),  mCatName + " Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    public RecyclerAdapter(List<String> categoriesNames) {
        mCategoriesNames = categoriesNames;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_storage_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
            holder.mTVName.setText(mCategoriesNames.get(position));
            holder.mTVPosition.setText(String.valueOf(position + 1));
            Map<String, List<Question>> map = new BankQuestion().getBankQuestion(); // это прям не нравится
            for (Map.Entry<String, List<Question>> entry : map.entrySet()) {
                if (mCategoriesNames.get(position).equals(entry.getKey())) {
                    holder.mTVQuantityNumber.setText(String.valueOf(entry.getValue().size()));
                    holder.mCatName = entry.getKey();
                }
            }
    }

    @Override
    public int getItemCount() {
        return mCategoriesNames.size();
    }
}
