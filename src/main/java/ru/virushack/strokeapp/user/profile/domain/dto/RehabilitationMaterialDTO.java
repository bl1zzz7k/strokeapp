package ru.virushack.strokeapp.user.profile.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RehabilitationMaterialDTO implements Serializable {
    private static final long serialVersionUID = -889725778440324748L;
    private String title;
    private String version;
    private String guide;
    @JsonProperty("ref_guide")
    private String refGuide;
    private String description;
    @JsonProperty("question_list")
    private List<RehabilitationMaterialQuestionDTO> questionList;
    private List<RehabilitationMaterialVideoDTO> videos;
    private List<RehabilitationMaterialDTO> subtopics;
    private List<String> categories;
    private RehabilitationMaterialMetadataDTO metadata;
}
