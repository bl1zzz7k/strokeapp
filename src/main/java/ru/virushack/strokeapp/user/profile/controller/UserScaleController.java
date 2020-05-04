package ru.virushack.strokeapp.user.profile.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.virushack.strokeapp.common.payload.Response;
import ru.virushack.strokeapp.common.request.Request;
import ru.virushack.strokeapp.user.profile.domain.User;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialDTO;
import ru.virushack.strokeapp.user.profile.domain.dto.UserScaleResultDTO;
import ru.virushack.strokeapp.user.profile.service.UserMeasureService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/scale")
public class UserScaleController {

    @Autowired
    UserMeasureService userMeasureService;

    @GetMapping(value = "/list")
    public ResponseEntity<Response<List<RehabilitationMaterialDTO>>> getScaleList(@AuthenticationPrincipal UserDetails userDetails) {
        log.debug("on getScaleList()");
        User principal = (User) userDetails;
        List<RehabilitationMaterialDTO> scaleList = userMeasureService.getMeasureListForUserType((principal).getUserRole().name(), "scale");
        Response<List<RehabilitationMaterialDTO>> response = new Response<>(scaleList);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/get")
    public ResponseEntity<Response<RehabilitationMaterialDTO>> getScale(@RequestBody Request<RehabilitationMaterialDTO> requestBody,
                                                                        @AuthenticationPrincipal UserDetails userDetails){
        log.debug("on getScale()");
        User principal = (User) userDetails;
        RehabilitationMaterialDTO result = userMeasureService.getScaleByTitleAndVersionAndUserType(requestBody.getData(), principal.getUserRole().toString(), "scale");
        Response<RehabilitationMaterialDTO> response = new Response<>(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Response<String>> saveScaleForUser(@RequestBody Request<UserScaleResultDTO> requestBody, @AuthenticationPrincipal UserDetails userDetails){
        log.debug("on saveScaleForUser()");
        User principal = (User) userDetails;
        String message = userMeasureService.evaluateAndSaveMeasure(requestBody.getData(), principal, "scale");
        Response<String> response = new Response<>(null);
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

}

