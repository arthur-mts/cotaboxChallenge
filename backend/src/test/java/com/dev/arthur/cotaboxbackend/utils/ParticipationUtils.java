package com.dev.arthur.cotaboxbackend.utils;

import com.dev.arthur.cotaboxbackend.dto.ParticipationDTO;
import com.dev.arthur.cotaboxbackend.entity.Participation;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipationUtils {

    private static final Faker faker = Faker.instance();

    public static ParticipationDTO createFakeParticipationDTO() {

        return ParticipationDTO.builder().
                firstName(faker.name().firstName()).
                lastName(faker.name().lastName())
                .build();
    }

    public static Float createRandomParticipationPercentage() {
        return (float) faker.number().numberBetween(1, 100);
    }

    public static Participation createFakeParticipation() {
        return Participation.builder()
                .id(faker.idNumber().valid())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }

    public static String createFakeID() {


        return faker.idNumber().valid();
    }


    public static List<Participation> createParticipationsListWithCompletePercentage() {
        var part1 = createFakeParticipation();
        part1.setParticipationPercentage(50F);
        var part2 = createFakeParticipation();
        part2.setParticipationPercentage(49F);
        return Arrays.asList(part1, part2);
    }

}
