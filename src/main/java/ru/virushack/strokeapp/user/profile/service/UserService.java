package ru.virushack.strokeapp.user.profile.service;

import ru.virushack.strokeapp.user.profile.domain.dto.UserDTO;

public interface UserService {
    void registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException;
}
