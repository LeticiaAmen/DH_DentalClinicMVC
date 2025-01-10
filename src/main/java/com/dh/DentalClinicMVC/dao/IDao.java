package com.dh.DentalClinicMVC.dao;

import java.util.List;

public interface IDao<T> {

    T save(T t); //Para crear un objeto de tipo genérico
    T findById(int id);
    void update(T t);
    void delete(Integer id); //id del objeto a borrar
    List<T> findAll();


    //este método es para el find by email pero lo hacemos más general porque en otras clases puede no ser un email
    T findByString(String value);

}
