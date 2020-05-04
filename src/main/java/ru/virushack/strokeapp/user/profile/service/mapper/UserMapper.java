package ru.virushack.strokeapp.user.profile.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.virushack.strokeapp.user.profile.domain.User;
import ru.virushack.strokeapp.user.profile.domain.dto.UserDTO;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO dto);
}
