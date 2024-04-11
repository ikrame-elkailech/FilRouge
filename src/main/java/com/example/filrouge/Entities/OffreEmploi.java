package com.example.filrouge.Entities;


import com.example.filrouge.Enum.StatusOffre;
import com.example.filrouge.Enum.TypeOffre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OffreEmploi")
public class OffreEmploi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeOffre;
    private String titre;
    private String description;
    private String poste;

    @Enumerated(EnumType.STRING)
    private StatusOffre statusOffre;

    @Enumerated(EnumType.STRING)
    private TypeOffre typeOffre;

    @JsonIgnore
    private Boolean deleted;

    @ManyToOne
    private User user;

    @ManyToOne
    private Entreprise entreprise;

    @ToString.Exclude
    @OneToMany(mappedBy = "offreEmploi", fetch = FetchType.EAGER)
    private List<Entretien> entretiens= new ArrayList<>();


    @ToString.Exclude
    @ManyToMany(mappedBy = "offreEmplois", fetch = FetchType.EAGER)
    private List<Candidat> candidats= new ArrayList<>();



}
