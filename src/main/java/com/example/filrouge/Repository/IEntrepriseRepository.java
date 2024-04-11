package com.example.filrouge.Repository;


import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEntrepriseRepository extends JpaRepository<Entreprise,Long> {

    List<Entreprise> findAllByDeletedFalse();
    Optional<Entreprise> findByNameAndDeletedFalse(String name);
    Optional<Entreprise> findByIdAndDeletedFalse(Long id);
}
