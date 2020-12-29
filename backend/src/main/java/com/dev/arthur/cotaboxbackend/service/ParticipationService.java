package com.dev.arthur.cotaboxbackend.service;

import com.dev.arthur.cotaboxbackend.dto.ParticipationDTO;
import com.dev.arthur.cotaboxbackend.entity.Participation;
import com.dev.arthur.cotaboxbackend.exception.ParticipationNotFound;
import com.dev.arthur.cotaboxbackend.exception.ParticipationOutOfBound;
import com.dev.arthur.cotaboxbackend.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public List<Participation> getAll() {
        return this.participationRepository.findAll();
    }

    public void removeAll() {
        this.participationRepository.deleteAll();
    }

    public void remove(String id) throws ParticipationNotFound {
        if(! this.participationRepository.existsById(id)) {
            throw new ParticipationNotFound();
        }

        this.participationRepository.deleteById(id);
    }

    public Participation create(ParticipationDTO participationData) throws ParticipationOutOfBound {

        var allParticipations = this.getAll();

        float percentageSum = 0;

        for(Participation participationItem : allParticipations)
            percentageSum += participationItem.getParticipationPercentage();



        float excess = percentageSum + participationData.getParticipationPercentage() - 100;

        if(excess > 0)
            throw new ParticipationOutOfBound(excess);

        var participation = Participation
                .builder()
                .firstName(participationData.getFirstName())
                .lastName(participationData.getLastName())
                .participationPercentage(participationData.getParticipationPercentage())
                .build();

        return this.participationRepository.save(participation);
    }
}
