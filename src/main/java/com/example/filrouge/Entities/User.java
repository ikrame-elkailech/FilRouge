package com.example.filrouge.Entities;


import com.example.filrouge.Enum.RoleUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User extends Personne{


    @Column(unique = true)
    private String email;
    private String password;


    @JsonIgnore
    private Boolean deleted;


    @Enumerated(EnumType.STRING)
    private RoleUser userRole;


    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<OffreEmploi> offreEmplois = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Entretien> entretiens= new ArrayList<>();




}
