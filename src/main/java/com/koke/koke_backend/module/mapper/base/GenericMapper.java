package com.koke.koke_backend.module.mapper.base;

public interface GenericMapper<E, D> {

	D toDto(E entity);

	E toEntity(D dto);

}
