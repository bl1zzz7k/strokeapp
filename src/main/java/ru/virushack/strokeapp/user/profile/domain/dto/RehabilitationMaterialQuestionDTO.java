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
public class RehabilitationMaterialQuestionDTO implements Serializable {
    private static final long serialVersionUID = 6937943645896473568L;

    @JsonProperty("question_name")
    private String questionName;
    @JsonProperty("answer_type")
    private RehabilitationMaterialAnswerTypeDTO answerType;
    @JsonProperty("answer_values")
    private List<RehabilitationMaterialAnswerValuesDTO> answerValueList;

}
