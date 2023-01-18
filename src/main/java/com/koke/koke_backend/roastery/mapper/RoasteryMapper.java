package com.koke.koke_backend.roastery.mapper;

import com.koke.koke_backend.common.mapper.EntityMapper;
import com.koke.koke_backend.roastery.dto.ImgUrlDto;
import com.koke.koke_backend.roastery.dto.RoasteryDataDto;
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

	@IterableMapping(qualifiedByName = "toEntity")
	List<Roastery> toEntityList(List<RoasteryDataDto> roasteryDataDtos);

	@Named("toEntity")
	@Mapping(target = "id", source = "dto", qualifiedByName = "getId")
	@Mapping(target = "roasteryNm", source = "name")
	@Mapping(target = "description", source = "summary")
	@Mapping(target = "awards", source = "awards", qualifiedByName = "getAwards")
	@Mapping(target = "location", source = "address")
	@Mapping(target = "photoImgUrl", source = "imgUrls", qualifiedByName = "getPhotoImgUrl")
	@Mapping(target = "logoImgUrl", source = "iconUrl")
	@Mapping(target = "webUrl" , source = "webSite")
	@Mapping(target = "snsUrl", source = "instagram")
	Roastery toEntity(RoasteryDataDto dto);

	@Named("getId")
	default String getId(RoasteryDataDto dto) {
		return System.currentTimeMillis() + dto.getName();
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
