package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.entity.Dentist;
import com.dh.DentalClinicMVC.service.IDentistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //add filters para desactivar los filtros de seguridad al testear
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DentistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IDentistService dentistService;


    public void dataLoad(){
        Dentist dentist = new Dentist();
        dentist.setRegistration(5826);
        dentist.setName("Pedro");
        dentist.setLastName("Muelas");
        dentistService.save(dentist);

    }

    @Test
    @Order(2)
    public void testGetDentistById() throws Exception {
        dataLoad();
        mockMvc.perform(get("/odontologos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registration").value("5826"))
                .andExpect(jsonPath("$.name").value("Pedro"))
                .andExpect(jsonPath("$.lastName").value("Muelas"));
    }

    @Test
    @Order(3)
    public void testPostDentist() throws Exception {
        String dentistSaved = "{\"registration\":\"125\",\"name\":\"Juan\",\"lastName\":\"Perez\"}";

        mockMvc.perform(post("/odontologos")
        .contentType(MediaType.APPLICATION_JSON)
                .content(dentistSaved)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registration").value("125"))
                .andExpect(jsonPath("$.name").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Perez"));
    }

    @Test
    @Order(1)
    public void testGetAllDentists() throws Exception {
        mockMvc.perform(get("/odontologos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

}