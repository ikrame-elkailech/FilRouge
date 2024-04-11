package com.example.filrouge.Entities;


import com.example.filrouge.Enum.Sexe;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String telephone;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;


}
