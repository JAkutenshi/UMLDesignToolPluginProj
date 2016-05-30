package com.jakutenshi.projects.umlplugin.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAkutenshi on 30.05.2016.
 */
public class Observable<T> {

    private List<Observer<T>> observerList = new ArrayList<>();

    private T object;

    public Observable(final T object) {
        this.object = object;
    }

    public void notifyChanged() {
        observerList.stream().forEach(o -> o.onChange(object));
    }

    public void addObserver(@NotNull  final Observer<T> observer) {
        observerList.add(observer);
    }

    public void removeObserver(@NotNull  final Observer<T> observer) {
        observerList.remove(observer);
    }


}
