package ru.virushack.strokeapp.user.profile.domain;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document
public class UserRehabilitationVideoProfile {
    @Id
    private ObjectId id;
    @Indexed
    private ObjectId userId;
    private List<RehabilitationMaterialVideo> rehabilitationMaterialVideoList;


    public boolean addVideoList(List<RehabilitationMaterialVideo> videoList){
        boolean result = false;
        if (rehabilitationMaterialVideoList == null){
            rehabilitationMaterialVideoList = new ArrayList<>();
        }
        for (RehabilitationMaterialVideo video : videoList) {
            if (!rehabilitationMaterialVideoList.contains(video)) {
                result = true;
                rehabilitationMaterialVideoList.add(video);
            }
        }
        return result;
    }
}
