package com.agileengine.test.htmlcrawler;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class CollectionUtil {

    public static <E> List<E> innerJoin(Collection<E> left, Collection<E> right) {
        requireNonNull(left, "left");
        requireNonNull(right, "right");
        List<E> result = new ArrayList<>(left);
        result.retainAll(right);
        return result;
    }

    public static <T> T getSingleElement(Iterable<T> iterable) {
        requireNonNull(iterable);
        Iterator<T> iterator = iterable.iterator();
        T singleElement = iterator.next();
        if (iterator.hasNext()) {
            throw new IllegalStateException("Has more than one element.");
        }
        return singleElement;
    }
}
