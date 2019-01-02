package com.example.alexandr.megaquiz.base;

/**
 * Created by Alexandr Mikhalev on 02.01.2019.
 *
 * @author Alexandr Mikhalev
 */
public interface FragmentComponentBuilder<C extends FragmentComponent, M extends FragmentModule> {
    C build();
    FragmentComponentBuilder<C, M> module(M module);
}
