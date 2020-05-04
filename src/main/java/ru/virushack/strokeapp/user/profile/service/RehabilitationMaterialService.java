package ru.virushack.strokeapp.user.profile.service;

import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialDTO;

import java.util.List;

public interface RehabilitationMaterialService {
    List<RehabilitationMaterialDTO> getRehabilitationMaterialGuides();

    RehabilitationMaterialDTO getRehabilitationMaterial(RehabilitationMaterialDTO data);

    byte[] getRehabilitationMaterialRaw(String fileName);
}
