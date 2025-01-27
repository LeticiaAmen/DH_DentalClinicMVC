package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Dentist;
import com.dh.DentalClinicMVC.service.DentistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    private DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    //localhost:8080/odontologos/{id}
    @GetMapping("/{id}") //entre llaves porque es una variable
    public Dentist findById(@PathVariable Integer id) {
        return dentistService.findById(id);
    }

    //guardar nuevo odontologo
    @PostMapping
    public Dentist save(@RequestBody Dentist dentist) {
        return dentistService.save(dentist);
    }

    //actualizar los datos de un odontologo
    @PutMapping
    public void update(@RequestBody Dentist dentist) {
        dentistService.updateDentist(dentist);
    }

    //borrar odontologo
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        dentistService.deleteDentist(id);
    }

    //Listar todos
    @GetMapping
    public List<Dentist> findAll() {
        return dentistService.findAll();
    }
}
