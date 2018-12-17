package com.example.alexandr.megaquiz.quizactivity.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Alexandr Mikhalev on 17.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface QuizActivityScope {
}
