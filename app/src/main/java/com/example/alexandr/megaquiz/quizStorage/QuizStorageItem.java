package com.example.alexandr.megaquiz.quizStorage;

/**
 * Created by Alexandr Mikhalev on 22.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizStorageItem {
    private int mPosition;
    private String mNameOfItem;
    private int mCategorySize;

    public QuizStorageItem(int position, String nameOfItem, int categorySize) {
        this.mPosition = position;
        this.mNameOfItem = nameOfItem;
        this.mCategorySize = categorySize;
    }

    public String getNameOfItem() {
        return mNameOfItem;
    }

    public int getCategorySize() {
        return mCategorySize;
    }

    public int getPosition() {
        return mPosition;
    }
}
