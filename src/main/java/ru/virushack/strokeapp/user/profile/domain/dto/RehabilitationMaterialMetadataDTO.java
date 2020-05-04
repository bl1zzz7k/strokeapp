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
public class RehabilitationMaterialMetadataDTO implements Serializable {
    private static final long serialVersionUID = 7035915260433970840L;
    private String type;
    @JsonProperty("scale_type")
    private String scaleType;
    private List<RehabilitationMaterialMetadataResultDTO> result;
}
