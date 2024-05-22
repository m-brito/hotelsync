package br.edu.ifsp.hotelsync.domain.usecases.utils;

import java.util.Collection;

public abstract class Validator<T> {
    public abstract Notification validate(T type);

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean nullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
