package com.example.filrouge.Controller;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Entities.Candidat;
import com.example.filrouge.Entities.User;
import com.example.filrouge.Enum.NiveauEtude;
import com.example.filrouge.Enum.RoleUser;
import com.example.filrouge.Enum.Sexe;
import com.example.filrouge.Enum.Specialite;
import com.example.filrouge.Service.Impl.CandidatServiceImpl;
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

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(controllers = CandidatController.class)
@ExtendWith(MockitoExtension.class)
class CandidatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidatServiceImpl candidatService;

    @Autowired
    private ObjectMapper objectMapper;

    private Candidat candidat;
    private CandidatDto candidatDto;

    @BeforeEach
    public void init()
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
    }

    @Test
    public void candidatController_addCandidat_ReturnCandidat() throws Exception {
        given(candidatService.add(ArgumentMatchers.any(CandidatDto.class)))
                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/candidat/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(candidatDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void userController_getAllUser_ReturnMoreThanUser() throws Exception
    {
        when(candidatService.getAll()).thenReturn(Collections.singletonList(candidatDto));

        ResultActions response = mockMvc.perform(get("/api/candidat")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void userController_getByIDUser_ReturnUser() throws Exception {
        when(candidatService.getById(1L)).thenReturn(candidatDto);

        ResultActions response = mockMvc.perform(get("/api/candidat/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void userController_deleteUser_ReturnNothing() throws Exception {
        doNothing().when(candidatService).delete(1L);

        ResultActions response = mockMvc.perform(delete("/api/candidat/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void userController_updateUser_ReturnUser() throws Exception {
        when(candidatService.update(1L, candidatDto)).thenReturn(candidatDto);

        ResultActions response = mockMvc.perform(put("/api/candidat/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(candidatDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }




}