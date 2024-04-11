package com.example.filrouge.Entities;


import com.example.filrouge.Enum.StatusResultat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Entretien")
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "date")
    private LocalDate date;


    @Column(name = "description")
    private String description;


    @JsonIgnore
    private Boolean deleted;


    @Enumerated(EnumType.STRING)
    private StatusResultat statusResultat;

    @ManyToOne
    private Candidat candidat;

    @ManyToOne
    private User user;

    @ManyToOne
    private OffreEmploi offreEmploi;



}
