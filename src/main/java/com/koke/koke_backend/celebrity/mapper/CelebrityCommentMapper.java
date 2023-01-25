package com.koke.koke_backend.celebrity.mapper;

import com.koke.koke_backend.celebrity.dto.json.CelebrityCommentDataDto;
import com.koke.koke_backend.celebrity.entity.CelebrityComment;
import com.koke.koke_backend.common.mapper.EntityMapper;
import org.mapstruct.*;

import java.util.List;

import static java.lang.System.lineSeparator;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CelebrityCommentMapper {

	@IterableMapping(qualifiedByName = "toEntity")
	List<CelebrityComment> toEntityList(List<CelebrityCommentDataDto> dtoList);

	@Named("toEntity")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "celebrityName", source = "name")
	@Mapping(target = "celebrityJob", source = "job")
	@Mapping(target = "comment", source = "detail", qualifiedByName = "getCommentFromCelebrityCommentDataDto")
	@Mapping(target = "instagramId", source = "instad")
	CelebrityComment toEntity(CelebrityCommentDataDto dto);

	@Named("getCommentFromCelebrityCommentDataDto")
	default List<String> getDescription(String detail) {
		return List.of(detail.split(lineSeparator()));
	}

}
