package ru.virushack.strokeapp.user.profile.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.virushack.strokeapp.user.profile.domain.UserRehabilitationVideoProfile;

public interface RehabilitationProfileRepository extends MongoRepository<UserRehabilitationVideoProfile, String> {

    UserRehabilitationVideoProfile getUserRehabilitationProfileByUserId(ObjectId id);
}
