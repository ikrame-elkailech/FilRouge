package com.example.filrouge.Dtos;

import com.example.filrouge.Entities.Entretien;
import com.example.filrouge.Enum.NiveauEtude;
import com.example.filrouge.Enum.Sexe;
import com.example.filrouge.Enum.Specialite;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatDto implements Serializable {

    Long id;

    @NotNull
    String nom;

    @NotNull
    String prenom;

    @NotNull
    String adresse;

    @NotNull
    String telephone;

    @NotNull
    String ville;

    @NotNull
    Sexe sexe;

    @NotNull
    NiveauEtude niveauEtude;

    @NotNull
    Specialite specialite;

    @JsonIgnore
    @Builder.Default
    Boolean deleted = false;

    @JsonIgnoreProperties(value = "candidat")
    List<EntretienDto> entretienDtos;

    @JsonIgnore
    List<OffreEmploiDto> offreEmploiDtos;

}
