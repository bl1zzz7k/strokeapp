package ru.virushack.strokeapp.user.profile.service.mapper;

import ru.virushack.strokeapp.user.profile.domain.RehabilitationMaterialVideo;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialVideoDTO;

import java.util.ArrayList;
import java.util.List;

public class RehabilitationMaterialVideoMapperImpl implements RehabilitationMaterialVideoMapper {
    @Override
    public List<RehabilitationMaterialVideo> toEntityList(List<RehabilitationMaterialVideoDTO> dtoList, List<String> categories) {
        List<RehabilitationMaterialVideo> result = new ArrayList<>();
        if (dtoList == null) return result;
        dtoList.forEach(dto -> {
            RehabilitationMaterialVideo video = RehabilitationMaterialVideo.builder()
                    .title(dto.getTitle())
                    .categories(categories)
                    .url(dto.getUrl())
                    .build();
            result.add(video);
        });
        return result;
    }

    @Override
    public RehabilitationMaterialVideoDTO toDto(RehabilitationMaterialVideo entity) {
        return RehabilitationMaterialVideoDTO.builder()
                .title(entity.getTitle())
                .url(entity.getUrl())
                .build();
    }
}
