package com.example.filrouge.Repository;

import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.Entretien;
import com.example.filrouge.Entities.OffreEmploi;
import com.example.filrouge.Enum.TypeOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOffreEmploiRepository extends JpaRepository<OffreEmploi,Long> {

    List<OffreEmploi> findByDeletedFalse();
    Optional<OffreEmploi> findByIdAndDeletedFalse(Long id);
    Optional<OffreEmploi> findByTypeOffreAndDeletedFalse(TypeOffre typeOffre);

    Optional<OffreEmploi> findByCodeOffreAndDeletedFalse(String codeEchontillon);

}
