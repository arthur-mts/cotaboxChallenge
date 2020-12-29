package com.dev.arthur.cotaboxbackend.services;

import com.dev.arthur.cotaboxbackend.entity.Participation;
import com.dev.arthur.cotaboxbackend.exception.ParticipationOutOfBound;
import com.dev.arthur.cotaboxbackend.repository.ParticipationRepository;
import com.dev.arthur.cotaboxbackend.service.ParticipationService;
import com.dev.arthur.cotaboxbackend.utils.ParticipationUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
//import static org.assertj.core.api.Assertions.*;


import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class ParticipationServiceTest {

    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ParticipationService participationService;

    @AfterEach
    void resetMocks() {
        Mockito.reset(participationRepository);
    }

    @BeforeEach
    void configureMocks(){
        Mockito.when(participationRepository.save(notNull())).
            thenAnswer(((InvocationOnMock invocationMock) -> {
                Participation data = invocationMock.getArgument(0);
                data.setId(ParticipationUtils.createFakeID());
                return data;
            }));
    }



    @Test
    void shouldCreateParticipationWithSucces() {

        //MOCKANDO CONEXÕES COM O BANCO DE DADOS
        Mockito.when(participationRepository.findAll()).thenReturn(Collections.emptyList());

        var fakerParticipation = ParticipationUtils.createFakeParticipationDTO();

        fakerParticipation.setParticipationPercentage(ParticipationUtils.createRandomParticipationPercentage());

        var participation = participationService.create(fakerParticipation);

        assertEquals(participation.getFirstName(),fakerParticipation.getFirstName());

        assertEquals(participation.getLastName(), fakerParticipation.getLastName());


    }

    @Test()
    void shouldFailToCreateParticipationBecausePercentageOutOfBound(){
        Mockito.when(participationRepository.findAll()).thenReturn(ParticipationUtils.createParticipationsListWithCompletePercentage());

        var participationData = ParticipationUtils.createFakeParticipationDTO();

        participationData.setParticipationPercentage(ParticipationUtils.createRandomParticipationPercentage());

        assertThrows(ParticipationOutOfBound.class, ()-> participationService.create(participationData));
    }

    @Test()
    void shouldFailToCreateParticipationBecauseInvalidPercentage() {
        Mockito.when(participationRepository.findAll()).thenReturn(Collections.emptyList());

        var participationData = ParticipationUtils.createFakeParticipationDTO();

        participationData.setParticipationPercentage(101F);

        assertThrows(ParticipationOutOfBound.class, ()-> participationService.create(participationData));

    }
}
