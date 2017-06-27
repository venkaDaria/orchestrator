package com.globallogic.orchestrator.base;

/**
 * Translates model to dto and backwards
 * @param <T> a model
 * @param <R> a dto
 */
public interface Translator<T, R> {

    R getDto(final T model);

    T fromDto(final R dto);
}
