package com.example.filrouge.Repository;

import com.example.filrouge.Entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICandidatRepository extends JpaRepository<Candidat,Long> {

    List<Candidat> findAllByDeletedFalse();
    Optional<Candidat> findByIdAndDeletedFalse(Long id);

    Optional<Candidat> findByNomAndDeletedFalse(String name);
}
