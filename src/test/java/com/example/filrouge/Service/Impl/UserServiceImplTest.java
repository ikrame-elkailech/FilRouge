package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Enum.RoleUser;
import com.example.filrouge.Enum.Sexe;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private IUserRepository iUserRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    @DisplayName("Instantiation object iUserRepository par .mock & modelMapper & UserService")
    public void setup()
    {
        iUserRepository = Mockito.mock(IUserRepository.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ModelMapper modelMapper = new ModelMapper();
        userService = new UserServiceImpl(iUserRepository, modelMapper);
    }

    @Test
    @DisplayName("userService_saveUser_ReturnOneUser")
    public void userService_saveUser_ReturnOneUser()
    {
        User user = new User();
        user.setId(1L);
        user.setNom("test1");
        user.setPrenom("test1");
        user.setTelephone("0631792201");
        user.setSexe(Sexe.homme);
        user.setAdresse("address 1");
        user.setVille("casablanca");
        user.setDeleted(false);
        user.setUserRole(RoleUser.USER);
        user.setEmail("test@gmail.com");
        user.setPassword("123456");

        UserDto userDto = new UserDto();
        userDto.setNom("test1");
        userDto.setPrenom("test1");
        userDto.setTelephone("0631792201");
        userDto.setSexe(Sexe.homme);
        userDto.setAdresse("address 1");
        userDto.setVille("casablanca");
        userDto.setDeleted(false);
        userDto.setUserRole(RoleUser.USER);
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("123456");


        when(iUserRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDto userSaved = userService.add(userDto);
        System.out.println(userSaved);
        System.out.println(user.getNom());
        Assertions.assertThat(userSaved.getId()).isEqualTo(user.getId());
        Assertions.assertThat(userSaved).isNotNull();
    }

    @Test
    @DisplayName("userService_getUserByIdUser_ReturnOneUser")
    public void userService_getUserByIdUser_ReturnOneUser()
    {
        User user = new User();
        user.setId(1L);
        user.setNom("test1");
        user.setPrenom("test1");
        user.setTelephone("0631792201");
        user.setSexe(Sexe.homme);
        user.setAdresse("address 1");
        user.setVille("casablanca");
        user.setDeleted(false);
        user.setUserRole(RoleUser.USER);
        user.setEmail("test@gmail.com");
        user.setPassword("123456");

        UserDto userDto = new UserDto();
        userDto.setNom("test1");
        userDto.setPrenom("test1");
        userDto.setTelephone("0631792201");
        userDto.setSexe(Sexe.homme);
        userDto.setAdresse("address 1");
        userDto.setVille("casablanca");
        userDto.setDeleted(false);
        userDto.setUserRole(RoleUser.USER);
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("123456");


        when(iUserRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(user));

        UserDto userDtoSaved = userService.getById(1L);
        Assertions.assertThat(userDtoSaved).isNotNull();
        Assertions.assertThat(userDtoSaved.getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("userRepository_updateUser_ReturnOneUserUpdated")
    public void userRepository_updateUser_ReturnOneUserUpdated()
    {
        User user = new User();
        user.setId(1L);
        user.setNom("test1");
        user.setPrenom("test1");
        user.setTelephone("0631792201");
        user.setSexe(Sexe.homme);
        user.setAdresse("address 1");
        user.setVille("casablanca");
        user.setDeleted(false);
        user.setUserRole(RoleUser.USER);
        user.setEmail("test@gmail.com");
        user.setPassword("123456");

        UserDto userDto = new UserDto();
        userDto.setNom("test1");
        userDto.setPrenom("test1");
        userDto.setTelephone("0631792201");
        userDto.setSexe(Sexe.homme);
        userDto.setAdresse("address 1");
        userDto.setVille("casablanca");
        userDto.setDeleted(false);
        userDto.setUserRole(RoleUser.USER);
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("123456");


        when(iUserRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(iUserRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(user));


        UserDto userDtoUpdated = userService.update(1L, userDto);
        System.out.println(userDtoUpdated);
        System.out.println(user.getNom() + " " + user.getAdresse() + " " +  user.getPrenom());
        Assertions.assertThat(userDtoUpdated).isNotNull();
        Assertions.assertThat(userDtoUpdated.getAdresse()).isEqualTo(user.getAdresse());
    }

    @Test
    @DisplayName("userService_deleteByIdUser_Return0User")
    public void userService_deleteByIdUser_Return0User()
    {
        User user = new User();
        user.setId(1L);
        user.setNom("test1");
        user.setPrenom("test1");
        user.setTelephone("0631792201");
        user.setSexe(Sexe.homme);
        user.setAdresse("address 1");
        user.setVille("casablanca");
        user.setDeleted(false);
        user.setUserRole(RoleUser.USER);
        user.setEmail("test@gmail.com");
        user.setPassword("123456");



        when(iUserRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(user));

        assertAll(() -> userService.delete(1L));
    }

    @Test
    @DisplayName("patientService_getAllPatient_ReturnMoreThanOnePatient")
    public void patientService_getAllPatient_ReturnMoreThanOnePatient()
    {
        User user = new User();
        user.setId(1L);
        user.setNom("test1");
        user.setPrenom("test1");
        user.setTelephone("0631792201");
        user.setSexe(Sexe.homme);
        user.setAdresse("addresse1");
        user.setVille("casablanca");
        user.setDeleted(false);
        user.setUserRole(RoleUser.USER);
        user.setEmail("test@gmail.com");
        user.setPassword("123456");

        User user2 = new User();
        user2.setId(1L);
        user2.setNom("test2");
        user2.setPrenom("test2");
        user2.setTelephone("0631792201");
        user2.setSexe(Sexe.homme);
        user2.setAdresse("address2");
        user2.setVille("casablanca");
        user2.setDeleted(false);
        user2.setUserRole(RoleUser.USER);
        user2.setEmail("test@gmail.com");
        user2.setPassword("123456");

        User user3 = new User();
        user3.setId(1L);
        user3.setNom("test3");
        user3.setPrenom("test3");
        user3.setTelephone("0631792201");
        user3.setSexe(Sexe.homme);
        user3.setAdresse("address3");
        user3.setVille("casablanca");
        user3.setDeleted(false);
        user3.setUserRole(RoleUser.USER);
        user3.setEmail("test@gmail.com");
        user3.setPassword("123456");

        UserDto userDto = new UserDto();
        userDto.setNom("test1");
        userDto.setPrenom("test1");
        userDto.setTelephone("0631792201");
        userDto.setSexe(Sexe.homme);
        userDto.setAdresse("address 1");
        userDto.setVille("casablanca");
        userDto.setDeleted(false);
        userDto.setUserRole(RoleUser.USER);
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("123456");

        when(iUserRepository.findByDeletedFalse()).thenReturn(Arrays.asList(user, user2, user3));

        List<UserDto> userAll = userService.getAll();
        System.out.println(userAll);
        Assertions.assertThat(userAll).isNotEmpty();
        Assertions.assertThat(userAll).hasSize(3);
    }
}


