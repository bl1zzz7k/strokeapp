package ru.virushack.strokeapp.user.profile.service;

import org.bson.types.ObjectId;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialVideoDTO;

import java.util.List;
import java.util.Map;

public interface UserProfileService {
    Map<String, List<RehabilitationMaterialVideoDTO>> getUserVideoProfileWithCategory(ObjectId userId);
}
