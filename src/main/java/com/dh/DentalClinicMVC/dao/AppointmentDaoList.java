package com.dh.DentalClinicMVC.dao;

import com.dh.DentalClinicMVC.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoList implements IDao<Appointment> {

    private List<Appointment> appointments;

    public AppointmentDaoList() {
        appointments = new ArrayList<>(); //generamos espacio en memoria para la lista dentro del constructor
    }

    @Override
    public Appointment save(Appointment appointment) {
        //guardamos en la lista el turno que recibimos por parámentro
        appointments.add(appointment);
        return appointment;
    }

    @Override
    public Appointment findById(int id) {
        Appointment appointmentToLookFor = null; //instanciamos el turno en null
        //recorrer la lista
        for(Appointment a : appointments) {
            //ir chequeando que el id matchee con el id que estamos buscando
            if(a.getId() == id){
                appointmentToLookFor = a;
                return appointmentToLookFor;
            }
        }
        return appointmentToLookFor;
    }

    @Override
    public void update(Appointment appointment) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Appointment> findAll() {
        return appointments;
    }

    @Override
    public Appointment findByString(String value) {
        return null;
    }
}
