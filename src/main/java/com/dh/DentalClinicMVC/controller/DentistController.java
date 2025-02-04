package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.entity.Dentist;
import com.dh.DentalClinicMVC.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    private IDentistService identistService;

    @Autowired
    public DentistController(IDentistService dentistService) {
        this.identistService = dentistService;
    }


    //localhost:8080/odontologos/{id}
    @GetMapping("/{id}") //entre llaves porque es una variable
    public ResponseEntity<Dentist> findById(@PathVariable Long id) {
        Optional<Dentist> dentist = identistService.findById(id);
        if(dentist.isPresent()){
            return ResponseEntity.ok().body(dentist.get());
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    //guardar nuevo odontologo
    @PostMapping
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(identistService.save(dentist));
    }

    //actualizar los datos de un odontologo
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Dentist dentist) {
        ResponseEntity<String> response;
        Optional<Dentist> dentistToLookFor = identistService.findById(dentist.getId());

        if(dentistToLookFor.isPresent()){
            identistService.update(dentist);
            response = ResponseEntity.ok("Se actualizó el odontólogo con nombre: " + dentist.getName());
        }else {
            response = ResponseEntity.ok().body("No se puede actualizar un odontólogo que no existe en la base de datos");
        }
        return response;
    }

    //borrar odontologo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response;
        Optional<Dentist> dentist = identistService.findById(id);

        if(dentist.isPresent()){
            identistService.delete(id);
            response = ResponseEntity.ok("Se eliminó el odontólogo con id: " + id);
        }else {
            response = ResponseEntity.ok().body("No se puede eliminar un odontólogo que no existe en la base de datos");
        }
        return response;

    }

    //Listar todos
    @GetMapping
    public ResponseEntity<List<Dentist>> findAll() {
        return ResponseEntity.ok(identistService.findAll());
    }
}
