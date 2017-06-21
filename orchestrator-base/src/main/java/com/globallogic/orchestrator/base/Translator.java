package com.globallogic.orchestrator.base;

public interface Translator<T, R> {

    R getDto(T model);

    T fromDto(R dto);
}
