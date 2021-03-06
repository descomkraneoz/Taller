package net.severo.taller.DAO;

import net.severo.taller.pojo.Vehiculo;

import java.util.List;

public interface IVehiculo {
    void crearNuevoVehiculoDAO(Vehiculo vehiculo) throws DAOException;

    void modificarVehiculoDAO(Vehiculo vehiculo) throws DAOException;

    void eliminarVehiculoDAO(int idVehiculo) throws DAOException;

    Vehiculo obtenerUnVehiculoPorID(int idVehiculo) throws DAOException;
    List<Vehiculo> obtenerTodosVehiculos()  throws DAOException ;


    void finalizar() throws DAOException; //cortar la conexion

    void iniciarTransaccion() throws DAOException;

    void finalizarTransaccion() throws DAOException;


}
