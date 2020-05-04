package ru.virushack.strokeapp.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.virushack.strokeapp.common.payload.Response;
import ru.virushack.strokeapp.common.request.Request;
import ru.virushack.strokeapp.user.profile.domain.dto.UserDTO;
import ru.virushack.strokeapp.user.profile.service.UserAlreadyExistException;
import ru.virushack.strokeapp.user.profile.service.UserService;

import javax.validation.Valid;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUserAccount(@Valid @RequestBody Request<UserDTO> requestBody) {
        try {
            UserDTO user = requestBody.getData();
            log.info("Registration new user: " + user.getLogin());
            userService.registerNewUserAccount(user);
        } catch (UserAlreadyExistException uaeEx) {
            Response<String> response = new Response<>(null);
            response.setMessage("Пользователь с таким именем уже существует.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.ok(null);
    }
}
