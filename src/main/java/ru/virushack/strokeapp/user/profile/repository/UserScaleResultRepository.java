package ru.virushack.strokeapp.user.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.virushack.strokeapp.user.profile.domain.UserScaleResult;

public interface UserScaleResultRepository extends MongoRepository<UserScaleResult, String> {
}
