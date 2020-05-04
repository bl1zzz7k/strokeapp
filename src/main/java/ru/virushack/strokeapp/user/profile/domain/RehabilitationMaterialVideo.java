package ru.virushack.strokeapp.user.profile.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RehabilitationMaterialVideo {
    private List<String> categories;
    private String title;
    private String url;
}
