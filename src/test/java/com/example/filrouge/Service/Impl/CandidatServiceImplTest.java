package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Enum.NiveauEtude;
import com.example.filrouge.Enum.RoleUser;
import com.example.filrouge.Enum.Sexe;
import com.example.filrouge.Enum.Specialite;
import com.example.filrouge.Repository.ICandidatRepository;
import com.example.filrouge.Repository.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidatServiceImplTest {



    @Mock
    private ICandidatRepository iCandidatRepository;


    @InjectMocks
    private CandidatServiceImpl candidatService;

    @BeforeEach
    @DisplayName("Instantiation object iUserRepository par .mock & modelMapper & UserService")
    public void setup()
    {
        iCandidatRepository = Mockito.mock(ICandidatRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        candidatService = new CandidatServiceImpl(modelMapper, iCandidatRepository);
    }

    @Test
    @DisplayName("candidatService_saveCandidat_ReturnOneCandidat")
    public void candidatService_saveCandidat_ReturnOneCandidat()
    {
        Candidat candidat = new Candidat();
        candidat.setId(1L);
        candidat.setNom("test1");
        candidat.setPrenom("test1");
        candidat.setTelephone("0631792201");
        candidat.setSexe(Sexe.homme);
        candidat.setAdresse("address 1");
        candidat.setVille("casablanca");
        candidat.setDeleted(false);
        candidat.setSpecialite(Specialite.MARKETING);
        candidat.setNiveauEtude(NiveauEtude.BAC_2);

        CandidatDto candidatDto = new CandidatDto();
        candidatDto.setNom("test1");
        candidatDto.setPrenom("test1");
        candidatDto.setTelephone("0631792201");
        candidatDto.setSexe(Sexe.homme);
        candidatDto.setAdresse("address 1");
        candidatDto.setVille("casablanca");
        candidatDto.setDeleted(false);
        candidatDto.setSpecialite(Specialite.MARKETING);
        candidatDto.setNiveauEtude(NiveauEtude.BAC_2);


        when(iCandidatRepository.save(Mockito.any(Candidat.class))).thenReturn(candidat);

        CandidatDto candidatSaved = candidatService.add(candidatDto);
        System.out.println(candidatSaved);
        System.out.println(candidat.getNom());
        Assertions.assertThat(candidatSaved.getId()).isEqualTo(candidat.getId());
        Assertions.assertThat(candidatSaved).isNotNull();
    }

    @Test
    @DisplayName("candidatService_getCandidatByIdCandidat_ReturnOneCandidat")
    public void candidatService_getCandidatByIdCandidat_ReturnOneCandidat()
    {
        Candidat candidat = new Candidat();
        candidat.setId(1L);
        candidat.setNom("test1");
        candidat.setPrenom("test1");
        candidat.setTelephone("0631792201");
        candidat.setSexe(Sexe.homme);
        candidat.setAdresse("address 1");
        candidat.setVille("casablanca");
        candidat.setDeleted(false);
        candidat.setSpecialite(Specialite.MARKETING);
        candidat.setNiveauEtude(NiveauEtude.BAC_2);

        CandidatDto candidatDto = new CandidatDto();
        candidatDto.setNom("test1");
        candidatDto.setPrenom("test1");
        candidatDto.setTelephone("0631792201");
        candidatDto.setSexe(Sexe.homme);
        candidatDto.setAdresse("address 1");
        candidatDto.setVille("casablanca");
        candidatDto.setDeleted(false);
        candidatDto.setSpecialite(Specialite.MARKETING);
        candidatDto.setNiveauEtude(NiveauEtude.BAC_2);


        when(iCandidatRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(candidat));

        CandidatDto candidatDtoSaved = candidatService.getById(1L);
        Assertions.assertThat(candidatDtoSaved).isNotNull();
        Assertions.assertThat(candidatDtoSaved.getId()).isEqualTo(candidat.getId());
    }

    @Test
    @DisplayName("candidatRepository_updateCandidat_ReturnOneCandidatUpdated")
    public void candidatRepository_updateCandidat_ReturnOneCandidatUpdated()
    {
        Candidat candidat = new Candidat();
        candidat.setId(1L);
        candidat.setNom("test1");
        candidat.setPrenom("test1");
        candidat.setTelephone("0631792201");
        candidat.setSexe(Sexe.homme);
        candidat.setAdresse("address 1");
        candidat.setVille("casablanca");
        candidat.setDeleted(false);
        candidat.setSpecialite(Specialite.MARKETING);
        candidat.setNiveauEtude(NiveauEtude.BAC_2);

        CandidatDto candidatDto = new CandidatDto();
        candidatDto.setNom("test1");
        candidatDto.setPrenom("test1");
        candidatDto.setTelephone("0631792201");
        candidatDto.setSexe(Sexe.homme);
        candidatDto.setAdresse("address 1");
        candidatDto.setVille("casablanca");
        candidatDto.setDeleted(false);
        candidatDto.setSpecialite(Specialite.MARKETING);
        candidatDto.setNiveauEtude(NiveauEtude.BAC_2);

        when(iCandidatRepository.save(Mockito.any(Candidat.class))).thenReturn(candidat);
        when(iCandidatRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(candidat));


        CandidatDto candidatDtoUpdated = candidatService.update(1L, candidatDto);
        System.out.println(candidatDtoUpdated);
        System.out.println(candidat.getNom() + " " + candidat.getAdresse() + " " +  candidat.getPrenom());
        Assertions.assertThat(candidatDtoUpdated).isNotNull();
        Assertions.assertThat(candidatDtoUpdated.getAdresse()).isEqualTo(candidat.getAdresse());
    }

    @Test
    @DisplayName("candidatService_deleteByIdCandidat_Return0Candidat")
    public void candidatService_deleteByIdCandidat_Return0Candidat()
    {
        Candidat candidat = new Candidat();
        candidat.setId(1L);
        candidat.setNom("test1");
        candidat.setPrenom("test1");
        candidat.setTelephone("0631792201");
        candidat.setSexe(Sexe.homme);
        candidat.setAdresse("address 1");
        candidat.setVille("casablanca");
        candidat.setDeleted(false);
        candidat.setSpecialite(Specialite.MARKETING);
        candidat.setNiveauEtude(NiveauEtude.BAC_2);



        when(iCandidatRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(candidat));

        assertAll(() -> candidatService.delete(1L));
    }

    @Test
    @DisplayName("candidatService_getAllCandidat_ReturnMoreThanOneCandidat")
    public void candidatService_getAllCandidat_ReturnMoreThanOneCandidat()
    {
        Candidat candidat = new Candidat();
        candidat.setId(1L);
        candidat.setNom("test1");
        candidat.setPrenom("test1");
        candidat.setTelephone("0631792201");
        candidat.setSexe(Sexe.homme);
        candidat.setAdresse("address 1");
        candidat.setVille("casablanca");
        candidat.setDeleted(false);
        candidat.setSpecialite(Specialite.MARKETING);
        candidat.setNiveauEtude(NiveauEtude.BAC_2);

        Candidat candidat2 = new Candidat();
        candidat2.setId(1L);
        candidat2.setNom("test2");
        candidat2.setPrenom("test2");
        candidat2.setTelephone("0631792201");
        candidat2.setSexe(Sexe.homme);
        candidat2.setAdresse("address2");
        candidat2.setVille("casablanca");
        candidat2.setDeleted(false);
        candidat2.setSpecialite(Specialite.MARKETING);
        candidat2.setNiveauEtude(NiveauEtude.BAC_2);

        Candidat candidat3 = new Candidat();
        candidat3.setId(1L);
        candidat3.setNom("test3");
        candidat3.setPrenom("test3");
        candidat3.setTelephone("0631792201");
        candidat3.setSexe(Sexe.homme);
        candidat3.setAdresse("address3");
        candidat3.setVille("casablanca");
        candidat3.setDeleted(false);
        candidat3.setSpecialite(Specialite.MARKETING);
        candidat3.setNiveauEtude(NiveauEtude.BAC_2);

        CandidatDto candidatDto = new CandidatDto();
        candidatDto.setNom("test1");
        candidatDto.setPrenom("test1");
        candidatDto.setTelephone("0631792201");
        candidatDto.setSexe(Sexe.homme);
        candidatDto.setAdresse("address 1");
        candidatDto.setVille("casablanca");
        candidatDto.setDeleted(false);
        candidatDto.setSpecialite(Specialite.MARKETING);
        candidatDto.setNiveauEtude(NiveauEtude.BAC_2);


        when(iCandidatRepository.findByDeletedFalse()).thenReturn(Arrays.asList(candidat, candidat2, candidat3));

        List<CandidatDto> candidatAll = candidatService.getAll();
        System.out.println(candidatAll);
        Assertions.assertThat(candidatAll).isNotEmpty();
        Assertions.assertThat(candidatAll).hasSize(3);
    }
}