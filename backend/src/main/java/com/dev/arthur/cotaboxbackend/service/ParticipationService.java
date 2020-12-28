package com.dev.arthur.cotaboxbackend.service;

import com.dev.arthur.cotaboxbackend.dto.ParticipationDTO;
import com.dev.arthur.cotaboxbackend.dto.ResponseDTO;
import com.dev.arthur.cotaboxbackend.entity.Participation;
import com.dev.arthur.cotaboxbackend.exception.ParticipationNotFound;
import com.dev.arthur.cotaboxbackend.exception.ParticipationOutOfBound;
import com.dev.arthur.cotaboxbackend.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ParticipationService {

    private ParticipationRepository personRepo;

    @Autowired
    public ParticipationService(ParticipationRepository personRepo) {
        this.personRepo = personRepo;
    }

    public List<Participation> getAll() {
        return this.personRepo.findAll();
    }

    public void removeAll() {
        this.personRepo.deleteAll();
    }

    public void remove(String id) throws ParticipationNotFound {
        if(! this.personRepo.existsById(id)) {
            throw new ParticipationNotFound();
        }

        this.personRepo.deleteById(id);
    }

    public Participation create(ParticipationDTO participationData) throws ParticipationOutOfBound {

        var allParticipations = this.getAll();

        float percentageSum = 0;

        for(Participation participationItem : allParticipations)
            percentageSum += participationItem.getParticipation();



        float excess = percentageSum + participationData.getParticipation() - 100;

        if(excess > 0)
            throw new ParticipationOutOfBound(excess);

        var participation = Participation
                .builder()
                .firstName(participationData.getFirstName())
                .lastName(participationData.getLastName())
                .participation(participationData.getParticipation())
                .build();

        return this.personRepo.save(participation);
    }
}
