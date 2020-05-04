package ru.virushack.strokeapp.user.profile.controller;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.virushack.strokeapp.common.payload.Response;
import ru.virushack.strokeapp.user.profile.domain.User;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialVideoDTO;
import ru.virushack.strokeapp.user.profile.service.UserProfileService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<Response<Map<String, List<RehabilitationMaterialVideoDTO>>>> getVideoProfileById(@AuthenticationPrincipal UserDetails userDetails) {
        log.debug("on getVideoProfileById()");
        ObjectId userId = ((User) userDetails).getId();
        Map<String, List<RehabilitationMaterialVideoDTO>> result = userProfileService.getUserVideoProfileWithCategory(userId);
        Response<Map<String, List<RehabilitationMaterialVideoDTO>>> response = new Response<>(result);
        return ResponseEntity.ok(response);
    }


}
