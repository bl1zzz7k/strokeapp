package ru.virushack.strokeapp.user.profile.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.virushack.strokeapp.common.payload.Response;
import ru.virushack.strokeapp.common.request.Request;
import ru.virushack.strokeapp.user.profile.domain.dto.RehabilitationMaterialDTO;
import ru.virushack.strokeapp.user.profile.service.RehabilitationMaterialService;

import java.util.List;

@RestController
@RequestMapping("/material")
@Slf4j
public class RehabilitationMaterialController {

    @Autowired
    RehabilitationMaterialService rehabilitationMaterialService;

    @GetMapping(value = "/list")
    public ResponseEntity<Response<List<RehabilitationMaterialDTO>>> getRehabilitationMaterialList(){
        log.debug("on getRehabilitationMaterialList()");
        List<RehabilitationMaterialDTO> materialDTOList = rehabilitationMaterialService.getRehabilitationMaterialGuides();
        Response<List<RehabilitationMaterialDTO>> response = new Response<>(materialDTOList);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/get")
    public ResponseEntity<?> getRehabilitationMaterial(@RequestBody Request<RehabilitationMaterialDTO> requestBody){
        log.debug("on getRehabilitationMaterial()");
        if (requestBody.getData().getRefGuide() != null){
            byte[] response = rehabilitationMaterialService.getRehabilitationMaterialRaw(requestBody.getData().getRefGuide());
            return ResponseEntity.ok(response);
        }
        RehabilitationMaterialDTO result = rehabilitationMaterialService.getRehabilitationMaterial(requestBody.getData());
        Response<RehabilitationMaterialDTO> response = new Response<>(result);
        return ResponseEntity.ok(response);
    }

}
