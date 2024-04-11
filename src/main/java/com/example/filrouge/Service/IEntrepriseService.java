package com.example.filrouge.Service;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Dtos.EntrepriseDto;

import java.util.List;

public interface IEntrepriseService {

    EntrepriseDto add(EntrepriseDto entrepriseDto);
    EntrepriseDto update(Long id, EntrepriseDto entrepriseDto);
    void delete(Long id);
    List<EntrepriseDto> getAll();
    EntrepriseDto getById(Long id);
    EntrepriseDto getByName(String name);
    void validation(EntrepriseDto entrepriseDto);
}
