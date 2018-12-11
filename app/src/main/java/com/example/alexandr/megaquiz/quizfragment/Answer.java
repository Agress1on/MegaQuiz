package com.example.alexandr.megaquiz.quizfragment;

/**
 * Created by Alexandr Mikhalev on 11.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public enum Answer {
    TRUE(true),
    FALSE(false);

    private boolean result;

    Answer(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
