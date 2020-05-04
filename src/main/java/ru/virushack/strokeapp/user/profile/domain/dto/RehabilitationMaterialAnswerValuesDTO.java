package ru.virushack.strokeapp.user.profile.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RehabilitationMaterialAnswerValuesDTO implements Serializable {
    private static final long serialVersionUID = 5995390151514803855L;
    private String answer;
    @JsonProperty("answer_point")
    private int answerPoint;

}
