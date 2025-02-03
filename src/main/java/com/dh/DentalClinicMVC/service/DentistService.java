package com.dh.DentalClinicMVC.service;

import com.dh.DentalClinicMVC.dao.DentistDaoH2;
import com.dh.DentalClinicMVC.dao.IDao;
import com.dh.DentalClinicMVC.model.Dentist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {

    private IDao<Dentist> dentistIDao;

    public DentistService(IDao<Dentist> dentistIDao) {
        this.dentistIDao = dentistIDao;
    }

    public Dentist save(Dentist dentist) {
        return dentistIDao.save(dentist);
    }

    public Dentist findById(Integer id) {
        return dentistIDao.findById(id);
    }

    public void updateDentist(Dentist dentist) {
        dentistIDao.update(dentist);
    }

    public void deleteDentist(Integer id) {
        dentistIDao.delete(id);
    }

    public List<Dentist> findAll() {
        return dentistIDao.findAll();
    }
}
