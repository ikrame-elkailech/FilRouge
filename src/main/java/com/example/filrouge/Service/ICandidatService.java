package com.example.filrouge.Service;

import com.example.filrouge.Dtos.CandidatDto;

import java.util.List;

public interface ICandidatService {

    CandidatDto add(CandidatDto candidatDto);
    CandidatDto update(Long id, CandidatDto candidatDto);
    void delete(Long id);
    List<CandidatDto> getAll();
    CandidatDto getById(Long id);
    CandidatDto getByName(String name);
    void validation(CandidatDto candidatDto);


}
