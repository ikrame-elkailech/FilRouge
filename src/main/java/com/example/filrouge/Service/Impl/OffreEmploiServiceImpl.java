package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.EntrepriseDto;
import com.example.filrouge.Dtos.OffreEmploiDto;
import com.example.filrouge.Entities.Entreprise;
import com.example.filrouge.Entities.OffreEmploi;
import com.example.filrouge.Enum.TypeOffre;
import com.example.filrouge.Exception.NotFoundException;
import com.example.filrouge.Repository.IEntrepriseRepository;
import com.example.filrouge.Repository.IOffreEmploiRepository;
import com.example.filrouge.Service.IOffreEmploiService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OffreEmploiServiceImpl implements IOffreEmploiService {
    private final ModelMapper modelMapper;
    private final IEntrepriseRepository iEntrepriseRepository;
    private final IOffreEmploiRepository iOffreEmploiRepository;

    @Override
    public OffreEmploiDto add(OffreEmploiDto offreEmploiDto) {

        validation(offreEmploiDto);
        Entreprise entrepriseExist = iEntrepriseRepository.findByIdAndDeletedFalse(offreEmploiDto.getEntrepriseDto().getId()).orElseThrow(() -> new NotFoundException("Offre emploi not found with this id : " + offreEmploiDto.getEntrepriseDto().getId()));

        offreEmploiDto.setCodeOffre("OFFRE-" + UUID.randomUUID()+ "-" + entrepriseExist.getName().toUpperCase());
        offreEmploiDto.setEntrepriseDto(modelMapper.map(entrepriseExist, EntrepriseDto.class));

        OffreEmploi offreEmploi = iOffreEmploiRepository.save(modelMapper.map(offreEmploiDto, OffreEmploi.class));
        return modelMapper.map(offreEmploi, OffreEmploiDto.class);
    }

    @Override
    public OffreEmploiDto update(Long id, OffreEmploiDto offreEmploiDto) {

        validation(offreEmploiDto);
        OffreEmploi offreEmploiExist = iOffreEmploiRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("Offre Emploi not found with this id : " + id));
        Entreprise entrepriseExist = iEntrepriseRepository.findByIdAndDeletedFalse(offreEmploiDto.getEntrepriseDto().getId()).orElseThrow(() -> new NotFoundException("Entreprise not found with this id : " + id));

        offreEmploiExist.setEntreprise(entrepriseExist);
        offreEmploiExist.setTitre(offreEmploiDto.getTitre());
        offreEmploiExist.setDescription(offreEmploiDto.getDescription());
        offreEmploiExist.setPoste(offreEmploiDto.getPoste());
        offreEmploiExist.setStatusOffre(offreEmploiDto.getStatusOffre());
        offreEmploiExist.setTypeOffre(offreEmploiDto.getTypeOffre());

        OffreEmploi offreEmploiUpdated = iOffreEmploiRepository.save(offreEmploiExist);
        return modelMapper.map(offreEmploiUpdated, OffreEmploiDto.class);

    }

    @Override
    public void delete(Long id) {

        OffreEmploi offreEmploi = iOffreEmploiRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("Offre emploi not found with this id : " + id));
        offreEmploi.setDeleted(true);
        iOffreEmploiRepository.save(offreEmploi);
    }

    @Override
    public List<OffreEmploiDto> getAll() {

        List<OffreEmploi> offreEmplois = iOffreEmploiRepository.findByDeletedFalse();
        return offreEmplois
                .stream()
                .map(offreEmploi -> modelMapper.map(offreEmploi, OffreEmploiDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OffreEmploiDto getById(Long id) {

        OffreEmploi offreEmploi = iOffreEmploiRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("Offre emploi not found with this id : " + id));
        return modelMapper.map(offreEmploi, OffreEmploiDto.class);
    }

    @Override
    public OffreEmploiDto getByTypeOffre(TypeOffre typeOffre) {

        OffreEmploi offreEmploi = iOffreEmploiRepository.findByTypeOffreAndDeletedFalse(typeOffre).orElseThrow(() -> new NotFoundException("Offre emploi not found with this code :" + typeOffre));
        return modelMapper.map(offreEmploi, OffreEmploiDto.class);
    }


    @Override
    public OffreEmploiDto getByCodeOffre(String codeOffre) {
        OffreEmploi offreEmploi = iOffreEmploiRepository.findByCodeOffreAndDeletedFalse(codeOffre).orElseThrow(() -> new NotFoundException("Offre emploi not found with this code :" + codeOffre));
        return modelMapper.map(offreEmploi, OffreEmploiDto.class);

    }



    @Override
    public void validation(OffreEmploiDto offreEmploiDto) {

        if (offreEmploiDto == null) {
            throw new ValidationException("Les données de l'offre d'emploi sont nécessaires.");
        }

        if (StringUtils.isBlank(offreEmploiDto.getTitre())) {
            throw new ValidationException("Le titre est requis.");
        }

        if (StringUtils.isBlank(offreEmploiDto.getPoste())) {
            throw new ValidationException("Le poste est requis.");
        }

        if (StringUtils.isBlank(offreEmploiDto.getDescription())) {
            throw new ValidationException("La description est requise.");
        }

        if (offreEmploiDto.getTypeOffre() == null) {
            throw new ValidationException("Le type de l'offre est requis.");
        }

        if (offreEmploiDto.getStatusOffre() == null) {
            throw new ValidationException("Le status de l'offre est requis.");
        }    }
}
