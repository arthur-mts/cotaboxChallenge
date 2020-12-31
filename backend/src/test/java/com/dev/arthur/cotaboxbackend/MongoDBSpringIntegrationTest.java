package com.dev.arthur.cotaboxbackend;

import com.dev.arthur.cotaboxbackend.entity.Participation;
import com.dev.arthur.cotaboxbackend.repository.ParticipationRepository;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class MongoDBSpringIntegrationTest {

    @Autowired
    private ParticipationRepository participationRepository;

    @BeforeEach
    public void cleanup(){
        this.participationRepository.deleteAll();
    }


    @Test
    public void databaseShouldBeEmpty() {
        var participations = participationRepository.findAll();
        assertThat(participations.size()).isZero();
    }

    @Test
    public void shouldCreateParticipationWithSucces(){
        var part = Participation.builder().firstName("Name").lastName("Last name").participationPercentage(50.0F).build();
        participationRepository.save(part);
        var participations = participationRepository.findAll();
        assertThat(participations.size()).isEqualTo(1);
    }

    @Test
    public void shouldRemoveParticipationWithSucces(){
        var part = Participation.builder().firstName("Name").lastName("Last name").participationPercentage(50.0F).build();
        participationRepository.save(part);
        participationRepository.delete(part);

        var participations = participationRepository.findAll();

        assertThat(participations.size()).isZero();
    }

}
