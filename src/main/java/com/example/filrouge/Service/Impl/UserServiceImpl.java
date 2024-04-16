package com.example.filrouge.Service.Impl;

import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Exception.EmailDuplicateRecordException;
import com.example.filrouge.Exception.NotFoundException;
import com.example.filrouge.Repository.IUserRepository;
import com.example.filrouge.Service.IUserService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
///import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository iUserRepository;
    private final ModelMapper modelMapper;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto add(UserDto userDto)
    {
        validation(userDto);
        checkExistEmail(userDto);
        //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = iUserRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto  update(Long id, UserDto userDto)
    {
        validation(userDto);

        User userExist = iUserRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("User not found with this id : " + id));
        //TODO: CHECK SI EMAIL FOURNI EQUAL EMAIL OF USER EXIST SI NO EQUAL CHECK EMAIL EXIST IN SYSTEM OR NOT ;
        SiNoEqualCheckEmailExist(userExist, userDto);

        userExist.setNom(userDto.getNom());
        userExist.setEmail(userDto.getEmail());
        userExist.setUserRole(userDto.getUserRole());
        userExist.setPrenom(userDto.getPrenom());
        userExist.setSexe(userDto.getSexe());
        userExist.setAdresse(userDto.getAdresse());
        userExist.setPassword(userDto.getPassword());
        userExist.setVille(userDto.getVille());
        userExist.setTelephone(userDto.getTelephone());

        User userUpdated = iUserRepository.save(userExist);
        return modelMapper.map(userUpdated, UserDto.class);
    }

    @Override
    public void delete(Long id)
    {
        User user = iUserRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("User not found with this id : " + id));
        user.setDeleted(true);
        iUserRepository.save(user);
    }

    @Override
    public List<UserDto> getAll()
    {
        List<User> users = iUserRepository.findByDeletedFalse();
        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id)
    {
        User user = iUserRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("User not found with this id : " + id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getByName(String name)
    {
        User user = iUserRepository.findByNomAndDeletedFalse(name).orElseThrow(() -> new NotFoundException("User not found with this name : " + name));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public String getByEmail(String email)
    {
        User user = iUserRepository.findByEmailAndDeletedFalse(email).orElse(null);
        if(user != null)
        {
            return user.getEmail();
        }
        return null;
    }

    @Override
    public UserDto getByEmailInObject(String email)
    {
        User user = iUserRepository.findByEmailAndDeletedFalse(email).orElseThrow(() -> new UsernameNotFoundException("User not found with this email : " + email));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void checkExistEmail(UserDto userDto)
    {
        if(userDto.getEmail().equals(getByEmail(userDto.getEmail())))
        {
            throw new EmailDuplicateRecordException("Email already exist in system.");
        }
    }

    @Override
    public void SiNoEqualCheckEmailExist(User userExist, UserDto userDto)
    {
        if (!userDto.getEmail().equals(userExist.getEmail()))
        {
            checkExistEmail(userDto);
        }
    }

    @Override
    public void validation(UserDto userDto)
    {
        if (userDto == null) {
            throw new ValidationException("Les données du user sont nécessaires.");
        }

        if (StringUtils.isBlank(userDto.getNom())) {
            throw new ValidationException("Le nom est requise.");
        }

        if (StringUtils.isBlank(userDto.getPrenom())) {
            throw new ValidationException("Le prénom est requise.");
        }

        if (StringUtils.isBlank(userDto.getAdresse())) {
            throw new ValidationException("L'adresse est requise.");
        }

        if (StringUtils.isBlank(userDto.getTelephone())) {
            throw new ValidationException("Le numéro de téléphone est requise.");
        }

        if (StringUtils.isBlank(userDto.getVille())) {
            throw new ValidationException("La ville est requise.");
        }

        if (userDto.getSexe() == null) {
            throw new ValidationException("Le sexe est requise.");
        }



        if (userDto.getEmail() == null) {
            throw new ValidationException("Email est requise.");
        }

        if (userDto.getPassword() == null) {
            throw new ValidationException("Password est requise.");
        }
    }

}
