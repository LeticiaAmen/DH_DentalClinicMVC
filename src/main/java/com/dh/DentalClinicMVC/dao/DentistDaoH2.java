package com.dh.DentalClinicMVC.dao;

import com.dh.DentalClinicMVC.model.Address;
import com.dh.DentalClinicMVC.model.Dentist;
import com.dh.DentalClinicMVC.model.Patient;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DentistDaoH2 implements IDao<Dentist> {

  private static final String SQL_INSERT= "INSERT INTO DENTISTS (REGISTRATION," +
          "NAME, LAST_NAME) VALUES (?,?,?)";

  private static final String SQL_SELECT_ID = " SELECT * FROM DENTISTS WHERE ID = ?";

  private static final String SQL_UPDATE = "UPDATE DENTISTS SET REGISTRATION=?, " +
          "NAME=?, LAST_NAME=? WHERE ID = ?";

  private static final String SQL_DELETE = "DELETE FROM DENTISTS WHERE ID = ?";

  private static final String SQL_SELECT_ALL = "SELECT * FROM DENTISTS";

    @Override
    public Dentist save(Dentist dentist) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            //Statement.RETURN_GENERATED_KEYS para que genere y devuelva la id
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dentist.getRegistration());
            ps.setString(2, dentist.getName());
            ps.setString(3, dentist.getLastName());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                dentist.setId(rs.getInt(1)); //esto es disntito de los parámetros de la consulta,
                // acá el 1 indica el índice de la columna de la tabla.
                // Que se generó al usar Statement.RETURN_GENERATED_KEYS
            }

        } catch (Exception e){
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return dentist;
    }

    @Override
    public Dentist findById(int id) {
        Connection connection = null;
        Dentist dentist = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dentist = new Dentist(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return dentist;
    }

    @Override
    public void update(Dentist dentist) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setInt(1, dentist.getRegistration());
            ps.setString(2, dentist.getName());
            ps.setString(3, dentist.getLastName());
            ps.setInt(4, dentist.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Dentist> findAll() {
        Connection connection = null;
        Dentist dentist = null;
        List<Dentist> dentistList = new ArrayList<>();
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               dentist = new Dentist(rs.getInt(1), rs.getInt(2),
                       rs.getString(3), rs.getString(4));
                dentistList.add(dentist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return dentistList;
    }

    @Override
    public Dentist findByString(String value) {
        return null;
    }
}
