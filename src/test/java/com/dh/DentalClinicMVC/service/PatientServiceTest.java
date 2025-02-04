package com.dh.DentalClinicMVC.service;

import com.dh.DentalClinicMVC.model.Patient;
import com.dh.DentalClinicMVC.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @Test
    void findById() {
        Long idPatient = (long) 3;

        //buscar al paciente
        Optional<Patient> patient = patientServiceImpl.findById(idPatient);
        assertTrue(patient.isPresent());

    }
}