package com.example.alexandr.megaquiz.quizStorage;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 22.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class RecyclerAdapterDiffUtilCallback extends DiffUtil.Callback {
    private List<QuizStorageItem> oldList;
    private List<QuizStorageItem> newList;

    public RecyclerAdapterDiffUtilCallback(List<QuizStorageItem> oldList, List<QuizStorageItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        QuizStorageItem oldStorage = oldList.get(oldItemPosition);
        QuizStorageItem newStorage = newList.get(newItemPosition);
        return oldStorage.getPosition() == newStorage.getPosition();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        QuizStorageItem oldStorage = oldList.get(oldItemPosition);
        QuizStorageItem newStorage = newList.get(newItemPosition);
        return oldStorage.getNameOfItem().equals(newStorage.getNameOfItem())
                && oldStorage.getCategorySize() == newStorage.getCategorySize() && oldStorage.getPosition() == newStorage.getPosition();
    }
}
