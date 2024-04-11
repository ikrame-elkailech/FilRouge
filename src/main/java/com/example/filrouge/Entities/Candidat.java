package com.example.filrouge.Entities;

import com.example.filrouge.Enum.NiveauEtude;
import com.example.filrouge.Enum.RoleUser;
import com.example.filrouge.Enum.Specialite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "candidat")
public class Candidat extends Personne{

    @Enumerated(EnumType.STRING)
    private NiveauEtude niveauEtude;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    @JsonIgnore
    private Boolean deleted;

    //cv et lettre motivation

    @ToString.Exclude
    @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER)
    private List<Entretien> entretiens = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    private List<OffreEmploi> offreEmplois= new ArrayList<>();

}
