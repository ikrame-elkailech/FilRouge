package com.example.filrouge.Dtos;

import com.example.filrouge.Enum.RoleUser;
import com.example.filrouge.Enum.Sexe;
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
public class UserDto implements Serializable {

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
    @Email
    String email;

    @NotNull
    String password;

    @NotNull
    RoleUser userRole;

    @JsonIgnore
    @Builder.Default
    Boolean deleted = false;

    @JsonIgnoreProperties(value = "recruteur")
    List<EntretienDto> entretienDtos;

    @JsonIgnoreProperties(value = "recruteur")
    List<OffreEmploiDto> offreEmploiDtos;
}
