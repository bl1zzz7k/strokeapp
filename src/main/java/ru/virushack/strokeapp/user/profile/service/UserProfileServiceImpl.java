package ru.virushack.strokeapp.user.profile.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.virushack.strokeapp.user.profile.domain.RehabilitationMaterialVideo;
import ru.virushack.strokeapp.user.profile.domain.UserRehabilitationVideoProfile;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialVideoDTO;
import ru.virushack.strokeapp.user.profile.repository.RehabilitationProfileRepository;
import ru.virushack.strokeapp.user.profile.service.mapper.RehabilitationMaterialVideoMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    RehabilitationProfileRepository rehabilitationProfileRepository;

    @Override
    public Map<String, List<RehabilitationMaterialVideoDTO>> getUserVideoProfileWithCategory(ObjectId userId) {
        Map<String, List<RehabilitationMaterialVideoDTO>> result = new HashMap<>();
        UserRehabilitationVideoProfile userVideoProfile = rehabilitationProfileRepository.getUserRehabilitationProfileByUserId(userId);
        List<RehabilitationMaterialVideo> videoList = userVideoProfile.getRehabilitationMaterialVideoList();
        if (videoList == null) return result;
        for (RehabilitationMaterialVideo video : videoList) {
            sortVideoListByCategories(result, video);
        }
        return result;
    }

    private void sortVideoListByCategories(Map<String, List<RehabilitationMaterialVideoDTO>> result, RehabilitationMaterialVideo video) {
        RehabilitationMaterialVideoDTO videoDTO = RehabilitationMaterialVideoMapper.INSTANCE.toDto(video);
        for (String category : video.getCategories()) {
            if (result.get(category) == null){
                ArrayList<RehabilitationMaterialVideoDTO> videos = new ArrayList<>();
                videos.add(videoDTO);
                result.put(category, videos);
            } else {
                result.get(category).add(videoDTO);
            }
        }
    }
}
