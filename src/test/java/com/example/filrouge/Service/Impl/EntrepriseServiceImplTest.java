package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.EntrepriseDto;
import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.Entreprise;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Enum.RoleUser;
import com.example.filrouge.Enum.Sexe;
import com.example.filrouge.Repository.IEntrepriseRepository;
import com.example.filrouge.Repository.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EntrepriseServiceImplTest {

    @Mock
    private IEntrepriseRepository iEntrepriseRepository;

    @InjectMocks
    private EntrepriseServiceImpl entrepriseService;

    @BeforeEach
    @DisplayName("Instantiation object iEntrepriseRepository par .mock & modelMapper & EntrepriseService")
    public void setup()
    {
        iEntrepriseRepository = Mockito.mock(IEntrepriseRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        entrepriseService = new EntrepriseServiceImpl(modelMapper, iEntrepriseRepository);
    }

    @Test
    @DisplayName("entrepriseService_saveEntreprise_ReturnOneEntreprise")
    public void entrepriseService_saveEntreprise_ReturnOneEntreprise()
    {
        Entreprise entreprise = new Entreprise();
        entreprise.setId(1L);
        entreprise.setName("test1");
        entreprise.setEmail("test1@gmail.com");
        entreprise.setTelephone("0631792201");
        entreprise.setDeleted(false);


        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setId(1L);
        entrepriseDto.setName("test1");
        entrepriseDto.setEmail("test1@gmail.com");
        entrepriseDto.setTelephone("0631792201");
        entrepriseDto.setDeleted(false);


        when(iEntrepriseRepository.save(Mockito.any(Entreprise.class))).thenReturn(entreprise);

        EntrepriseDto entrepriseSaved = entrepriseService.add(entrepriseDto);
        System.out.println(entrepriseSaved);
        System.out.println(entreprise.getName());
        Assertions.assertThat(entrepriseSaved.getId()).isEqualTo(entreprise.getId());
        Assertions.assertThat(entrepriseSaved).isNotNull();
    }

    @Test
    @DisplayName("entrepriseService_getEntrepriseByIdEntreprise_ReturnOneEntreprise")
    public void entrepriseService_getEntrepriseByIdEntreprise_ReturnOneEntreprise()
    {
        Entreprise entreprise = new Entreprise();
        entreprise.setId(1L);
        entreprise.setName("test1");
        entreprise.setEmail("test1@gmail.com");
        entreprise.setTelephone("0631792201");
        entreprise.setDeleted(false);


        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setId(1L);
        entrepriseDto.setName("test1");
        entrepriseDto.setEmail("test1@gmail.com");
        entrepriseDto.setTelephone("0631792201");
        entrepriseDto.setDeleted(false);


        when(iEntrepriseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(entreprise));

        EntrepriseDto entrepriseDtoSaved = entrepriseService.getById(1L);
        Assertions.assertThat(entrepriseDtoSaved).isNotNull();
        Assertions.assertThat(entrepriseDtoSaved.getId()).isEqualTo(entreprise.getId());
    }

    @Test
    @DisplayName("entrepriseRepository_updateEntreprise_ReturnOneEntrepriseUpdated")
    public void entrepriseRepository_updateEntreprise_ReturnOneEntrepriseUpdated()
    {
        Entreprise entreprise = new Entreprise();
        entreprise.setId(1L);
        entreprise.setName("test1");
        entreprise.setEmail("test1@gmail.com");
        entreprise.setTelephone("0631792201");
        entreprise.setDeleted(false);


        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setId(1L);
        entrepriseDto.setName("test1");
        entrepriseDto.setEmail("test1@gmail.com");
        entrepriseDto.setTelephone("0631792201");
        entrepriseDto.setDeleted(false);

        when(iEntrepriseRepository.save(Mockito.any(Entreprise.class))).thenReturn(entreprise);
        when(iEntrepriseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(entreprise));


        EntrepriseDto entrepriseDtoUpdated = entrepriseService.update(1L, entrepriseDto);
        System.out.println(entrepriseDtoUpdated);
        System.out.println(entreprise.getName());
        Assertions.assertThat(entrepriseDtoUpdated).isNotNull();
        Assertions.assertThat(entrepriseDtoUpdated.getName()).isEqualTo(entreprise.getName());
    }

    @Test
    @DisplayName("entrepriseService_deleteByIdEntreprise_Return0Entreprise")
    public void entrepriseService_deleteByIdEntreprise_Return0Entreprise()
    {
        Entreprise entreprise = new Entreprise();
        entreprise.setId(1L);
        entreprise.setName("test1");
        entreprise.setEmail("test1@gmail.com");
        entreprise.setTelephone("0631792201");
        entreprise.setDeleted(false);



        when(iEntrepriseRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(entreprise));

        assertAll(() -> entrepriseService.delete(1L));
    }

    @Test
    @DisplayName("patientService_getAllPatient_ReturnMoreThanOnePatient")
    public void patientService_getAllPatient_ReturnMoreThanOnePatient()
    {
        Entreprise entreprise = new Entreprise();
        entreprise.setId(1L);
        entreprise.setName("test1");
        entreprise.setEmail("test1@gmail.com");
        entreprise.setTelephone("0631792201");
        entreprise.setDeleted(false);

        Entreprise entreprise2 = new Entreprise();
        entreprise2.setId(1L);
        entreprise2.setName("test2");
        entreprise2.setEmail("test2@gmail.com");
        entreprise2.setTelephone("0631792201");
        entreprise2.setDeleted(false);

        Entreprise entreprise3 = new Entreprise();
        entreprise3.setId(1L);
        entreprise3.setName("test3");
        entreprise3.setEmail("test3@gmail.com");
        entreprise3.setTelephone("0631792201");
        entreprise3.setDeleted(false);


        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setId(1L);
        entrepriseDto.setName("test1");
        entrepriseDto.setEmail("test1@gmail.com");
        entrepriseDto.setTelephone("0631792201");
        entrepriseDto.setDeleted(false);

        when(iEntrepriseRepository.findByDeletedFalse()).thenReturn(Arrays.asList(entreprise, entreprise2, entreprise3));

        List<EntrepriseDto> entrepriseAll = entrepriseService.getAll();
        System.out.println(entrepriseAll);
        Assertions.assertThat(entrepriseAll).isNotEmpty();
        Assertions.assertThat(entrepriseAll).hasSize(3);
    }



}