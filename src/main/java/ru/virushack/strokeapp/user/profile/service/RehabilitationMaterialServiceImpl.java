package ru.virushack.strokeapp.user.profile.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialDTO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RehabilitationMaterialServiceImpl implements RehabilitationMaterialService {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public List<RehabilitationMaterialDTO> getRehabilitationMaterialGuides() {
        List<RehabilitationMaterialDTO> result = new ArrayList<>();
        try {
            for (Resource resource : getRehabilitationList()) {
                RehabilitationMaterialDTO rehabilitationMaterialDTO = getUserScaleDTOFromResource(resource);
                RehabilitationMaterialDTO resultDTO = RehabilitationMaterialDTO.builder()
                        .title(rehabilitationMaterialDTO.getTitle())
                        .version(rehabilitationMaterialDTO.getVersion())
                        .refGuide(rehabilitationMaterialDTO.getRefGuide())
                        .build();
                result.add(resultDTO);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public RehabilitationMaterialDTO getRehabilitationMaterial(RehabilitationMaterialDTO requestDTO) {
        RehabilitationMaterialDTO result = null;
        try {
            Resource[] scales = getRehabilitationList();
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

    private RehabilitationMaterialDTO getUserScaleDTOFromResource(Resource resource) throws IOException {
        File scaleFile = resource.getFile();
        ObjectMapper om = new ObjectMapper();
        return om.readValue(scaleFile, RehabilitationMaterialDTO.class);
    }

    private Resource[] getRehabilitationList() throws IOException {
        return applicationContext.getResources("classpath*:guide/topic_*.json");
    }


    @Override
    public byte[] getRehabilitationMaterialRaw(String fileName) {
        Resource resource = applicationContext.getResource("classpath:guide/" + fileName);
        byte[] result = null;
        try (InputStream in = resource.getInputStream()) {
            result = in.readAllBytes();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
