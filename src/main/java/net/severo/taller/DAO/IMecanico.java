package net.severo.taller.DAO;

import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;

import java.util.List;

public interface IMecanico {
    void crearMecanico(Mecanico mecanico)throws DAOException ;
    void modificarMecanico(Mecanico mecanico)throws DAOException ;
    void eliminarMecanico(int idMecanico)throws DAOException ;
    List<Mecanico> obtenerTodosMecanicos()  throws DAOException ;
    List<Mecanico> obtenerTodosMecanicos(int idVehiculo) throws DAOException;

    List<Vehiculo> obtenerVehiculosPorMecanico() throws DAOException;
    List<Vehiculo> obtenerVehiculosPorMecanico(int idMecanico) throws DAOException;

    Mecanico obtenerMecanicoPorID(int id) throws DAOException;

    public void finalizar() throws DAOException; //cortar la conexion

    public void iniciarTransaccion() throws DAOException;

    public void finalizarTransaccion() throws DAOException;
}
