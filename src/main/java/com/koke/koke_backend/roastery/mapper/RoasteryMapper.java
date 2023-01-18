package com.koke.koke_backend.roastery.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.roastery.dto.ImgUrlDto;
import com.koke.koke_backend.roastery.dto.RoasteryCreateRequestDto;
import com.koke.koke_backend.roastery.dto.RoasteryDataDto;
import com.koke.koke_backend.roastery.entity.Roastery;
import org.mapstruct.*;

import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.UUID.randomUUID;

@Mapper(
		componentModel = "spring",
		uses = {EntityMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RoasteryMapper {

	@Mapping(target = "id", source = "dto", qualifiedByName = "getIdFromRoasteryCreateDto")
	Roastery createDtoToEntity(RoasteryCreateRequestDto dto);

	@Named("getIdFromRoasteryCreateDto")
	default String getIdFromRoasteryCreateDto(RoasteryCreateRequestDto dto) {
		return System.currentTimeMillis() + randomUUID().toString().substring(0, 5);
	}

	@IterableMapping(qualifiedByName = "toEntity")
	List<Roastery> toEntityList(List<RoasteryDataDto> roasteryDataDtos);

	@Named("toEntity")
	@Mapping(target = "id", source = "dto", qualifiedByName = "getIdFromRoasteryDataDto")
	@Mapping(target = "roasteryNm", source = "name")
	@Mapping(target = "description", source = "summary", qualifiedByName = "getDescriptionFromRoasteryDataDto")
	@Mapping(target = "awards", source = "awards", qualifiedByName = "getAwards")
	@Mapping(target = "location", source = "address")
	@Mapping(target = "photoImgUrl", source = "imgUrls", qualifiedByName = "getPhotoImgUrl")
	@Mapping(target = "logoImgUrl", source = "iconUrl")
	@Mapping(target = "webUrl" , source = "webSite")
	@Mapping(target = "snsUrl", source = "instagram")
	Roastery toEntity(RoasteryDataDto dto);

	@Named("getIdFromRoasteryDataDto")
	default String getIdFromRoasteryDataDto(RoasteryDataDto dto) {
		return System.currentTimeMillis() + randomUUID().toString().substring(0, 5);
	}

	@Named("getDescriptionFromRoasteryDataDto")
	default List<String> getDescription(String summary) {
		return List.of(summary.split(lineSeparator()));
	}

	@Named("getAwards")
	default List<String> getAwards(String awards) {
		return List.of(awards.split(lineSeparator()));
	}

	@Named("getPhotoImgUrl")
	default List<String> getPhotoImgUrl(List<ImgUrlDto> imgUrls) {
		return imgUrls.stream().map(ImgUrlDto::getSrc).toList();
	}
}
