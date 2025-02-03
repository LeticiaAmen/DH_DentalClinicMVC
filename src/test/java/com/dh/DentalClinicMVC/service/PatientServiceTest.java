package com.dh.DentalClinicMVC.service;

import com.dh.DentalClinicMVC.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    void findById() {
        Integer idPatient = 3;

        //buscar al paciente
        Patient patient = patientService.findById(idPatient);
        assertNotNull(patient);

    }
}