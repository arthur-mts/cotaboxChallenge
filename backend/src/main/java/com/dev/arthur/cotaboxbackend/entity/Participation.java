package com.dev.arthur.cotaboxbackend.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "participations")
@Data
@Builder
@Getter
public class Participation {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Float participationPercentage;
}



