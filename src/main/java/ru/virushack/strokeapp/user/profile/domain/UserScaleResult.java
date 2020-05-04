package ru.virushack.strokeapp.user.profile.domain;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class UserScaleResult {
    @Id
    private ObjectId id;
    @Indexed
    private ObjectId userId;
    @Indexed
    private String scaleType;
    private int value;
}
