package com.dev.arthur.cotaboxbackend.service;

import com.dev.arthur.cotaboxbackend.dto.ParticipationDTO;
import com.dev.arthur.cotaboxbackend.entity.Participation;
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

    public void remove(String id) {
        if(! this.personRepo.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Não existe registro de participação com esse ID!");
        }

        this.personRepo.deleteById(id);
    }

    public Participation create(ParticipationDTO personData) {
        System.out.println(personData);
        var person = Participation
                .builder()
                .firstName(personData.getFirstName())
                .lastName(personData.getLastName())
                .participation(personData.getParticipation())
                .build();

        return this.personRepo.save(person);
    }
}
