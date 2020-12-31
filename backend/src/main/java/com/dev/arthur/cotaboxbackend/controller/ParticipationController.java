package com.dev.arthur.cotaboxbackend.controller;

import com.dev.arthur.cotaboxbackend.dto.ParticipationDTO;
import com.dev.arthur.cotaboxbackend.dto.ResponseDTO;
import com.dev.arthur.cotaboxbackend.entity.Participation;

import com.dev.arthur.cotaboxbackend.exception.ParticipationNotFound;
import com.dev.arthur.cotaboxbackend.exception.ParticipationOutOfBound;
import com.dev.arthur.cotaboxbackend.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/persons")
public class ParticipationController {

    private final ParticipationService participationService;

    private Optional<String> getFirstValidationErrorMessage(BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return Optional.of(bindingResult.getAllErrors().get(0).getDefaultMessage());


        return Optional.empty();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception ex) {

        if(ex instanceof HttpStatusCodeException)
            return
                ResponseEntity
                    .status(((HttpStatusCodeException) ex).getStatusCode())
                    .body(
                        ResponseDTO
                            .builder()
                            .message(
                                ((HttpStatusCodeException) ex).getStatusText()
                            )
                            .build()
                    );


        System.out.println(ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    ResponseDTO.builder()
                    .message("Erro interno do servidor.")
                    .build()
                );
    }

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> removeAll() {
        this.participationService.removeAll();
        return ResponseEntity.ok().body(ResponseDTO.builder().message("Participações removidas com sucesso!").build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<Object>> remove(@PathVariable("id") String id) throws ParticipationNotFound {
            this.participationService.remove(id);
            return ResponseEntity.ok().body(ResponseDTO.builder().message("Participação removida com sucesso!").build());
    }

    @PostMapping
    public ResponseEntity<Object>
        create(@Valid @RequestBody ParticipationDTO participationData,
               BindingResult bindingResult) throws ParticipationOutOfBound {

        var validationErrorMessage= this.getFirstValidationErrorMessage(bindingResult);

        if(validationErrorMessage.isPresent())
            return ResponseEntity
                    .badRequest()
                    .body(
                        ResponseDTO.builder()
                            .message(validationErrorMessage.get())
                            .build()
                    );

        var participation= this.participationService.create(participationData);

        return ResponseEntity.ok()
                .body(participation);
    }

    @GetMapping
    public ResponseEntity<List<Participation>> list(){
        var participations = this.participationService.getAll();
        return ResponseEntity.ok().body(participations);
    }

}
