package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Dentist;
import com.dh.DentalClinicMVC.model.Patient;
import com.dh.DentalClinicMVC.service.DentistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dh.DentalClinicMVC.service.PatientService;

@Controller
@RequestMapping("/index")
public class IndexController {

    private PatientService patientService;

    private DentistService dentistService;

    public IndexController(PatientService patientService, DentistService dentistService) {
        this.patientService = patientService;
        this.dentistService = dentistService;
    }

    @GetMapping
    public String findPatientByEmail(Model model, @RequestParam("email") String email,
                                     @RequestParam ("id") Integer id){

        Patient patient = patientService.findByEmail(email);

        //buscamos al odontólogo por id
        Dentist dentist = dentistService.findById(id);

        model.addAttribute("name", patient.getName());
        model.addAttribute("lastName", patient.getLastName());

        //agregar la vista que se corresponde con odontólogo
        model.addAttribute("nameDentist", dentist.getName());
        model.addAttribute("lastNameDentist", dentist.getLastName());
        model.addAttribute("registration", dentist.getRegistration());

        return "index";


    }
}
