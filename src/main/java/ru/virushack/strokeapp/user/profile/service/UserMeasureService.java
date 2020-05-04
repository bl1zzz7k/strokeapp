package ru.virushack.strokeapp.user.profile.service;

import ru.virushack.strokeapp.user.profile.domain.User;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialDTO;
import ru.virushack.strokeapp.user.profile.domain.dto.UserScaleResultDTO;

import java.util.List;

public interface UserMeasureService {

    List<RehabilitationMaterialDTO> getMeasureListForUserType(String userType, String measureType);

    RehabilitationMaterialDTO getScaleByTitleAndVersionAndUserType(RehabilitationMaterialDTO requestDTO, String userType, String measureType);

    String evaluateAndSaveMeasure(UserScaleResultDTO dto, User user, String measureType);
}
