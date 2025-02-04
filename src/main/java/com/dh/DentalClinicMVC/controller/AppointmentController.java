package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Appointment;
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

    //este endpoint consulta todos los turnos
    @GetMapping
    public ResponseEntity<List<Appointment>>  finAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment) {
        // Declaración de la variable que almacenará la respuesta HTTP
        ResponseEntity<Appointment> response;

        // Verifica si el odontólogo y el paciente asociados a la cita existen en la base de datos
        if(dentistService.findById(appointment.getDentist().getId()).isPresent()
        && patientService.findById(appointment.getPatient().getId()).isPresent()) {
            // Si ambos existen, seteamos el código 200 y le agregamos el turno como cuerpo de la respuesta
            response = ResponseEntity.ok(appointmentService.save(appointment));
        } else{
            // Si alguno de los dos no existe, seteamos un código 400 Bad Request
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // retorna la respuesta HTTP construida
       return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable Long id) {
        Optional<Appointment> appointmentToLookFor = appointmentService.findById(id);

        if(appointmentToLookFor.isPresent()) {
            return ResponseEntity.ok(appointmentToLookFor.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Appointment appointment) {
        ResponseEntity<String> response;

        //chequeamos que existan el odontólogo y el paciente
        if(dentistService.findById(appointment.getDentist().getId()).isPresent() &&
        patientService.findById(appointment.getPatient().getId()).isPresent()) {
            //ambos existen en la BD
            //seteamos al responseEntity en código 200 y le agregamos el turno
            appointmentService.update(appointment);
            response = ResponseEntity.ok("Se actualizó el turno con id: " + appointment.getId());
        }else {
            //uno no existe, entonces bloqueamos la operación
            //setear al REsponseEntity en el código 400
            response = ResponseEntity.badRequest().body("No se puede actualizar un turno que no existe en la Base de datos");
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
