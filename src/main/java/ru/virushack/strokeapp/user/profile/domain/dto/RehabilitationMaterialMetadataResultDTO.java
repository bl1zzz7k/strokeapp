package ru.virushack.strokeapp.user.profile.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RehabilitationMaterialMetadataResultDTO implements Serializable {
    private static final long serialVersionUID = -5495193539408814659L;
    private String measure;
    private String message;
    private List<RehabilitationMaterialVideoDTO> videos;
}
