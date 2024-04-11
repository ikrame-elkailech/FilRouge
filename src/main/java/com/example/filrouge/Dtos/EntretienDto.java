package com.example.filrouge.Dtos;

import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.OffreEmploi;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Enum.StatusResultat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntretienDto implements Serializable {

    Long id;

    @NotNull
    LocalDate date;

    @NotNull
    String description;


    @Enumerated(EnumType.STRING)
    private StatusResultat statusResultat;

    @JsonIgnore
    @Builder.Default
    Boolean deleted = false;


    @JsonIgnoreProperties(value = "recruteur")
    @NotNull
    User user;

    @JsonIgnoreProperties(value = "offreEmploi")
    @NotNull
    OffreEmploi offreEmploi;

    @JsonIgnoreProperties(value = "candidat")
    @NotNull
    Candidat candidat;
}
