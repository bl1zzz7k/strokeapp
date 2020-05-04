package ru.virushack.strokeapp.user.profile.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.virushack.strokeapp.user.profile.domain.RehabilitationMaterialVideo;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialVideoDTO;

import java.util.List;

@Mapper
public interface RehabilitationMaterialVideoMapper {
    RehabilitationMaterialVideoMapper INSTANCE = Mappers.getMapper(RehabilitationMaterialVideoMapper.class);

    List<RehabilitationMaterialVideo> toEntityList(List<RehabilitationMaterialVideoDTO> dtoList, List<String> categories);
    RehabilitationMaterialVideoDTO toDto(RehabilitationMaterialVideo entity);

}
