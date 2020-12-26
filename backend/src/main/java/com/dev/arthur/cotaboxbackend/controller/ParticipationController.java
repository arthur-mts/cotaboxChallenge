package com.dev.arthur.cotaboxbackend.controller;

import com.dev.arthur.cotaboxbackend.dto.ParticipationDTO;
import com.dev.arthur.cotaboxbackend.dto.ResponseDTO;
import com.dev.arthur.cotaboxbackend.entity.Participation;

import com.dev.arthur.cotaboxbackend.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class ParticipationController {

    private final ParticipationService participationService;

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @DeleteMapping
    public ResponseEntity<String> removeAll() {
        this.participationService.removeAll();
        return ResponseEntity.ok().body("Todas as participações foram removidas com sucesso!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> remove(@PathVariable("id") String id) {
        try {
            System.out.println(id);
            this.participationService.remove(id);
            return ResponseEntity.ok().body(ResponseDTO.builder().message("Removido com sucesso!").build());
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(
                        ResponseDTO.builder()
                            .message(e.getStatusText())
                        .build()
                    );
        }

    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Participation>> create(@Valid @RequestBody ParticipationDTO participationData) {

        var allParticipations = this.participationService.getAll();

        float percentageSum = 0;

        ResponseDTO<Participation> responseDTO;

        for(Participation participationItem : allParticipations)
            percentageSum += participationItem.getParticipation();

        if(percentageSum + participationData.getParticipation() > 100){
            responseDTO = ResponseDTO
                    .<Participation>builder()
                    .message("A soma das porcentagens excede "
                            + (percentageSum + participationData.getParticipation() - 100)
                            + "de 100 por cento.")
                    .build();
        }
        else {
            var participation= this.participationService.create(participationData);
            responseDTO = ResponseDTO.
                    <Participation>builder()
                    .message("Participação adicionada com sucesso!")
                    .body(participation)
                    .build();
        }

        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Participation>> list(){
        var participations = this.participationService.getAll();
        return ResponseEntity.ok().body(participations);
    }

}
