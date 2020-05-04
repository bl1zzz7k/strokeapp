package ru.virushack.strokeapp.user.profile.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRehabilitationVideoProfileDTO implements Serializable {
    private static final long serialVersionUID = -4115375691326205399L;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("video_list")
    private List<RehabilitationMaterialVideoDTO> videoList;
}
