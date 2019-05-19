package com.github.satr.common;
// Copyright © 2019, github.com/satr, MIT License

public class OperationValueResultImpl<T> extends OperationResultImpl implements OperationValueResult<T> {
    private T value;

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public OperationValueResult<T> withValue(T value) {
        setValue(value);
        return this;
    }
}