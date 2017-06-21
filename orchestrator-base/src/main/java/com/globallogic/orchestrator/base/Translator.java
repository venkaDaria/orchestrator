package com.globallogic.orchestrator.base;

/**
 * Translates model to dto and backwards
 * @param <T> a model
 * @param <R> a dto
 */
public interface Translator<T, R> {

    R getDto(T model);

    T fromDto(R dto);
}
