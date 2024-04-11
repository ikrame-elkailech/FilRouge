package com.example.filrouge.Service;

import com.example.filrouge.Dtos.EntretienDto;
import com.example.filrouge.Service.Impl.EntretienServiceImpl;

import java.util.List;

public interface IEntretienService {

    EntretienDto add(EntretienDto entretienDto);
    EntretienDto update(Long id, EntretienDto entretienDto);
    void delete(Long id);
    List<EntretienDto> getAll();
    EntretienDto getById(Long id);
    EntretienDto getByName(String name);
    void validation(EntretienDto entretienDto);

}
