package ru.virushack.strokeapp.user.profile.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserScaleResultDTO implements Serializable {
    private static final long serialVersionUID = 7180255215169101357L;
    @JsonProperty("title")
    private String rehabilitationMaterialTitle;
    @JsonProperty("version")
    private String rehabilitationMaterialVersion;
    private int value;
}
