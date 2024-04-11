package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Dtos.EntretienDto;
import com.example.filrouge.Dtos.OffreEmploiDto;
import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.Entretien;
import com.example.filrouge.Entities.OffreEmploi;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Exception.NotFoundException;
import com.example.filrouge.Repository.*;
import com.example.filrouge.Service.IEntretienService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntretienServiceImpl implements IEntretienService {

    private final ModelMapper modelMapper;
    private final IEntrepriseRepository iEntrepriseRepository;
    private final IOffreEmploiRepository iOffreEmploiRepository;
    private final ICandidatRepository iCandidatRepository;
    private final IEntretienRepository iEntretienRepository;
    private final IUserRepository iUserRepository;




    @Override
    public EntretienDto add(EntretienDto entretienDto) {

        validation(entretienDto);
        System.out.println(entretienDto);

        OffreEmploi offreEmploiExist = iOffreEmploiRepository.findByIdAndDeletedFalse(entretienDto.getOffreEmploi().getId()).orElseThrow(() -> new NotFoundException("Offre emploi not found with this id : " + entretienDto.getOffreEmploi().getId()));
        User userExist = iUserRepository.findByIdAndDeletedFalse(entretienDto.getUser().getId()).orElseThrow(() -> new NotFoundException("User not found with this id : " + entretienDto.getUser().getId()));
        Candidat candidatExist = iCandidatRepository.findByIdAndDeletedFalse(entretienDto.getCandidat().getId()).orElseThrow(() -> new NotFoundException("Candidat not found with this id : " + entretienDto.getCandidat().getId()));

        entretienDto.setOffreEmploi(modelMapper.map(offreEmploiExist, OffreEmploi.class));
        entretienDto.setUser(modelMapper.map(userExist, User.class));
        entretienDto.setCandidat(modelMapper.map(candidatExist, Candidat.class));

        Entretien entretien = iEntretienRepository.save(modelMapper.map(entretienDto, Entretien.class));
        System.out.println(entretien);
        return modelMapper.map(entretien, EntretienDto.class);
    }

    @Override
    public EntretienDto update(Long id, EntretienDto entretienDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<EntretienDto> getAll() {
        return null;
    }

    @Override
    public EntretienDto getById(Long id) {
        return null;
    }

    @Override
    public EntretienDto getByName(String name) {
        return null;
    }

    @Override
    public void validation(EntretienDto entretienDto) {

    }
}
