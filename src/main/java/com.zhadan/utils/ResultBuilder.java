package com.zhadan.utils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 26.01.14
 * Time: 12:45
 */
public interface ResultBuilder<T> {
    ResultBuilder<T> withOffset(int offset);

    ResultBuilder<T> withLimit(int limit);

    ResultBuilder<T> orderedBy(String property);

    List<T> result();
}
