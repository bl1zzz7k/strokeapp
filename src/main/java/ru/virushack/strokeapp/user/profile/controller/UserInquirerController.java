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
@RequestMapping("/inquirer")
public class UserInquirerController {
    @Autowired
    UserMeasureService userMeasureService;

    @GetMapping(value = "/list")
    public ResponseEntity<Response<List<RehabilitationMaterialDTO>>> getInquirerList(@AuthenticationPrincipal UserDetails userDetails) {
        log.debug("on getInquirerList()");
        User principal = (User) userDetails;
        List<RehabilitationMaterialDTO> scaleList = userMeasureService.getMeasureListForUserType((principal).getUserRole().name(), "inquirer");
        Response<List<RehabilitationMaterialDTO>> response = new Response<>(scaleList);
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/get")
    public ResponseEntity<Response<RehabilitationMaterialDTO>> getInquirer(@RequestBody Request<RehabilitationMaterialDTO> requestBody,
                                                                           @AuthenticationPrincipal UserDetails userDetails){
        log.debug("on getInquirer()");
        User principal = (User) userDetails;
        RehabilitationMaterialDTO result = userMeasureService.getScaleByTitleAndVersionAndUserType(requestBody.getData(), principal.getUserRole().toString(), "inquirer");
        Response<RehabilitationMaterialDTO> response = new Response<>(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Response<String>> saveInquirerForUser(@RequestBody Request<UserScaleResultDTO> requestBody, @AuthenticationPrincipal UserDetails userDetails){
        log.debug("on saveInquirerForUser()");
        User principal = (User) userDetails;
        String message = userMeasureService.evaluateAndSaveMeasure(requestBody.getData(), principal, "");
        Response<String> response = new Response<>(null);
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }
}
