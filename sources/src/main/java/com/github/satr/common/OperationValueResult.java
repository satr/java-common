package com.github.satr.common;

public interface OperationValueResult<T> extends OperationResult {
    void setValue(T value);
    T getValue();
    OperationValueResult<T> withValue(T value);
}

