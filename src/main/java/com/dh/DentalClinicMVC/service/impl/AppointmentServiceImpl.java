package com.dh.DentalClinicMVC.service.impl;

import com.dh.DentalClinicMVC.dto.AppointmentDTO;
import com.dh.DentalClinicMVC.entity.Appointment;
import com.dh.DentalClinicMVC.entity.Dentist;
import com.dh.DentalClinicMVC.entity.Patient;
import com.dh.DentalClinicMVC.exception.ResourceNotFoundException;
import com.dh.DentalClinicMVC.repository.IAppointmentRepository;
import com.dh.DentalClinicMVC.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
    private IAppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        //mapear nuestras entidades como DTO manualmente
        //instanciar una entidad de appointment
        Appointment appointmentEntity = new Appointment();

        //instanciar un paciente y setearle el id que viene en el DTO
        Patient patientEntity = new Patient();
        patientEntity.setId(appointmentDTO.getPatient_id());

        //instanciar un odontólogo y setearle el id que viene en el DTO
        Dentist dentistEntity = new Dentist();
        dentistEntity.setId(appointmentDTO.getDentist_id());

        //seteamos el paciente y el odontólogo a la entidad turno
        appointmentEntity.setPatient(patientEntity);
        appointmentEntity.setDentist(dentistEntity);

        //convertir el string de la fecha de appointmentDTO a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(appointmentDTO.getDate(), formatter);

        //setear la fecha
        appointmentEntity.setDate(date);

        //persistir en BD
        appointmentRepository.save(appointmentEntity);

        //vamos a trabajar con el DTO que debemos devolver
        //generar una instancia de appointmentDTO
        AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();

        //le seteamos los datos de la entidad que persistimos
        appointmentDTOToReturn.setId(appointmentEntity.getId());
        appointmentDTOToReturn.setDate(appointmentEntity.getDate().toString());
        appointmentDTOToReturn.setDentist_id(appointmentEntity.getDentist().getId());
        appointmentDTOToReturn.setPatient_id(appointmentEntity.getPatient().getId());

        return appointmentDTOToReturn;


    }

    @Override
    public Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException {
        //buscamos la entidad por id en la BD
        Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);

        //instamciamos el optional de dto a devolver
        Optional<AppointmentDTO> appointmentDTOOptional = null;

        if (appointmentToLookFor.isPresent()) {
            //recuperar la entidad que se encontró y guardarla en la variable appointmentEntity
            Appointment appointmentEntity = appointmentToLookFor.get();

            //trabajar sobre la información que tenemos que devolver: dto
            //vamos a crear una instancia de appointmentDTO para devolver
            AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();

            //seteamos los atributos del DTO
            appointmentDTOToReturn.setId(appointmentEntity.getId());
            appointmentDTOToReturn.setPatient_id(appointmentEntity.getPatient().getId());
            appointmentDTOToReturn.setDentist_id(appointmentEntity.getDentist().getId());
            appointmentDTOToReturn.setDate(appointmentEntity.getDate().toString());

            appointmentDTOOptional = Optional.of(appointmentDTOToReturn);
            return appointmentDTOOptional;
        } else {
            throw new ResourceNotFoundException("No se encontró el turno con id: " + id);
        }

    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) throws Exception {

        //validamos que el turno a actualizar exista
        if(appointmentRepository.findById(appointmentDTO.getId()).isPresent()){

            //recuperar la entidad de la BD y guardarla en una variable
            Optional<Appointment> appointmentEntity = appointmentRepository.findById(appointmentDTO.getId());

            //instanciar un paciente
            Patient patientEntity = new Patient();
            patientEntity.setId(appointmentDTO.getPatient_id());

            //instanciar un odontólogo
            Dentist dentistEntity = new Dentist();
            dentistEntity.setId(appointmentDTO.getDentist_id());

            //seteamos el paciente y el odontólogo a el opcional de appointment
            appointmentEntity.get().setPatient(patientEntity);
            appointmentEntity.get().setDentist(dentistEntity);

            //convertir el string de turnoDTO que es la fecha a LocalDAte
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(appointmentDTO.getDate(), formatter);

            //setear la fecha
            appointmentEntity.get().setDate(date);

            //persistir en la BD
            appointmentRepository.save(appointmentEntity.get());

            //vamos a trabajar sobre la respuesta (dto) a devolver

            AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
            appointmentDTOToReturn.setId(appointmentEntity.get().getId());
            appointmentDTOToReturn.setPatient_id(appointmentEntity.get().getPatient().getId());
            appointmentDTOToReturn.setDentist_id(appointmentEntity.get().getDentist().getId());
            appointmentDTOToReturn.setDate(appointmentEntity.get().getDate().toString());

            return appointmentDTOToReturn;
        }else {
            throw new Exception("No se pudo actualizar el turno");
        }
    }

    @Override
    public Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException {
        //buscamos la entidad por id en BD y guardarla en un optional
        Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);
        Optional<AppointmentDTO> appointmentDTO;

        if(appointmentToLookFor.isPresent()){
            //si existe la entidad lo recuperamos y lo guardamos en una variable turno
            Appointment appointment = appointmentToLookFor.get();

            //devolvemos un dto entonces vamos a trabajar sobre él
            //creamos una instancia de ese DTO y le seteamos los atributos de la entidad recuperada
            AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
            appointmentDTOToReturn.setId(appointment.getId());
            appointmentDTOToReturn.setDentist_id(appointment.getDentist().getId());
            appointmentDTOToReturn.setPatient_id(appointment.getPatient().getId());
            appointmentDTOToReturn.setDate(appointment.getDate().toString());

            appointmentDTO = Optional.of(appointmentDTOToReturn);
            return appointmentDTO;

        } else {
            //lanzamos la exception
            throw new ResourceNotFoundException("No se encontró el turno con id: " + id);
        }
    }

    @Override
    public List<AppointmentDTO> findAll() {
        //vamos a traernos las entidades de la BD y las vamos a guardar en una lista
        List<Appointment> appointments = appointmentRepository.findAll();

        //vamos a crear una lista vacía de appointmentsDTO que luego devolveremos
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        //recorremos la lista de entidades de appointments para luego
        //guardarlas en la lista de appointmentsDTO
        for (Appointment appointmentEntity : appointments) {
            appointmentDTOS.add(new AppointmentDTO(
                    appointmentEntity.getId(),
                    appointmentEntity.getPatient().getId(),
                    appointmentEntity.getDentist().getId(),
                    appointmentEntity.getDate().toString())
            );
        }
        return appointmentDTOS;

    }
}
