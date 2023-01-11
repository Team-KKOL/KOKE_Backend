package com.koke.koke_backend.common.mapper;

public interface GenericMapper<E, D> {

	D toDto(E entity);

	E toEntity(D dto);

}
