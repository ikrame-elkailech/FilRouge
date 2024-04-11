package com.example.filrouge.Service;

import com.example.filrouge.Dtos.OffreEmploiDto;
import com.example.filrouge.Enum.TypeOffre;

import java.time.LocalDate;
import java.util.List;

public interface IOffreEmploiService {


    OffreEmploiDto add(OffreEmploiDto offreEmploiDto);
    OffreEmploiDto update(Long id, OffreEmploiDto offreEmploiDto);
    void delete(Long id);
    List<OffreEmploiDto> getAll();
    OffreEmploiDto getById(Long id);
    OffreEmploiDto getByTypeOffre(TypeOffre typeOffre);

    OffreEmploiDto getByCodeOffre(String codeOffre);

    void validation(OffreEmploiDto offreEmploiDto);

}
