package com.example.filrouge.Dtos;

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
public class EntrepriseDto implements Serializable {


    Long id;

    @NotNull
    String name;

    @NotNull
    String telephone;

    @NotNull
    @Email
    String email;

    @JsonIgnore
    @Builder.Default
    Boolean deleted = false;

    @JsonIgnoreProperties(value = "entreprise")
    List<OffreEmploiDto> offreEmploiDtos;

}
