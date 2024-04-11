package com.example.filrouge.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LongSummaryStatistics;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String telephone;
    private String email;

    @JsonIgnore
    private Boolean deleted;

    @OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
    private List<OffreEmploi> offreEmplois = new ArrayList<>();

}
