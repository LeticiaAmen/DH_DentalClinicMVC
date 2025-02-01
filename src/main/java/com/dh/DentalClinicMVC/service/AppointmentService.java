package com.dh.DentalClinicMVC.service;

import com.dh.DentalClinicMVC.dao.AppointmentDaoList;
import com.dh.DentalClinicMVC.dao.IDao;
import com.dh.DentalClinicMVC.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private IDao<Appointment> appointmentIDao;

    public AppointmentService() {
        this.appointmentIDao = new AppointmentDaoList();
    }

    public Appointment save(Appointment appointment) {
        return appointmentIDao.save(appointment);
    }

    public Appointment findById(int id) {
        return appointmentIDao.findById(id);
    }

    public List<Appointment> findAll() {
        return appointmentIDao.findAll();
    }
}
