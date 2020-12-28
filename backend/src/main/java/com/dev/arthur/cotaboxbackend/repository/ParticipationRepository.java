package com.dev.arthur.cotaboxbackend.repository;

import com.dev.arthur.cotaboxbackend.entity.Participation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParticipationRepository extends MongoRepository<Participation, String> {

}
