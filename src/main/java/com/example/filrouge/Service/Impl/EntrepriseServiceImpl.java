package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Dtos.EntrepriseDto;
import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.Entreprise;
import com.example.filrouge.Exception.NotFoundException;
import com.example.filrouge.Repository.ICandidatRepository;
import com.example.filrouge.Repository.IEntrepriseRepository;
import com.example.filrouge.Service.IEntrepriseService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements IEntrepriseService {

    private final ModelMapper modelMapper;
    private final IEntrepriseRepository iEntrepriseRepository;

    @Override
    public EntrepriseDto add(EntrepriseDto entrepriseDto) {
        validation(entrepriseDto);
        Entreprise entrepriseEntity = iEntrepriseRepository.save(modelMapper.map(entrepriseDto, Entreprise.class));
        return modelMapper.map(entrepriseEntity, EntrepriseDto.class);
    }

    @Override
    public EntrepriseDto update(Long id, EntrepriseDto entrepriseDto) {
        validation(entrepriseDto);
        Entreprise entrepriseExist = iEntrepriseRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("Entreprise not found with this id :" + id));
        entrepriseExist.setName(entrepriseDto.getName());
        entrepriseExist.setTelephone(entrepriseDto.getTelephone());
        entrepriseExist.setEmail(entrepriseDto.getEmail());


        Entreprise entrepriseUpdated = iEntrepriseRepository.save(entrepriseExist);
        return modelMapper.map(entrepriseUpdated, EntrepriseDto.class);
    }

    @Override
    public void delete(Long id) {

        Entreprise entreprise = iEntrepriseRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("Entreprise not found with this id : " + id));
        entreprise.setDeleted(true);
        iEntrepriseRepository.save(entreprise);
    }

    @Override
    public List<EntrepriseDto> getAll() {
        List<Entreprise> entreprises = iEntrepriseRepository.findAllByDeletedFalse();
        return entreprises
                .stream()
                .map(entreprise -> modelMapper.map(entreprise, EntrepriseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EntrepriseDto getById(Long id) {
        Entreprise entreprise = iEntrepriseRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("Entreprise not found with id : " + id));
        return modelMapper.map(entreprise, EntrepriseDto.class);
    }

    @Override
    public EntrepriseDto getByName(String name) {
        Entreprise entreprise = iEntrepriseRepository.findByNameAndDeletedFalse(name).orElseThrow(() -> new NotFoundException("Entreprise not found with name :" + name));
        return modelMapper.map(entreprise, EntrepriseDto.class);
    }

    @Override
    public void validation(EntrepriseDto entrepriseDto) {
        if (entrepriseDto == null) {
            throw new ValidationException("Les données de l'entreprise sont nécessaires.");
        }

        if (StringUtils.isBlank(entrepriseDto.getName())) {
            throw new ValidationException("Le nom est requis.");
        }

        if (StringUtils.isBlank(entrepriseDto.getEmail())) {
            throw new ValidationException("L'email est requis.");
        }

        if (StringUtils.isBlank(entrepriseDto.getTelephone())) {
            throw new ValidationException("Le télephone est requis.");
        }
    }
}
