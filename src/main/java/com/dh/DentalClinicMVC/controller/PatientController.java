package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Patient;
import com.dh.DentalClinicMVC.service.DentistService;
import com.dh.DentalClinicMVC.service.PatientService;
import org.springframework.web.bind.annotation.*;

//Controller para vistas, rest controller para apis
@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //un endpoint que permita agregar un paciente
    @PostMapping
    public Patient save(@RequestBody Patient patient) { //el paciente viene en el cuerpo de la petición
        return patientService.save(patient);
    }

    //un endpoint que nos permita actualizar un paciente que ya esté registrado
    @PutMapping
    public void update(@RequestBody Patient patient) {
        patientService.update(patient);
    }
}
