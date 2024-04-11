package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Exception.NotFoundException;
import com.example.filrouge.Repository.ICandidatRepository;
import com.example.filrouge.Service.ICandidatService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.webresources.Cache;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidatServiceImpl implements ICandidatService {

    private final ModelMapper modelMapper;
    private final ICandidatRepository iCandidatRepository;


    @Override
    public CandidatDto add(CandidatDto candidatDto) {
        validation(candidatDto);
        Candidat candidatEntity = iCandidatRepository.save(modelMapper.map(candidatDto,Candidat.class));
        return modelMapper.map(candidatEntity, CandidatDto.class);
    }

    @Override
    public CandidatDto update(Long id, CandidatDto candidatDto) {
        validation(candidatDto);
        Candidat candidatExist = iCandidatRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("Candidat not found with this id :" + id));
        candidatExist.setNom(candidatDto.getNom());
        candidatExist.setPrenom(candidatDto.getPrenom());
        candidatExist.setVille(candidatDto.getVille());
        candidatExist.setAdresse(candidatDto.getAdresse());
        candidatExist.setTelephone(candidatDto.getTelephone());
        candidatExist.setSexe(candidatDto.getSexe());
        candidatExist.setSpecialite(candidatDto.getSpecialite());
        candidatExist.setNiveauEtude(candidatDto.getNiveauEtude());

        Candidat candidatUpdated = iCandidatRepository.save(candidatExist);
        return modelMapper.map(candidatUpdated, CandidatDto.class);
    }

    @Override
    public void delete(Long id) {

        Candidat candidat = iCandidatRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("candidat not found with this id : " + id));
        candidat.setDeleted(true);
        iCandidatRepository.save(candidat);
    }

    @Override
    public List<CandidatDto> getAll() {

        List<Candidat> candidats = iCandidatRepository.findAllByDeletedFalse();
        return candidats
                .stream()
                .map(candidat -> modelMapper.map(candidat, CandidatDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidatDto getById(Long id) {

        Candidat candidat = iCandidatRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("candidat not found with id : " + id));
        return modelMapper.map(candidat, CandidatDto.class);
    }

    @Override
    public CandidatDto getByName(String name) {

        Candidat candidat = iCandidatRepository.findByNomAndDeletedFalse(name).orElseThrow(() -> new NotFoundException("candidat not found with name :" + name));
        return modelMapper.map(candidat, CandidatDto.class);
    }

    @Override
    public void validation(CandidatDto candidatDto) {
        if (candidatDto == null) {
            throw new ValidationException("Les données du candidat sont nécessaires.");
        }

        if (StringUtils.isBlank(candidatDto.getPrenom())) {
            throw new ValidationException("Le prénom est requis.");
        }

        if (StringUtils.isBlank(candidatDto.getNom())) {
            throw new ValidationException("Le nom est requis.");
        }

        if (StringUtils.isBlank(candidatDto.getAdresse())) {
            throw new ValidationException("L'adresse est requise.");
        }

        if (StringUtils.isBlank(candidatDto.getVille())) {
            throw new ValidationException("La ville est requise.");
        }

        if (StringUtils.isBlank(candidatDto.getTelephone())) {
            throw new ValidationException("Le téléphone est requis.");
        }

        if (candidatDto.getSexe() == null) {
            throw new ValidationException("Le sexe est requis.");
        }

        if (candidatDto.getSpecialite() == null) {
            throw new ValidationException("La spécialité est requise.");
        }

        if (candidatDto.getNiveauEtude() == null) {
            throw new ValidationException("Le niveau d'étude est requis.");
        }


    }
}
