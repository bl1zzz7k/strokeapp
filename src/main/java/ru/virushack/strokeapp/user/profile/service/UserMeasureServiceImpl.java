package ru.virushack.strokeapp.user.profile.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.virushack.strokeapp.user.profile.domain.RehabilitationMaterialVideo;
import ru.virushack.strokeapp.user.profile.domain.User;
import ru.virushack.strokeapp.user.profile.domain.UserRehabilitationVideoProfile;
import ru.virushack.strokeapp.user.profile.domain.UserScaleResult;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialDTO;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialMetadataResultDTO;
import ru.virushack.strokeapp.user.profile.domain.dto.UserScaleResultDTO;
import ru.virushack.strokeapp.user.profile.repository.RehabilitationProfileRepository;
import ru.virushack.strokeapp.user.profile.repository.UserScaleResultRepository;
import ru.virushack.strokeapp.user.profile.service.mapper.RehabilitationMaterialVideoMapper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserMeasureServiceImpl implements UserMeasureService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    RehabilitationProfileRepository profileRepository;

    @Autowired
    UserScaleResultRepository scaleResultRepository;

    @Override
    public List<RehabilitationMaterialDTO> getMeasureListForUserType(String userType, String measureType) {
        List<RehabilitationMaterialDTO> result = new ArrayList<>();
        try {
            for (Resource resource : getScalesByUserRoleAndMeasureType(measureType, userType)) {
                RehabilitationMaterialDTO rehabilitationMaterialDTO = getUserScaleDTOFromResource(resource);
                RehabilitationMaterialDTO resultDTO = RehabilitationMaterialDTO.builder()
                        .title(rehabilitationMaterialDTO.getTitle())
                        .version(rehabilitationMaterialDTO.getVersion())
                        .build();
                result.add(resultDTO);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    private RehabilitationMaterialDTO getUserScaleDTOFromResource(Resource resource) throws IOException {
        File scaleFile = resource.getFile();
        ObjectMapper om = new ObjectMapper();
        return om.readValue(scaleFile, RehabilitationMaterialDTO.class);
    }

    private Resource[] getScalesByUserRoleAndMeasureType(String measureType, String userType) throws IOException {
        return applicationContext.getResources("classpath*:scale/" + userType + "/" + measureType + "*.json");
    }

    @Override
    public RehabilitationMaterialDTO getScaleByTitleAndVersionAndUserType(RehabilitationMaterialDTO requestDTO, String userType, String measureType) {
        RehabilitationMaterialDTO result = null;
        try {
            Resource[] scales = getScalesByUserRoleAndMeasureType(measureType, userType);
            for (Resource scale : scales) {
                RehabilitationMaterialDTO rehabilitationMaterialDTO = getUserScaleDTOFromResource(scale);
                if (rehabilitationMaterialDTO.getTitle().equals(requestDTO.getTitle()) && rehabilitationMaterialDTO.getVersion().equals(requestDTO.getVersion())) {
                    result = rehabilitationMaterialDTO;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public String evaluateAndSaveMeasure(UserScaleResultDTO dto, User user, String measureType) {
        String result = "";
        int value = dto.getValue();
        RehabilitationMaterialDTO materialDTO = RehabilitationMaterialDTO.builder()
                .title(dto.getRehabilitationMaterialTitle())
                .version(dto.getRehabilitationMaterialVersion())
                .build();

        RehabilitationMaterialDTO rehabilitationMaterial = getScaleByTitleAndVersionAndUserType(materialDTO, user.getUserRole().name(), measureType) ;
        List<RehabilitationMaterialMetadataResultDTO> metadataResultList = rehabilitationMaterial.getMetadata().getResult();
        for (RehabilitationMaterialMetadataResultDTO resultMeasure : metadataResultList) {
            if (resultMeasure.getMeasure().equals("default") && result.isEmpty()) {
                result = resultMeasure.getMessage();
            } else {
                Boolean eval = evaluateMeasureExpression(value, resultMeasure);
                if (eval) {
                    result = saveOrUpdateUserProfile(user, resultMeasure, rehabilitationMaterial.getCategories());

                }
            }
        }
        saveUserScaleResult(user, value, rehabilitationMaterial);
        return result;
    }

    private void saveUserScaleResult(User user, int value, RehabilitationMaterialDTO rehabilitationMaterial) {
        UserScaleResult userScaleResult = UserScaleResult.builder()
                .userId(user.getId())
                .scaleType(rehabilitationMaterial.getMetadata().getScaleType())
                .value(value)
                .build();

        scaleResultRepository.insert(userScaleResult);
    }

    private Boolean evaluateMeasureExpression(int value, RehabilitationMaterialMetadataResultDTO resultMeasure) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        Boolean eval = false;
        try {
            eval = (Boolean) engine.eval(value + resultMeasure.getMeasure());
        } catch (ScriptException e) {
            log.error(e.getMessage());
        }
        return eval;
    }

    private String saveOrUpdateUserProfile(User user, RehabilitationMaterialMetadataResultDTO resultMeasure, List<String> categories) {
        String result;
        result = resultMeasure.getMessage();
        List<RehabilitationMaterialVideo> videoList = RehabilitationMaterialVideoMapper.INSTANCE.toEntityList(resultMeasure.getVideos(), categories);
        UserRehabilitationVideoProfile userProfile = profileRepository.getUserRehabilitationProfileByUserId(user.getId());
        if (userProfile == null) {
            userProfile = UserRehabilitationVideoProfile.builder()
                    .userId(user.getId())
                    .rehabilitationMaterialVideoList(videoList)
                    .build();
            profileRepository.insert(userProfile);
        } else {
            boolean added = userProfile.addVideoList(videoList);
            if (!added) result = "Видео уже добавлено";
            profileRepository.save(userProfile);
        }
        return result;
    }
}
