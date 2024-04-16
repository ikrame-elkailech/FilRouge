package com.example.filrouge.Controller;

import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Enum.RoleUser;
import com.example.filrouge.Enum.Sexe;
import com.example.filrouge.Service.Impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void init()
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
    }

    @Test
    public void userController_addUser_ReturnUser() throws Exception {
        given(userService.add(ArgumentMatchers.any(UserDto.class)))
                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void userController_getAllUser_ReturnMoreThanUser() throws Exception
    {
        when(userService.getAll()).thenReturn(Collections.singletonList(userDto));

        ResultActions response = mockMvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void userController_getByIDUser_ReturnUser() throws Exception {
        when(userService.getById(1L)).thenReturn(userDto);

        ResultActions response = mockMvc.perform(get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void userController_deleteUser_ReturnNothing() throws Exception {
        doNothing().when(userService).delete(1L);

        ResultActions response = mockMvc.perform(delete("/api/user/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void userController_updateUser_ReturnUser() throws Exception {
        when(userService.update(1L, userDto)).thenReturn(userDto);

        ResultActions response = mockMvc.perform(put("/api/user/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



}