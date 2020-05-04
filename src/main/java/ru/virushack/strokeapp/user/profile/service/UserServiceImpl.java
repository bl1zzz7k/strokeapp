package ru.virushack.strokeapp.user.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.virushack.strokeapp.user.profile.domain.User;
import ru.virushack.strokeapp.user.profile.domain.dto.UserDTO;
import ru.virushack.strokeapp.user.profile.repository.UserRepository;
import ru.virushack.strokeapp.user.profile.service.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException {
        if (usernameExist(userDTO)){
            throw new UserAlreadyExistException(
                    "There is an account with that email address: " +  userDTO.getEmail());
        }
        User newUser = UserMapper.INSTANCE.toUser(userDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.insert(newUser);

    }

    private boolean usernameExist(UserDTO userDTO) {
        return userRepository.findByLogin(userDTO.getLogin()).isPresent();
    }
}
