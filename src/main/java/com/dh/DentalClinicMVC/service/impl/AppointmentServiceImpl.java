package com.dh.DentalClinicMVC.service.impl;

import com.dh.DentalClinicMVC.model.Appointment;
import com.dh.DentalClinicMVC.repository.IAppointmentRepository;
import com.dh.DentalClinicMVC.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentRepository.save(appointment);

    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);

    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }
}
