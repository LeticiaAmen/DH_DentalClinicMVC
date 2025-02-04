package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.dto.AppointmentDTO;
import com.dh.DentalClinicMVC.entity.Appointment;
import com.dh.DentalClinicMVC.service.IAppointmentService;
import com.dh.DentalClinicMVC.service.IDentistService;
import com.dh.DentalClinicMVC.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class AppointmentController {

    private IAppointmentService appointmentService;
    private IDentistService dentistService;
    private IPatientService patientService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService, IDentistService dentistService, IPatientService patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    //en el controlador que es lo que tiene contacto con la vista, dejamos de trabajar con entidades y
    //ahora trabajamos con DTO, añadiendo una capa más de abstracción. protegemos la información
    //vamos a recibir y enviar DTOs pero persistir entidades en la bd

    //este endpoint consulta todos los turnos
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>>  finAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO) {
        // Declaración de la variable que almacenará la respuesta HTTP
        ResponseEntity<AppointmentDTO> response;

        // Verifica si el odontólogo y el paciente asociados a la cita existen en la base de datos
        if(dentistService.findById(appointmentDTO.getDentist_id()).isPresent()
        && patientService.findById(appointmentDTO.getPatient_id()).isPresent()) {
            // Si ambos existen, seteamos el código 200 y le agregamos el turno como cuerpo de la respuesta
            response = ResponseEntity.ok(appointmentService.save(appointmentDTO));
        } else{
            // Si alguno de los dos no existe, seteamos un código 400 Bad Request
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // retorna la respuesta HTTP construida
       return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Long id) {
        Optional<AppointmentDTO> appointmentToLookFor = appointmentService.findById(id);

        if(appointmentToLookFor.isPresent()) {
            return ResponseEntity.ok(appointmentToLookFor.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO) throws Exception {
        ResponseEntity<AppointmentDTO> response;

        //chequeamos que existan el odontólogo y el paciente
        if(dentistService.findById(appointmentDTO.getDentist_id()).isPresent() &&
        patientService.findById(appointmentDTO.getPatient_id()).isPresent()) {
            //ambos existen en la BD
            //seteamos al responseEntity en código 200 y le agregamos el turno dto como cuerpo de la respuesta
            response = ResponseEntity.ok(appointmentService.update(appointmentDTO));

        }else {
            //uno no existe, entonces bloqueamos la operación
            //setear al REsponseEntity en el código 400
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response;

        if(appointmentService.findById(id).isPresent()) {
            appointmentService.delete(id);
            response = ResponseEntity.ok("Se eliminó el turno con id: " + id);
        } else {
            response = ResponseEntity.ok().body("No se puede eliminar un turno que no existe en la Base de datos");
        }
        return response;
    }



}
