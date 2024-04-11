package com.example.filrouge.Service;

import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.User;

import java.util.List;

public interface IUserService {

    UserDto add(UserDto userDto);
    UserDto update(Long id, UserDto userDto);

    void delete(Long id);
    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto getByName(String name);
    String getByEmail(String email);
   // UserDto getByEmailInObject(String email);
    void checkExistEmail(UserDto userDto);
    void SiNoEqualCheckEmailExist(User userExist, UserDto userDto);
    void validation(UserDto userDto);
}
