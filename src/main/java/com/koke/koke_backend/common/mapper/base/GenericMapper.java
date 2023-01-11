package com.koke.koke_backend.common.mapper.base;

public interface GenericMapper<E, D> {

	D toDto(E entity);

	E toEntity(D dto);

}
