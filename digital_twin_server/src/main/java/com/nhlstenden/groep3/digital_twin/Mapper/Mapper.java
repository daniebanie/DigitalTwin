package com.nhlstenden.groep3.digital_twin.Mapper;

/**
 *Interface voor standaard mappers
 *
 * @param <T> DTO class type
 * @param <U> Entity class type
 */
public interface Mapper<T, U> {
    T toDTO(U u);

    U toEntity(T t);
}
