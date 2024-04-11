package com.example.filrouge.Repository;

import com.example.filrouge.Entities.RapportStatistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRapportStatistiqueRepository extends JpaRepository<RapportStatistique,Long> {

    List<RapportStatistique> findByDeletedFalse();
    Optional<RapportStatistique> findByIdAndDeletedFalse(Long id);

}
