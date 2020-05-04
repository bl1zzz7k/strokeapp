package ru.virushack.strokeapp.user.profile.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.virushack.strokeapp.user.profile.domain.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String > {

    @Query("{id:'?0'}")
    User findById(ObjectId id);

    Optional<User> findByLogin(String login);
}
