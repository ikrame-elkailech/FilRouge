package com.example.filrouge.Repository;


import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEntretienRepository extends JpaRepository<Entretien,Long> {

    List<Entretien> findByDeletedFalse();
    Optional<Entretien> findByIdAndDeletedFalse(Long id);
}
