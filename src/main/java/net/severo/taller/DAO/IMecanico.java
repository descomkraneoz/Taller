package net.severo.taller.DAO;

import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;

import java.util.List;

public interface IMecanico {
    void crearMecanico(Mecanico mecanico)throws DAOException ;
    void modificarMecanico(Mecanico mecanico)throws DAOException ;
    void eliminarMecanico(int idMecanico)throws DAOException ;
    List<Mecanico> obtenerTodosMecanicos()  throws DAOException ;

    List<Mecanico> obtenerTodosMecanicos(int idMecanico) throws DAOException;


    List<Vehiculo> obtenerVehiculosPorMecanico(int idVehiculo) throws DAOException;

    Mecanico obtenerMecanicoPorID(int id) throws DAOException;

    void asignarMecanicoVehiculo(Mecanico m, Vehiculo v) throws DAOException;

    void finalizar() throws DAOException; //cortar la conexion

    void iniciarTransaccion() throws DAOException;

    void finalizarTransaccion() throws DAOException;


}
