package com.example.filrouge.Dtos;

import com.example.filrouge.Enum.StatusOffre;
import com.example.filrouge.Enum.TypeOffre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OffreEmploiDto implements Serializable {


    Long id;

    String codeOffre;
    @NotNull
    String titre;

    @NotNull
    String description;

    @NotNull
    String poste;

    @NotNull
    StatusOffre statusOffre;

    @NotNull
    TypeOffre typeOffre;

    @JsonIgnore
    @Builder.Default
    Boolean deleted = false;

    @JsonIgnore
    List<CandidatDto> candidatDtos;

    @JsonIgnoreProperties(value = "offreEmploi")
    List<EntretienDto> entretienDtos;


    @JsonIgnoreProperties(value = "recruteur")
    @NotNull
    UserDto userDto;

    @JsonIgnoreProperties(value = "entreprise")
    @NotNull
    EntrepriseDto entrepriseDto;


}
