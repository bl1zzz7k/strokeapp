package ru.virushack.strokeapp.user.profile.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RehabilitationMaterialVideoDTO implements Serializable {
    private static final long serialVersionUID = 4051751231340821374L;
    private String title;
    private String url;
}
