package com.dh.DentalClinicMVC.repository;

import com.dh.DentalClinicMVC.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Long> {

    //consulta en HQL o JPQL - son esencialmente lo mismo
//    @Query("SELECT d FROM Dentist d WHERE d.registration=?1")
    //podemos no utilizar el query con hql porque estamos siguiendo las convenciones de spring data
    //usando el findBy atributo nos ahorramos de escribir la consulta
    Optional<Dentist> findByRegistration(Integer registration);
}
