package ru.virushack.strokeapp.user.profile.service.mapper;

import ru.virushack.strokeapp.user.profile.domain.User;
import ru.virushack.strokeapp.user.profile.domain.dto.UserDTO;

public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(UserDTO dto) {
        return User.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .userRole(dto.getUserRole())
                .build();
    }
}
