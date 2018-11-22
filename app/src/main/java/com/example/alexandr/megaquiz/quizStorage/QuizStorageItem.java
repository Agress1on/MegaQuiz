package com.example.alexandr.megaquiz.quizStorage;

/**
 * Created by Alexandr Mikhalev on 22.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageItem {
    private String mNameOfItem;
    private int mCategorySize;

    public QuizStorageItem(String nameOfItem, int categorySize) {
        mNameOfItem = nameOfItem;
        mCategorySize = categorySize;
    }

    public String getNameOfItem() {
        return mNameOfItem;
    }

    public int getCategorySize() {
        return mCategorySize;
    }
}
