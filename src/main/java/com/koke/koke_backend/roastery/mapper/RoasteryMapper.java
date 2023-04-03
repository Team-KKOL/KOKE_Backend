package com.koke.koke_backend.roastery.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.roastery.dto.RoasteryCreateRequestDto;
import com.koke.koke_backend.roastery.dto.json.ImgUrlDto;
import com.koke.koke_backend.roastery.dto.json.RoasteryDataDto;
import com.koke.koke_backend.roastery.entity.Roastery;
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
public interface RoasteryMapper {

	@Mapping(target = "uuid", source = "dto", qualifiedByName = "uuid")
	Roastery createDtoToEntity(RoasteryCreateRequestDto dto);

	@IterableMapping(qualifiedByName = "toEntity")
	List<Roastery> toEntityList(List<RoasteryDataDto> roasteryDataDtos);

	@Named("toEntity")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uuid", source = "dto", qualifiedByName = "uuid")
	@Mapping(target = "description", source = "summary", qualifiedByName = "description")
	@Mapping(target = "awards", source = "awards", qualifiedByName = "awards")
	@Mapping(target = "location", source = "address")
	@Mapping(target = "photoImgUrl", source = "imgUrls", qualifiedByName = "photoImgUrl")
	@Mapping(target = "logoImgUrl", source = "iconUrl")
	@Mapping(target = "webUrl" , source = "webSite")
	@Mapping(target = "snsUrl", source = "instagram")
	Roastery toEntity(RoasteryDataDto dto);

	@Named("description")
	default List<String> getDescription(String summary) {
		return List.of(summary.split(lineSeparator()));
	}

	@Named("awards")
	default List<String> getAwards(String awards) {
		return List.of(awards.split(lineSeparator()));
	}

	@Named("photoImgUrl")
	default List<String> getPhotoImgUrl(List<ImgUrlDto> imgUrls) {
		return imgUrls.stream().map(ImgUrlDto::getSrc).toList();
	}
}
